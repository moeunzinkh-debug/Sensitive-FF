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

    fun getRecommendedSensitivity(level: PerformanceLevel): Sensitivity {
        return when (level) {
            PerformanceLevel.HIGH -> Sensitivity(
                general = 95, redDot = 92, scope2x = 88, scope4x = 82, sniper = 60, freeLook = 75, drag = 90
            )
            PerformanceLevel.MEDIUM -> Sensitivity(
                general = 88, redDot = 85, scope2x = 80, scope4x = 75, sniper = 55, freeLook = 70, drag = 84
            )
            PerformanceLevel.LOW -> Sensitivity(
                general = 80, redDot = 78, scope2x = 72, scope4x = 68, sniper = 50, freeLook = 65, drag = 78
            )
        }
    }

    fun getTipForLevel(level: PerformanceLevel): String {
        return when (level) {
            PerformanceLevel.HIGH -> "🔥 ទូរស័ព្ទអ្នកខ្លាំង! ប្រើ General 95, Red Dot 92 ដើម្បីអូសលឿន។ បើប្រើ 90Hz/120Hz អេក្រង់នឹងរលូនខ្លាំង។ គួរបើក High FPS ក្នុង Free Fire។ កុំភ្លេចបើក DPI ខ្ពស់បន្តិច (500+) ដើម្បីអូសកាន់តែលឿន។"
            PerformanceLevel.MEDIUM -> "⚖️ ទូរស័ព្ទកម្រិតមធ្យម ប្រើ General 88, Red Dot 85 ល្អបំផុត។ កុំដាក់ខ្ពស់ពេក បើមិនចឹងនឹងកន្ត្រាក់។ បិទ Background App ដើម្បីលេងបានរលូន។ ប្រើ Graphic Smooth ដើម្បី FPS មានស្ថេរភាព។"
            PerformanceLevel.LOW -> "🛡️ ទូរស័ព្ទខ្សោយ ត្រូវប្រើ General 80, Red Dot 78 ទាបជាង ដើម្បីទប់កាំភ្លើងបាន។ បិទ Graphic ទាបបំផុត បិទ Shadow និង High FPS។ កុំបើក App ច្រើនពេលលេង។ ដាក់ DPI 380-420 ល្មម។"
        }
    }

    // Generator logic: create sensitivity based on inputs
    fun generateCustomSensitivity(
        screenInches: Float,
        ramGB: Int,
        dpi: Int,
        fingerCount: Int, // 2,3,4
        style: String // rusher, balanced, sniper
    ): Sensitivity {
        var baseGeneral = 85
        // Screen size: larger screen needs lower sensitivity (more distance to drag)
        baseGeneral += when {
            screenInches < 6.0f -> 6
            screenInches < 6.5f -> 3
            screenInches < 6.8f -> 0
            else -> -4
        }
        // RAM
        baseGeneral += when {
            ramGB >= 12 -> 6
            ramGB >= 8 -> 3
            ramGB >= 6 -> 0
            ramGB >= 4 -> -3
            else -> -6
        }
        // DPI
        baseGeneral += when {
            dpi >= 500 -> 4
            dpi >= 440 -> 2
            dpi >= 380 -> 0
            else -> -3
        }
        // Finger count: more fingers = can control higher sensitivity
        baseGeneral += when (fingerCount) {
            4 -> 4
            3 -> 2
            else -> 0
        }
        // Style
        baseGeneral += when (style) {
            "rusher" -> 5
            "sniper" -> -6
            else -> 0
        }

        baseGeneral = baseGeneral.coerceIn(72, 100)
        return Sensitivity(
            general = baseGeneral,
            redDot = (baseGeneral - 3).coerceIn(68, 98),
            scope2x = (baseGeneral - 8).coerceIn(64, 94),
            scope4x = (baseGeneral - 14).coerceIn(60, 90),
            sniper = (baseGeneral - 32).coerceIn(42, 68),
            freeLook = (baseGeneral - 18).coerceIn(55, 82),
            drag = when (style) {
                "rusher" -> (baseGeneral + 2).coerceIn(74, 100)
                "sniper" -> (baseGeneral - 4).coerceIn(70, 94)
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
            💡 គន្លឹះ៖ ចម្លងការកំណត់នេះទៅ Free Fire > Settings > Sensitivity ហើយសាកល្បងក្នុង Training Ground 10-15 នាទី។ បើអូសលើសក្បាល បន្ថយ 2-3 ពិន្ទុ បើអូសមិនដល់ បង្កើន 2-3 ពិន្ទុ។
        """.trimIndent()
    }
}
