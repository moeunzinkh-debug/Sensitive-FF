# Sensitive Pro - សែនស៊ីធីវ ប្រូ 🎯

កម្មវិធីកំណត់សែនស៊ីធីវ (Sensitivity) ល្អបំផុតសម្រាប់ **Free Fire** សម្រាប់សហគមន៍ខ្មែរ 🇰🇭

> **App Name:** Sensitive Pro  
> **Package:** `com.sensitivepro.app`  
> **Version:** 1.1.0 (3)  
> **Sensitivity scale:** **0-200** (Free Fire បានប្តូរពី 0-100)  
> **Min SDK:** 24 (Android 7.0) | Target SDK 34 | Compile SDK 34  
> **Signing:** Release APK ចុះហត្ថលេខា **v2 + v3** (បញ្ជាក់ដោយ `apksigner verify` ក្នុង CI) → ដំឡើងបាន

---

## ✨ ដំណើរការរបស់វា | មុខងារ

### 📱 1. Auto Detectives Device Phone - វិភាគទូរស័ព្ទដោយស្វ័យប្រវត្តិ
- ស្គេន **Model, Manufacturer, Android Version, RAM, CPU, Screen Size, Refresh Rate, DPI** ដោយស្វ័យប្រវត្តិ
- វាយតម្លៃកម្រិតដំណើរការ: **ខ្ពស់ / មធ្យម / ទាប**
- ណែនាំសែនស៊ីធីវ 6 ប្រភេទ: `General`, `Red Dot`, `2x Scope`, `4x Scope`, `Sniper Scope`, `Free Look`
- ពន្យល់ជាភាសាខ្មែរ + គន្លឹះតាមកម្រិតទូរស័ព្ទ
- ចម្លងការកំណត់មួយចុច

### ⚡ 2. Generate Sensitive Drought Model Phone - បង្កើតម៉ូដែលសែនស៊ីធីវអូស
- បញ្ចូល **ទំហំអេក្រង់, RAM, DPI, វិធីអូស (2/3/4 ម្រាម), រចនាប័ទ្មលេង (Rusher/Balanced/Sniper)**
- គណនា Sensitivity ដោយស្វ័យប្រវត្តិតាមរូបមន្ត
- បង្ហាញលទ្ធផល 6 តម្លៃ + ពន្យល់លម្អិតជាភាសាខ្មែរ
- ចម្លង / ចែករំលែក

### 🔫 3. Sensitive Weapon - សែនស៊ីធីវតាមអាវុធនីមួយៗ

#### SMG - កាំភ្លើងខ្លីបាញ់លឿន (9)
`MP40`, `MP5`, `UMP`, `MAC10`, `P90`, `Bizon`, `Thompson`, `Vector`, `CG15`

#### Shotgun - កាំភ្លើងបាញ់គ្រាប់ធំ (6)
`M1887`, `M1014`, `Mag-7`, `Trogon`, `M590`, `SPAS12`

#### Rifle - កាំភ្លើងវែង (10)
`Plasma`, `G36`, `AUG`, `XM8`, `AK47`, `M4A1`, `Parafal`, `SCAR`, `Groza`, `FAMAS`

#### Marksman Rifle - កាំភ្លើងបាញ់ចម្ងាយឆ្ងាយ (7)
`AC80`, `Winchester`, `SKS`, `M14`, `Woodpecker`, `SVD`, `M28B`

> សរុប **32 អាវុធ** មាន `General / Red Dot / 2x / 4x / Sniper / Drag` + ពន្យល់ + គន្លឹះជាភាសាខ្មែរគ្រប់អាវុធ

- ស្វែងរកអាវុធ
- Tab បែងចែកប្រភេទ
- ចម្លងសែនស៊ីធីវអាវុធនីមួយៗ

---

## 🖼️ រូបភាព App

- Dark Theme + Gradient Orange/Red (Free Fire Style)
- Material 3 + CardView + Bottom Navigation
- ភាសាខ្មែរ 100% សម្រាប់ការពន្យល់

---

## 📏 មាត្រដ្ឋានសែនស៊ីធីវ 0-200 (ការធ្វើបច្ចុប្បន្នភាព v1.1.0)

