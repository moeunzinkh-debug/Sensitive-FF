#!/usr/bin/env python3
"""Structural APK signature check (stdlib only, no Android SDK required).

An APK that Android refuses to install ("App not installed" / "failed install",
flagged as unsafe by Play Protect) is, in almost every case, an APK with no
signature at all. This script answers that question without needing
`apksigner`/`keytool`/a JDK:

  * v1 (JAR signing)      -> META-INF/*.SF + META-INF/*.{RSA,DSA,EC}
  * v2 / v3 / v3.1 / v4   -> APK Signing Block placed just before the ZIP
                             central directory, identified by the
                             "APK Sig Block 42" magic and its id/value pairs

Exit code 0 = signed with at least one scheme, 1 = unsigned, 2 = unreadable.
This complements `apksigner verify` (which is the authority when available):
it never claims the signature is *cryptographically valid*, only that a
signature is present - which is exactly the failure mode that makes Android
reject an install.
"""

from __future__ import annotations

import struct
import sys
import zipfile

APK_SIG_BLOCK_MAGIC = b"APK Sig Block 42"
EOCD_SIGNATURE = b"PK\x05\x06"

SCHEME_IDS = {
    0x7109871A: "v2 (APK Signature Scheme v2)",
    0xF05368C0: "v3 (APK Signature Scheme v3)",
    0x1B93AD61: "v3.1 (APK Signature Scheme v3.1)",
    0x42726577: "v4 (APK Signature Scheme v4)",
}


def find_central_directory_offset(data: bytes) -> int | None:
    """Return the offset of the ZIP central directory (EOCD field 16)."""
    # The EOCD record is at the very end, possibly followed by a comment
    # (max 65535 bytes).
    search_from = max(0, len(data) - 65557)
    idx = data.rfind(EOCD_SIGNATURE, search_from)
    if idx < 0 or idx + 22 > len(data):
        return None
    cd_size, cd_offset = struct.unpack_from("<II", data, idx + 12)
    if cd_offset == 0xFFFFFFFF or cd_size == 0xFFFFFFFF:
        # ZIP64: resolve through the ZIP64 end-of-central-directory locator.
        loc = data.rfind(b"PK\x06\x07", search_from)
        if loc < 0 or loc + 20 > len(data):
            return None
        (z64_eocd_offset,) = struct.unpack_from("<Q", data, loc + 8)
        if data[z64_eocd_offset : z64_eocd_offset + 4] != b"PK\x06\x06":
            return None
        _, cd_offset = struct.unpack_from("<QQ", data, z64_eocd_offset + 40)
    return cd_offset


def parse_signing_block(data: bytes, cd_offset: int) -> list[str]:
    """Return the signature schemes declared by the APK Signing Block."""
    if cd_offset < 24 or data[cd_offset - 16 : cd_offset] != APK_SIG_BLOCK_MAGIC:
        return []
    (block_size,) = struct.unpack_from("<Q", data, cd_offset - 24)
    # block_size covers the pairs + the trailing size field + magic,
    # but not the leading size field.
    block_start = cd_offset - block_size - 8
    if block_start < 0 or data[block_start : block_start + 8] != data[cd_offset - 24 : cd_offset - 16]:
        return []
    schemes: list[str] = []
    pos = block_start + 8
    pairs_end = cd_offset - 24
    while pos + 12 <= pairs_end:
        (pair_len,) = struct.unpack_from("<Q", data, pos)
        (pair_id,) = struct.unpack_from("<I", data, pos + 8)
        if pair_len < 4 or pos + 8 + pair_len > pairs_end:
            break
        label = SCHEME_IDS.get(pair_id)
        schemes.append(label if label else f"unknown id 0x{pair_id:08x}")
        pos += 8 + pair_len
    return schemes


def check(path: str) -> int:
    try:
        with open(path, "rb") as handle:
            data = handle.read()
        names = zipfile.ZipFile(path).namelist()
    except Exception as exc:  # noqa: BLE001 - report anything unreadable
        print(f"❌ {path}: cannot be read as an APK/ZIP ({exc})")
        return 2

    sf_files = [n for n in names if n.startswith("META-INF/") and n.upper().endswith(".SF")]
    pk_files = [
        n
        for n in names
        if n.startswith("META-INF/") and n.upper().endswith((".RSA", ".DSA", ".EC"))
    ]
    v1 = bool(sf_files and pk_files)

    cd_offset = find_central_directory_offset(data)
    schemes = parse_signing_block(data, cd_offset) if cd_offset else []
    modern = [s for s in schemes if s.startswith(("v2", "v3"))]

    print(f"===== {path} =====")
    print(f"  v1 (JAR signing): {'YES' if v1 else 'no'}"
          + (f"  [{', '.join(sf_files + pk_files)}]" if v1 else ""))
    print(f"  APK Signing Block: {'YES' if cd_offset and schemes else 'no'}")
    for scheme in schemes:
        print(f"    - {scheme}")

    if v1 or modern:
        print(f"✅ {path}: signature present -> installable "
              f"({'v1' if v1 else ''}{'+ ' if v1 and modern else ''}"
              f"{'/'.join(s.split(' ')[0] for s in modern) if modern else ''})")
        return 0
    print(f"❌ {path}: NO SIGNATURE - Android will reject this APK "
          f"('App not installed' / unsafe app)")
    return 1


def main(argv: list[str]) -> int:
    if len(argv) < 2:
        print(__doc__)
        return 2
    results = [check(path) for path in argv[1:]]
    if any(r == 2 for r in results):
        return 2
    return 1 if any(r != 0 for r in results) else 0


if __name__ == "__main__":
    sys.exit(main(sys.argv))
