package com.sensitivepro.app.data

data class Sensitivity(
    val general: Int,
    val redDot: Int,
    val scope2x: Int,
    val scope4x: Int,
    val sniper: Int,
    val freeLook: Int,
    val drag: Int
) {
    /** Every value, in the order Free Fire lists them in the Sensitivity menu. */
    fun values(): List<Int> = listOf(general, redDot, scope2x, scope4x, sniper, freeLook, drag)

    /** True when every value can actually be dialled into the game's sliders. */
    fun isWithinGameRange(): Boolean = values().all { it in MIN..MAX }

    companion object {
        /**
         * Free Fire's sensitivity sliders run from 0 to 200.
         *
         * This is an official Garena change, not a tuning preference. From the
         * Free Fire patch notes (ff.garena.com/en/article/1332/):
         * "Increased the sensitivity cap to 200. Players can now adjust their
         * sensitivity settings within a greater range."
         *
         * A value of 100 therefore is only about half the available camera
         * speed; recommendations that were written for the old 0-100 cap are
         * obsolete.
         */
        const val MAX = 200
        const val MIN = 0
    }
}

enum class WeaponCategory(val displayKh: String, val displayEn: String, val colorRes: String, val icon: String) {
    SMG("កាំភ្លើងខ្លីបាញ់លឿន", "SMG", "#FF6B00", "🔫"),
    SHOTGUN("កាំភ្លើងបាញ់គ្រាប់ធំ", "Shotgun", "#FF1744", "💥"),
    RIFLE("កាំភ្លើងវែង", "Rifle", "#00B0FF", "🎯"),
    MARKSMAN("កាំភ្លើងបាញ់ចម្ងាយឆ្ងាយ", "Marksman", "#00E676", "🔭")
}

data class Weapon(
    val id: String,
    val name: String,
    val category: WeaponCategory,
    val sensitivity: Sensitivity,
    val descriptionKh: String,
    val tipsKh: String,
    val difficulty: String,
    val fireRate: String
)

data class DeviceInfo(
    val model: String,
    val manufacturer: String,
    val androidVersion: String,
    val ramGB: Double,
    val cpuInfo: String,
    val screenSize: String,
    val refreshRate: Float,
    val dpi: Int,
    val performanceLevel: PerformanceLevel
)

enum class PerformanceLevel(val kh: String, val en: String, val explainKh: String) {
    HIGH(
        "កម្រិតខ្ពស់ - ទូរស័ព្ទខ្លាំង",
        "High-End",
        "ទូរស័ព្ទរបស់អ្នកខ្លាំង អាចប្រើសែនស៊ីធីវខ្ពស់បាន ដើម្បីអូសលឿន និងបាញ់ក្បាលបានងាយ។ អេក្រង់ 90Hz/120Hz នឹងជួយឲ្យការអូសរលូនជាង។"
    ),
    MEDIUM(
        "កម្រិតមធ្យម - ទូរស័ព្ទធម្មតា",
        "Mid-Range",
        "ទូរស័ព្ទកម្រិតមធ្យម គួរប្រើសែនស៊ីធីវមធ្យម ដើម្បីរក្សាលំនឹង និងកាត់បន្ថយការរអាក់រអួល។ កុំដាក់ខ្ពស់ពេក ប្រយ័ត្នកន្ត្រាក់។"
    ),
    LOW(
        "កម្រិតទាប - ទូរស័ព្ទខ្សោយ",
        "Low-End",
        "ទូរស័ព្ទកម្រិតទាប គួរប្រើសែនស៊ីធីវទាបជាង ដើម្បីជៀសវាងការកន្ត្រាក់ និងរក្សាភាពរលូន។ បិទ Graphic ខ្ពស់ដើម្បីលេងបានស្រួល។"
    )
}
