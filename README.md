# Sensitive Pro - សែនស៊ីធីវ ប្រូ 🎯

កម្មវិធីកំណត់សែនស៊ីធីវ (Sensitivity) ល្អបំផុតសម្រាប់ **Free Fire** សម្រាប់សហគមន៍ខ្មែរ 🇰🇭

> **App Name:** Sensitive Pro  
> **Package:** `com.sensitivepro.app`  
> **Version:** 1.0.0 (1)  
> **Min SDK:** 24 (Android 7.0) | Target SDK 34 | Compile SDK 34

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
# APK: app/build/outputs/apk/release/app-release-unsigned.apk
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