Garena បានពង្រីក slider សែនស៊ីធីវក្នុង Free Fire ពី **0-100** ទៅ **0-200**។ នេះជាការផ្លាស់ប្តូរផ្លូវការ មិនមែនជាការកំណត់ផ្ទាល់ខ្លួនទេ — ពី **Free Fire Patch Notes** ផ្លូវការរបស់ Garena៖

> "Increased the sensitivity cap to 200. Players can now adjust their sensitivity settings within a greater range."  
> — https://ff.garena.com/en/article/1332/

ដូច្នេះតម្លៃចាស់ `100` ឥឡូវមានល្បឿនត្រឹមតែ **ពាក់កណ្តាល** នៃអតិបរមាថ្មី។ កម្មវិធីនេះបានប្តូរទៅមាត្រដ្ឋាន 0-200 ទាំងស្រុងហើយ។

### Device profile ថ្មី (0-200)

| កម្រិតទូរស័ព្ទ | General | Red Dot | 2x | 4x | Sniper | Free Look | Drag |
|---|---|---|---|---|---|---|---|
| ខ្ពស់ (HIGH) | 190 | 184 | 176 | 164 | 120 | 150 | 180 |
| មធ្យម (MEDIUM) | 176 | 170 | 160 | 150 | 110 | 140 | 168 |
| ទាប (LOW) | 160 | 156 | 144 | 136 | 100 | 130 | 156 |

### អាវុធ ៣២

តម្លៃអាវុធទាំងអស់ត្រូវបានគុណនឹង ២ (ឧ. MP40 `92 → 184`, MAC10 `93 → 186`, M1887 `78 → 156`)។ ជួរថ្មីសរុបគឺ **88-186** ស្ថិតក្នុងចន្លោះដែលសហគមន៍ប្រើសម្រាប់មាត្រដ្ឋាន 0-200 (General 145-200, Red Dot 135-198, 2x 130-190, 4x 115-175, Sniper 65-140, Free Look 55-200)។

### Generator

រូបមន្តត្រូវបានប្តូរទៅ 0-200 ដែរ (base `85 → 170`, រាល់ជំហាន +/- គុណ ២) ហើយគ្រប់តម្លៃត្រូវបាន clamp ដោយ `coerceIn(..., Sensitivity.MAX)`។ ករណីធ្ងន់ធ្ងរបំផុតអាចឡើងដល់ 220 មុន clamp ដូច្នេះការ clamp នេះចាំបាច់។

### ការកែសម្រួល

លើមាត្រដ្ឋាន 0-200 សូមកែ **4-6 ពិន្ទុ** ម្តង (ពីមុន 2-3) ព្រោះឥឡូវមាន 200 កម្រិត។

### 🧪 ការផ្ទៀងផ្ទាត់ស្វ័យប្រវត្តិ

`app/src/test/java/com/sensitivepro/app/SensitivityScaleTest.kt` ត្រួតពិនិត្យថា៖
- អាវុធ ៣២ និង device profile ទាំង ៣ ស្ថិតក្នុង `0..200`
- Generator មិនលើសពី 200 សម្រាប់បន្សំធាតុចូល **1350** ករណី
- Rusher > Balanced > Sniper និង Sniper Scope < 4x < 2x < General
- អត្ថបទខ្មែរដែលបង្ហាញអ្នកប្រើមានពាក្យ "0-200"

រត់ដោយ GitHub Actions: `./gradlew testDebugUnitTest`

---

## 🚨 ដោះស្រាយបញ្ហា "មិនមានសុវត្ថិភាព / Failed install"

> **រោគវិនិច្ឆ័យ (អ្វីដែលខុសពីមុន):** ឯកសារ `app/build.gradle.kts` **មិនមាន `signingConfig`** សម្រាប់ build type `release` ទេ។
> ដូច្នេះ `./gradlew assembleRelease` បង្កើតឯកសារ **`app-release-unsigned.apk`** (APK គ្មានហត្ថលេខា)។
> Android **មិនអនុញ្ញាតឲ្យដំឡើង APK គ្មានហត្ថលេខាទេ** — Package Manager បដិសេធភ្លាមៗ (`INSTALL_PARSE_FAILED_NO_CERTIFICATES`
> ឬ `INSTALL_FAILED_INVALID_APK`) ហើយអេក្រង់បង្ហាញ **"App not installed" / "failed install"**។
> ដូចគ្នាដែរ Play Protect / Samsung / MIUI ស្កេនឃើញថាគ្មាន certificate ដែលអាចទុកចិត្តបាន
> ទើបព្រមាន **"មិនមានសុវត្ថិភាព" (unsafe)**។ ការចុច "បង្ខំដំឡើង" ក៏មិនអាចជោគជ័យ ព្រោះវាមិនមែនជាការព្រមានទេ — វាជាការបដិសេធពីប្រព័ន្ធ។

