package com.sensitivepro.app.data

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

object DeviceDetector {

    fun getDeviceInfo(context: Context): DeviceInfo {
        val model = Build.MODEL ?: "Unknown"
        val manufacturer = Build.MANUFACTURER?.replaceFirstChar { it.uppercase() } ?: "Unknown"
        val androidVersion = "Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT})"
        val ramGB = getTotalRamGB(context)
        val cpuInfo = getCpuInfo()
        val (screenSize, dpi, refreshRate) = getScreenInfo(context)
        val level = determinePerformance(ramGB, refreshRate, dpi)
        return DeviceInfo(model, manufacturer, androidVersion, ramGB, cpuInfo, screenSize, refreshRate, dpi, level)
    }

    private fun getTotalRamGB(context: Context): Double {
        return try {
            val actManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val memInfo = ActivityManager.MemoryInfo()
            actManager.getMemoryInfo(memInfo)
            val totalBytes = memInfo.totalMem
            // Round to nearest GB
            (totalBytes / (1024.0 * 1024.0 * 1024.0) * 10).toInt() / 10.0
        } catch (e: Exception) {
            4.0
        }
    }

    private fun getCpuInfo(): String {
        return try {
            val abi = Build.SUPPORTED_ABIS.firstOrNull() ?: "Unknown"
            val hardware = Build.HARDWARE ?: ""
            when {
                abi.contains("arm64") -> "Octa-core ($abi)"
                abi.contains("armeabi") -> "Quad-core ($abi)"
                else -> "$abi $hardware".trim()
            }
        } catch (e: Exception) {
            Build.HARDWARE ?: "Unknown"
        } + " • ${Build.BOARD}"
    }

    private fun getScreenInfo(context: Context): Triple<String, Int, Float> {
        return try {
            val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val metrics = DisplayMetrics()
            @Suppress("DEPRECATION")
            wm.defaultDisplay.getMetrics(metrics)
            val widthPx = metrics.widthPixels
            val heightPx = metrics.heightPixels
            val dpi = metrics.densityDpi
            val density = metrics.density
            // Approximate screen size in inches
            val widthInches = widthPx / (dpi.toDouble())
            val heightInches = heightPx / (dpi.toDouble())
            val size = Math.sqrt(widthInches * widthInches + heightInches * heightInches)
            val sizeStr = String.format("%.1f\" (%d x %d)", size, widthPx, heightPx)
            val refresh = try {
                @Suppress("DEPRECATION")
                wm.defaultDisplay.refreshRate
            } catch (e: Exception) { 60f }

            Triple(sizeStr, dpi, refresh)
        } catch (e: Exception) {
            Triple("6.5\" (1080 x 2400)", 420, 60f)
        }
    }

    private fun determinePerformance(ram: Double, refresh: Float, dpi: Int): PerformanceLevel {
        var score = 0
        if (ram >= 8) score += 3 else if (ram >= 6) score += 2 else if (ram >= 4) score += 1
        if (refresh >= 90) score += 2 else if (refresh >= 60) score += 1
        if (dpi >= 400) score += 1
        // Also check CPU cores approximated via available processors
        val cores = Runtime.getRuntime().availableProcessors()
        if (cores >= 8) score += 2 else if (cores >= 6) score += 1

        return when {
            score >= 6 -> PerformanceLevel.HIGH
            score >= 3 -> PerformanceLevel.MEDIUM
            else -> PerformanceLevel.LOW
        }
    }

