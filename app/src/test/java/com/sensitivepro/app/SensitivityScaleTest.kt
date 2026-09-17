package com.sensitivepro.app

import com.sensitivepro.app.data.DeviceDetector
import com.sensitivepro.app.data.PerformanceLevel
import com.sensitivepro.app.data.Sensitivity
import com.sensitivepro.app.data.WeaponRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Free Fire expanded its sensitivity sliders from 0-100 to 0-200. These tests
 * guard that migration: every value the app recommends must be diallable in
 * the game, and the relative ordering that makes the advice useful must hold.
 */
class SensitivityScaleTest {

    @Test
    fun gameScaleMaximumIs200() {
        assertEquals(200, Sensitivity.MAX)
        assertEquals(0, Sensitivity.MIN)
    }

    @Test
    fun everyWeaponStaysInsideTheGameSlider() {
        val weapons = WeaponRepository.getAllWeapons()
        assertEquals(32, weapons.size)
        weapons.forEach { weapon ->
            assertTrue(
                "${weapon.name} out of range: ${weapon.sensitivity.values()}",
                weapon.sensitivity.isWithinGameRange()
            )
        }
    }

    @Test
    fun weaponValuesUseTheNewScaleNotTheOldOne() {
        // Nothing may still sit on the old 0-100 scale: the highest General
        // used to be 93, which on the 0-200 scale is barely half speed.
        val maxGeneral = WeaponRepository.getAllWeapons().maxOf { it.sensitivity.general }
        assertTrue("highest weapon General is $maxGeneral, expected > 100", maxGeneral > 100)
    }

    @Test
    fun deviceProfilesStayInsideTheGameSlider() {
        PerformanceLevel.values().forEach { level ->
            val sensitivity = DeviceDetector.getRecommendedSensitivity(level)
            assertTrue("$level out of range: ${sensitivity.values()}", sensitivity.isWithinGameRange())
            assertTrue("$level General still on the old scale", sensitivity.general > 100)
        }
    }

    @Test
    fun deviceProfilesKeepHighAboveMediumAboveLow() {
        val high = DeviceDetector.getRecommendedSensitivity(PerformanceLevel.HIGH)
        val medium = DeviceDetector.getRecommendedSensitivity(PerformanceLevel.MEDIUM)
        val low = DeviceDetector.getRecommendedSensitivity(PerformanceLevel.LOW)
        assertTrue(high.general > medium.general)
        assertTrue(medium.general > low.general)
    }

    @Test
    fun generatorNeverExceedsTheGameSlider() {
        val styles = listOf("rusher", "balanced", "sniper")
        var cases = 0
        for (screen in listOf(4.5f, 5.9f, 6.4f, 6.7f, 7.2f)) {
            for (ram in listOf(2, 4, 6, 8, 12, 16)) {
                for (dpi in listOf(320, 380, 440, 500, 620)) {
                    for (finger in listOf(2, 3, 4)) {
                        for (style in styles) {
                            val sensitivity =
                                DeviceDetector.generateCustomSensitivity(screen, ram, dpi, finger, style)
                            assertTrue(
                                "screen=$screen ram=$ram dpi=$dpi finger=$finger style=$style -> " +
                                    sensitivity.values(),
                                sensitivity.isWithinGameRange()
                            )
                            cases++
                        }
                    }
                }
            }
        }
        assertEquals(1350, cases)
    }

    @Test
    fun generatorFollowsThePlayStyleOrdering() {
        val rusher = DeviceDetector.generateCustomSensitivity(6.5f, 8, 440, 4, "rusher")
        val balanced = DeviceDetector.generateCustomSensitivity(6.5f, 8, 440, 4, "balanced")
        val sniper = DeviceDetector.generateCustomSensitivity(6.5f, 8, 440, 4, "sniper")
        assertTrue("rusher ${rusher.general} should beat balanced ${balanced.general}", rusher.general > balanced.general)
        assertTrue("balanced ${balanced.general} should beat sniper ${sniper.general}", balanced.general > sniper.general)
    }

    @Test
    fun scopesAlwaysSitBelowGeneral() {
        WeaponRepository.getAllWeapons().forEach { weapon ->
            val s = weapon.sensitivity
            assertTrue("${weapon.name}: 4x ${s.scope4x} should be below 2x ${s.scope2x}", s.scope4x < s.scope2x)
            assertTrue("${weapon.name}: sniper ${s.sniper} should be below 4x ${s.scope4x}", s.sniper < s.scope4x)
        }
        PerformanceLevel.values().forEach { level ->
            val s = DeviceDetector.getRecommendedSensitivity(level)
            assertTrue("$level: sniper ${s.sniper} should be below general ${s.general}", s.sniper < s.general)
        }
    }

    @Test
    fun userFacingTextAdvertisesTheNewScale() {
        PerformanceLevel.values().forEach { level ->
            val tip = DeviceDetector.getTipForLevel(level)
            assertTrue("$level tip should mention the 0-200 scale", tip.contains("0-200"))
        }
        val sensitivity = DeviceDetector.generateCustomSensitivity(6.5f, 8, 440, 4, "balanced")
        val explain = DeviceDetector.getGeneratorExplain(6.5f, 8, 440, 4, "balanced", sensitivity)
        assertTrue("generator explanation should mention the 0-200 scale", explain.contains("0-200"))
    }
}