### ✅ អ្វីដែលបានជួសជុល

| ឯកសារ | ការផ្លាស់ប្តូរ |
|---|---|
| `app/build.gradle.kts` | បន្ថែម `signingConfigs { create("release") }` + `buildTypes.release.signingConfig` + បើក **v2 + v3 signing** (v2 គ្រប់គ្រាន់សម្រាប់ Android 7.0 / API 24 ឡើងទៅ) |
| `keystore.properties.example` | គំរូឯកសារ keystore (ចម្លងទៅ `keystore.properties`) |
| `.github/workflows/build-apk.yml` | បង្កើត/ប្រើ keystore មុន build + ជំហាន **`apksigner verify`** ដើម្បីបញ្ជាក់ថា APK មានហត្ថលេខាពិត |
| `.gitignore` | ទប់ស្កាត់ការ commit `*.p12`, `keystore.properties` (ពាក្យសម្ងាត់) |

### 🧪 លទ្ធផលបញ្ជាក់ (GitHub Actions — `apksigner verify`)

```
app-release.apk signature verified -> Verifies
Verified using v1 scheme (JAR signing): false
Verified using v2 scheme (APK Signature Scheme v2): true
Verified using v3 scheme (APK Signature Scheme v3): true
```

> ពាក្យ **"Verifies"** មានន័យថា APK មានហត្ថលេខាត្រឹមត្រូវ ហើយ Android នឹងអនុញ្ញាតឲ្យដំឡើង។
> v1 = false មិនមែនជាបញ្ហាទេ ព្រោះ **v2 មានចាប់ពី Android 7.0 (API 24)** ដែលជា minSdk របស់កម្មវិធីនេះ។

### 🔑 របៀបបង្កើត Keystore (ធ្វើម្តង ប្រើបានរហូត)

```bash
keytool -genkeypair -v \
  -keystore sensitive-pro.p12 \
  -storetype PKCS12 \
  -keyalg RSA -keysize 2048 -validity 10950 \
  -alias sensitive-pro \
  -storepass ពាក្យសម្ងាត់របស់អ្នក \
  -dname "CN=Sensitive Pro, OU=Mobile, O=Sensitive Pro, L=Phnom Penh, C=KH"

cp keystore.properties.example keystore.properties   # កែ storePassword / keyPassword
./gradlew assembleRelease
# APK ដែលដំឡើងបាន: app/build/outputs/apk/release/app-release.apk
```

> ⚠️ **សំខាន់:** រក្សាទុក `sensitive-pro.p12` ឲ្យបានល្អ។ ការ update កម្មវិធីត្រូវតែចុះហត្ថលេខា
> ដោយ **key ដដែល** បើមិនដូច្នោះទេ Android នឹងបដិសេធ (`INSTALL_FAILED_UPDATE_INCOMPATIBLE`)។
> កុំ commit ឯកសារ keystore ឬពាក្យសម្ងាត់ចូល Git។

### 🤖 សម្រាប់ GitHub Actions (ឲ្យ APK មាន key ថេរ)

បន្ថែម **Settings → Secrets and variables → Actions → New repository secret**:

| Secret | តម្លៃ |
|---|---|
| `RELEASE_KEYSTORE_BASE64` | `base64 -w 0 sensitive-pro.p12` |
| `RELEASE_STORE_PASSWORD` | ពាក្យសម្ងាត់ keystore |
| `RELEASE_KEY_ALIAS` | `sensitive-pro` |
| `RELEASE_KEY_PASSWORD` | ពាក្យសម្ងាត់ key |

បើមិនមាន secret ទេ workflow នឹងបង្កើត keystore បណ្តោះអាសន្នសម្រាប់ run នោះ —
APK នៅតែ **មានហត្ថលេខា និងដំឡើងបាន** ប៉ុន្តែត្រូវ **Uninstall កម្មវិធីចាស់ជាមុនសិន** បើវាចុះហត្ថលេខាដោយ key ផ្សេង។