    /**
     * Recommended sensitivity on Free Fire's **0-200 scale**.
     *
     * Garena expanded the in-game sensitivity sliders from 0-100 to 0-200, so a
     * value of 100 on the new scale is only half as fast as the old maximum.
     * The values below keep the same intent as the previous 0-100 profile
     * (near-maximum for strong phones) and stay inside the ranges the community
     * publishes for the 0-200 scale: General 145-200, Red Dot 135-198,
     * 2x 130-190, 4x 115-175, Sniper 65-140, Free Look 55-200.
     */
    fun getRecommendedSensitivity(level: PerformanceLevel): Sensitivity {
        return when (level) {
            PerformanceLevel.HIGH -> Sensitivity(
                general = 190, redDot = 184, scope2x = 176, scope4x = 164, sniper = 120, freeLook = 150, drag = 180
            )
            PerformanceLevel.MEDIUM -> Sensitivity(
                general = 176, redDot = 170, scope2x = 160, scope4x = 150, sniper = 110, freeLook = 140, drag = 168
            )
            PerformanceLevel.LOW -> Sensitivity(
                general = 160, redDot = 156, scope2x = 144, scope4x = 136, sniper = 100, freeLook = 130, drag = 156
            )
        }
    }

    fun getTipForLevel(level: PerformanceLevel): String {
        return when (level) {
            PerformanceLevel.HIGH -> "🔥 ទូរស័ព្ទអ្នកខ្លាំង! ប្រើ General 190, Red Dot 184 ដើម្បីអូសលឿន។ បើប្រើ 90Hz/120Hz អេក្រង់នឹងរលូនខ្លាំង។ គួរបើក High FPS ក្នុង Free Fire។ កុំភ្លេចបើក DPI ខ្ពស់បន្តិច (500+) ដើម្បីអូសកាន់តែលឿន។\n\n📏 Free Fire ឥឡូវប្រើមាត្រដ្ឋាន 0-200 (មិនមែន 0-100 ទេ)។ បើអ្នកនៅដាក់ 95 ដូចសម័យមុន ការអូសនឹងយឺតជាងមុនពាក់កណ្តាល។"
            PerformanceLevel.MEDIUM -> "⚖️ ទូរស័ព្ទកម្រិតមធ្យម ប្រើ General 176, Red Dot 170 ល្អបំផុត។ កុំដាក់ខ្ពស់ពេក បើមិនចឹងនឹងកន្ត្រាក់។ បិទ Background App ដើម្បីលេងបានរលូន។ ប្រើ Graphic Smooth ដើម្បី FPS មានស្ថេរភាព។\n\n📏 Free Fire ឥឡូវប្រើមាត្រដ្ឋាន 0-200 (មិនមែន 0-100 ទេ)។ បើអ្នកនៅដាក់ 88 ដូចសម័យមុន ការអូសនឹងយឺតជាងមុនពាក់កណ្តាល។"
            PerformanceLevel.LOW -> "🛡️ ទូរស័ព្ទខ្សោយ ត្រូវប្រើ General 160, Red Dot 156 ទាបជាង ដើម្បីទប់កាំភ្លើងបាន។ បិទ Graphic ទាបបំផុត បិទ Shadow និង High FPS។ កុំបើក App ច្រើនពេលលេង។ ដាក់ DPI 380-420 ល្មម។\n\n📏 Free Fire ឥឡូវប្រើមាត្រដ្ឋាន 0-200 (មិនមែន 0-100 ទេ)។ បើអ្នកនៅដាក់ 80 ដូចសម័យមុន ការអូសនឹងយឺតជាងមុនពាក់កណ្តាល។"
        }
    }