### 📲 បើទូរស័ព្ទនៅតែព្រមាន "App not safe" (បន្ទាប់ពី APK មានហត្ថលេខាហើយ)

នេះជាការព្រមានធម្មតាសម្រាប់កម្មវិធីដែលមិនមកពី Play Store (sideload) — វា **មិនមែន** បញ្ហា "failed install" ទៀតទេ៖

- **ទូរស័ព្ទទូទៅ:** Settings → Security → **Install unknown apps** → អនុញ្ញាតឲ្យ Chrome / Files → ចុច **Install anyway**
- **Samsung:** Settings → Biometrics and security → Install unknown apps → អនុញ្ញាត → បើ Play Protect រាំងខ្ទប់ ចុច **More details → Install anyway**
- **Xiaomi / MIUI:** Developer options → បើក **Install via USB** + **USB debugging** (បើត្រូវការ) និងបិទ **MIUI optimization** បើមានបញ្ហា
- **បញ្ហា "App not installed" នៅតែមាន:** Uninstall កម្មវិធីចាស់សិន (signature ខុសគ្នា) ឬពិនិត្យថាទូរស័ព្ទមាន Android 7.0 (API 24) ឡើងទៅ និងមានទំហំទំនេរគ្រប់គ្រាន់

---

## 🛠️ Build APK

### GitHub Actions (Workflow Build APK)
Workflow: `.github/workflows/build-apk.yml`

```yaml
on: [push, pull_request, workflow_dispatch]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - setup JDK 17 + Android SDK
      - ./gradlew assembleDebug
      - upload artifact Sensitive-Pro-debug-apk
```

**របៀប Build:**
1. Push ទៅ `main` ឬ `arena/**`
2. ឬចុច **Run workflow** ក្នុង Tab Actions
3. ទាញយក APK ពី Artifacts

### Local Build
```bash
./gradlew assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
./gradlew assembleRelease
# APK ដែលដំឡើងបាន (មាន keystore): app/build/outputs/apk/release/app-release.apk
# បើគ្មាន keystore: app-release-unsigned.apk  → ដំឡើងមិនបានទេ
```

---

## 📂 រចនាសម្ព័ន្ធ

```
app/src/main/
├── AndroidManifest.xml
├── java/com/sensitivepro/app/
│   ├── MainActivity.kt
│   ├── data/
│   │   ├── Weapon.kt
│   │   ├── WeaponRepository.kt (32 អាវុធ)
│   │   └── DeviceDetector.kt (Auto Detect + Generator)
│   └── ui/
│       ├── adapters/WeaponAdapter.kt
│       └── fragments/
│           ├── HomeFragment.kt
│           ├── DeviceFragment.kt
│           ├── WeaponsFragment.kt
│           └── GeneratorFragment.kt
└── res/
    ├── layout/
    ├── values/ (colors, themes, strings_kh)
    └── drawable/ (gradient, icons)
```

---

## 🇰🇭 ពន្យល់ជាភាសាខ្មែរ

កម្មវិធីនេះជួយអ្នកកំណត់សែនស៊ីធីវឲ្យត្រូវនឹងទូរស័ព្ទ និងអាវុធនីមួយៗ ដើម្បីបាញ់ក្បាលបានច្រើន និងអូសបានរលូន។

- **សែនស៊ីធីវទូទៅ (General):** ល្បឿនបង្វិលអេក្រង់ទូទៅ
- **Red Dot:** ពេលប្រើ Red Dot Sight
- **2x/4x Scope:** ពេលប្រើ Scope
- **Sniper Scope:** ពេលប្រើ Sniper
- **Free Look:** ពេលមើលជុំវិញ
- **Drag:** ល្បឿនអូសសម្រាប់ Headshot

> 💡 គន្លឹះ: កំណត់ហើយសាកក្នុង Training Ground 10-15 នាទី បើអូសលើសក្បាល បន្ថយ 2-3, បើអូសមិនដល់ បង្កើន 2-3។

---

## 📄 License
MIT - សម្រាប់សហគមន៍ Free Fire កម្ពុជា

**Made with ❤️ for Khmer Community 🇰🇭**