    // Generator logic: create sensitivity based on inputs.
    // All weights and clamps are on Free Fire's 0-200 scale (the old 0-100
    // values were doubled: base 85 -> 170, each +/- step x2, caps x2).
    fun generateCustomSensitivity(
        screenInches: Float,
        ramGB: Int,
        dpi: Int,
        fingerCount: Int, // 2,3,4
        style: String // rusher, balanced, sniper
    ): Sensitivity {
        var baseGeneral = 170
        // Screen size: larger screen needs lower sensitivity (more distance to drag)
        baseGeneral += when {
            screenInches < 6.0f -> 12
            screenInches < 6.5f -> 6
            screenInches < 6.8f -> 0
            else -> -8
        }
        // RAM
        baseGeneral += when {
            ramGB >= 12 -> 12
            ramGB >= 8 -> 6
            ramGB >= 6 -> 0
            ramGB >= 4 -> -6
            else -> -12
        }
        // DPI
        baseGeneral += when {
            dpi >= 500 -> 8
            dpi >= 440 -> 4
            dpi >= 380 -> 0
            else -> -6
        }
        // Finger count: more fingers = can control higher sensitivity
        baseGeneral += when (fingerCount) {
            4 -> 8
            3 -> 4
            else -> 0
        }
        // Style
        baseGeneral += when (style) {
            "rusher" -> 10
            "sniper" -> -12
            else -> 0
        }

        // Worst case the weights can reach 170+12+12+8+8+10 = 220, so the
        // clamp below is what keeps every value inside the in-game slider.
        baseGeneral = baseGeneral.coerceIn(144, Sensitivity.MAX)
        return Sensitivity(
            general = baseGeneral,
            redDot = (baseGeneral - 6).coerceIn(136, 196),
            scope2x = (baseGeneral - 16).coerceIn(128, 188),
            scope4x = (baseGeneral - 28).coerceIn(120, 180),
            sniper = (baseGeneral - 64).coerceIn(84, 136),
            freeLook = (baseGeneral - 36).coerceIn(110, 164),
            drag = when (style) {
                "rusher" -> (baseGeneral + 4).coerceIn(148, Sensitivity.MAX)
                "sniper" -> (baseGeneral - 8).coerceIn(140, 188)
                else -> baseGeneral
            }
        )
    }

    fun getGeneratorExplain(
        screen: Float, ram: Int, dpi: Int, finger: Int, style: String, sens: Sensitivity
    ): String {
        val fingerKh = when (finger) { 4 -> "4 ម្រាម (Claw)" ; 3 -> "3 ម្រាម" ; else -> "2 ម្រាម (មេដៃ)" }
        val styleKh = when (style) { "rusher" -> "វាយលុក Rusher" ; "sniper" -> "បាញ់ចម្ងាយឆ្ងាយ" ; else -> "មានតុល្យភាព" }
        return """
            📱 ទំហំអេក្រង់ ${screen} អ៊ីញ ${if (screen > 6.7) "ធំ ត្រូវការសែនស៊ីធីវទាបជាង" else if (screen < 6.1) "តូច អូសតិចក៏ដល់" else "ល្មម"} 
            💾 RAM ${ram}GB ${if (ram >= 8) "ខ្លាំង អាចប្រើខ្ពស់បាន" else if (ram >= 6) "ល្មម" else "ខ្សោយ គួរប្រើទាប"}
            🎯 DPI ${dpi} ${if (dpi >= 480) "ខ្ពស់ អូសលឿន" else "ល្មម"}
            👆 ${fingerKh} ${if (finger == 4) "Control បានល្អ អាចប្រើខ្ពស់" else ""}
            ⚔️ រចនាប័ទ្ម ${styleKh}
            
            👉 លទ្ធផល General ${sens.general} គឺល្អបំផុតសម្រាប់អ្នក។ 
            ${if (style == "rusher") "អ្នកជា Rusher គួរប្រើខ្ពស់ ដើម្បីបត់លឿន និងអូសក្បាលលឿន។" else if (style == "sniper") "អ្នកជា Sniper គួរប្រើទាប ដើម្បីបាញ់ចំ 100% ពេលប្រើ Scope។" else "តុល្យភាពល្អ សម្រាប់ទាំង Rush និងបាញ់ឆ្ងាយ។"}
            📏 មាត្រដ្ឋាន Free Fire ឥឡូវគឺ 0-200 (ពីមុន 0-100)។
            💡 គន្លឹះ៖ ចម្លងការកំណត់នេះទៅ Free Fire > Settings > Sensitivity ហើយសាកល្បងក្នុង Training Ground 10-15 នាទី។ បើអូសលើសក្បាល បន្ថយ 4-6 ពិន្ទុ បើអូសមិនដល់ បង្កើន 4-6 ពិន្ទុ។
        """.trimIndent()
    }
}
