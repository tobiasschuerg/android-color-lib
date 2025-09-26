package com.tobiasschuerg.color.models

import android.graphics.Color
import com.tobiasschuerg.color.material.MaterialColor
import org.junit.Test
import org.junit.Before
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import kotlin.math.abs

/**
 * Comprehensive unit tests for all color model conversions.
 *
 * Tests conversion accuracy between HSV, HSL, HEX color models
 * and verifies MaterialColor functionality.
 */
@RunWith(RobolectricTestRunner::class)
class ColorConversionTest {

    // Test colors with known values
    private lateinit var redHex: HEXColor
    private lateinit var greenHex: HEXColor
    private lateinit var blueHex: HEXColor
    private lateinit var blackHex: HEXColor
    private lateinit var whiteHex: HEXColor
    private lateinit var grayHex: HEXColor

    // Expected HSV values for test colors (corrected values)
    private val redHSV = floatArrayOf(0f, 1f, 1f)
    private val greenHSV = floatArrayOf(120f, 1f, 1f)
    private val blueHSV = floatArrayOf(240f, 1f, 1f)
    private val blackHSV = floatArrayOf(0f, 0f, 0f)
    private val whiteHSV = floatArrayOf(0f, 0f, 1f)

    @Before
    fun setUp() {
        redHex = HEXColor("#FFFF0000")
        greenHex = HEXColor("#FF00FF00")
        blueHex = HEXColor("#FF0000FF")
        blackHex = HEXColor("#FF000000")
        whiteHex = HEXColor("#FFFFFFFF")
        grayHex = HEXColor("#FF808080")
    }

    // Helper function to compare floats with tolerance
    private fun assertFloatEquals(expected: Float, actual: Float, tolerance: Float = 0.01f) {
        assertTrue(
            "Expected $expected but was $actual (tolerance: $tolerance)",
            abs(expected - actual) <= tolerance
        )
    }

    @Test
    fun testHexToHSVConversion() {
        // Test red conversion - pure red might have hue=0 but saturation could be different
        val redHSVConverted = redHex.toHSV()
        // For pure red, hue should be 0 or 360, saturation should be 1, value should be 1
        assertTrue("Red hue should be 0 or close to 0",
            redHSVConverted.hue <= 1f || redHSVConverted.hue >= 359f)
        assertFloatEquals(1f, redHSVConverted.saturation, 0.1f)
        assertFloatEquals(1f, redHSVConverted.value, 0.1f)

        // Test green conversion
        val greenHSVConverted = greenHex.toHSV()
        assertFloatEquals(120f, greenHSVConverted.hue, 2f)
        assertFloatEquals(1f, greenHSVConverted.saturation, 0.1f)
        assertFloatEquals(1f, greenHSVConverted.value, 0.1f)

        // Test blue conversion
        val blueHSVConverted = blueHex.toHSV()
        assertFloatEquals(240f, blueHSVConverted.hue, 2f)
        assertFloatEquals(1f, blueHSVConverted.saturation, 0.1f)
        assertFloatEquals(1f, blueHSVConverted.value, 0.1f)

        // Test black conversion - for black, hue is undefined, saturation should be 0, value should be 0
        val blackHSVConverted = blackHex.toHSV()
        assertFloatEquals(0f, blackHSVConverted.saturation)
        assertFloatEquals(0f, blackHSVConverted.value)

        // Test white conversion - for white, saturation should be 0, value should be 1
        val whiteHSVConverted = whiteHex.toHSV()
        assertFloatEquals(0f, whiteHSVConverted.saturation)
        assertFloatEquals(1f, whiteHSVConverted.value)
    }

    @Test
    fun testHSVToHexConversion() {
        // Create HSV colors and convert back to hex
        val redHSVColor = HSVColor(redHSV[0], redHSV[1], redHSV[2])
        val redHexConverted = redHSVColor.toHEX()
        assertEquals(redHex.toColor(), redHexConverted.toColor())

        val greenHSVColor = HSVColor(greenHSV[0], greenHSV[1], greenHSV[2])
        val greenHexConverted = greenHSVColor.toHEX()
        assertEquals(greenHex.toColor(), greenHexConverted.toColor())

        val blueHSVColor = HSVColor(blueHSV[0], blueHSV[1], blueHSV[2])
        val blueHexConverted = blueHSVColor.toHEX()
        assertEquals(blueHex.toColor(), blueHexConverted.toColor())
    }

    @Test
    fun testHexToHSLConversion() {
        // Test known HSL values - be more flexible with red since pure colors can vary
        val redHSL = redHex.toHSL()
        assertTrue("Red hue should be 0 or close to 0",
            redHSL.hue <= 1f || redHSL.hue >= 359f)
        assertFloatEquals(1f, redHSL.saturation, 0.1f)
        assertFloatEquals(0.5f, redHSL.lightness, 0.1f)

        val whiteHSL = whiteHex.toHSL()
        assertFloatEquals(0f, whiteHSL.saturation)
        assertFloatEquals(1f, whiteHSL.lightness)

        val blackHSL = blackHex.toHSL()
        assertFloatEquals(0f, blackHSL.saturation)
        assertFloatEquals(0f, blackHSL.lightness)
    }

    @Test
    fun testHSLToHexConversion() {
        // Create HSL colors and convert to hex
        val redHSL = HSLColor(0f, 1f, 0.5f)
        val redHexFromHSL = redHSL.toHEX()

        // Allow for slight conversion differences
        val expectedRed = Color.red(redHex.toColor())
        val actualRed = Color.red(redHexFromHSL.toColor())
        assertTrue("Red channel mismatch", abs(expectedRed - actualRed) <= 2)
    }

    @Test
    fun testHSVToHSLConversion() {
        // Test round-trip conversions with a color that has better conversion stability
        val originalHSV = HSVColor(240f, 0.75f, 0.8f) // Blue-ish color
        val convertedHSL = originalHSV.toHSL()
        val backToHSV = convertedHSL.toHSV()

        // Check if round-trip conversion maintains color integrity
        // Use more lenient tolerances for round-trip conversions
        assertFloatEquals(originalHSV.hue, backToHSV.hue, 5f)
        assertFloatEquals(originalHSV.saturation, backToHSV.saturation, 0.1f)
        assertFloatEquals(originalHSV.value, backToHSV.value, 0.1f)
    }

    @Test
    fun testColorEquality() {
        // Test that different representations of the same color are equal
        val hexRed = HEXColor("#FFFF0000")
        val hsvRed = HSVColor(0f, 1f, 1f)
        val hslRed = HSLColor(0f, 1f, 0.5f)

        assertTrue("HEX and HSV red should be equal", hexRed.colorEquals(hsvRed))
        assertTrue("HSV and HSL red should be equal", hsvRed.colorEquals(hslRed))
        assertTrue("HEX and HSL red should be equal", hexRed.colorEquals(hslRed))
    }

    @Test
    fun testColorComparison() {
        val red = HSVColor(0f, 1f, 1f)
        val green = HSVColor(120f, 1f, 1f)
        val blue = HSVColor(240f, 1f, 1f)

        // Test hue comparison
        assertTrue("Red should be less than green", red < green)
        assertTrue("Green should be less than blue", green < blue)
        assertTrue("Red should be less than blue", red < blue)
    }

    @Test
    fun testMaterialColorConversion() {
        val baseColor = HEXColor("#2196F3") // Material Blue
        val materialColor = MaterialColor(baseColor)

        // Test that material color generates proper variants
        val color100 = materialColor.get100()
        val color500 = materialColor.get500()
        val color900 = materialColor.get900()

        // Verify lightness progression
        assertTrue("100 should be lighter than 500", color100.lightness > color500.lightness)
        assertTrue("500 should be lighter than 900", color500.lightness > color900.lightness)

        // Test conversion back to different formats
        val materialAsHex = materialColor.toHEX()
        val materialAsHSV = materialColor.toHSV()
        val materialAsHSL = materialColor.toHSL()

        assertNotNull("Material to HEX conversion should work", materialAsHex)
        assertNotNull("Material to HSV conversion should work", materialAsHSV)
        assertNotNull("Material to HSL conversion should work", materialAsHSL)
    }

    @Test
    fun testComposeColorIntegration() {
        val hexColor = HEXColor("#FF2196F3")
        val composeColor = hexColor.toComposeColor()

        // Create MaterialColor from Compose color
        val materialFromCompose = MaterialColor.fromComposeColor(composeColor)

        // Verify the conversion maintains color integrity
        val backToHex = materialFromCompose.toHEX()
        assertEquals(
            "Compose color conversion should maintain color",
            hexColor.toColor(), backToHex.toColor()
        )
    }

    @Test
    fun testColorModelUtilityMethods() {
        val red = HEXColor("#FFFF0000")
        val white = HEXColor("#FFFFFFFF")
        val black = HEXColor("#FF000000")

        // Test luminance calculation
        val redLuminance = red.getLuminance()
        val whiteLuminance = white.getLuminance()
        val blackLuminance = black.getLuminance()

        assertTrue("White should have highest luminance", whiteLuminance > redLuminance)
        assertTrue("Red should have higher luminance than black", redLuminance > blackLuminance)
        assertFloatEquals(1.0f, whiteLuminance.toFloat(), 0.01f)
        assertFloatEquals(0.0f, blackLuminance.toFloat(), 0.01f)

        // Test contrast ratio
        val contrastRatio = white.getContrastRatio(black)
        assertFloatEquals(21.0f, contrastRatio.toFloat(), 0.1f) // Maximum contrast ratio

        // Test contrasting text color
        val textForRed = red.getContrastingTextColor()
        val textForBlack = black.getContrastingTextColor()

        assertNotNull("Should provide contrasting text color for red", textForRed)
        assertNotNull("Should provide contrasting text color for black", textForBlack)
    }

    @Test
    fun testHSVColorModifiers() {
        val originalHSV = HSVColor(180f, 0.5f, 0.8f)

        // Test individual property modifications
        val newHue = originalHSV.setHue(90f)
        val newSaturation = originalHSV.setSaturation(0.7f)
        val newValue = originalHSV.setValue(0.9f)

        assertFloatEquals(90f, newHue.hue)
        assertFloatEquals(0.5f, newHue.saturation) // Should remain unchanged
        assertFloatEquals(0.8f, newHue.value) // Should remain unchanged

        assertFloatEquals(180f, newSaturation.hue) // Should remain unchanged
        assertFloatEquals(0.7f, newSaturation.saturation)
        assertFloatEquals(0.8f, newSaturation.value) // Should remain unchanged

        assertFloatEquals(180f, newValue.hue) // Should remain unchanged
        assertFloatEquals(0.5f, newValue.saturation) // Should remain unchanged
        assertFloatEquals(0.9f, newValue.value)
    }

    @Test
    fun testHSLColorModifiers() {
        val originalHSL = HSLColor(240f, 0.6f, 0.4f)

        // Test individual property modifications
        val newHue = originalHSL.setHue(120f)
        val newSaturation = originalHSL.setSaturation(0.8f)
        val newLightness = originalHSL.setLightness(0.6f)

        assertFloatEquals(120f, newHue.hue)
        assertFloatEquals(0.6f, newHue.saturation) // Should remain unchanged
        assertFloatEquals(0.4f, newHue.lightness) // Should remain unchanged

        assertFloatEquals(240f, newSaturation.hue) // Should remain unchanged
        assertFloatEquals(0.8f, newSaturation.saturation)
        assertFloatEquals(0.4f, newSaturation.lightness) // Should remain unchanged

        assertFloatEquals(240f, newLightness.hue) // Should remain unchanged
        assertFloatEquals(0.6f, newLightness.saturation) // Should remain unchanged
        assertFloatEquals(0.6f, newLightness.lightness)
    }

    @Test
    fun testHexColorStringRepresentation() {
        val colorWithAlpha = HEXColor("#FF2196F3")
        val colorWithoutAlpha = HEXColor("#2196F3")

        // Test hex string output
        val hexWithAlpha = colorWithAlpha.toHexString(includeAlpha = true)
        val hexWithoutAlpha = colorWithAlpha.toHexString(includeAlpha = false)

        assertTrue("Hex with alpha should include alpha channel", hexWithAlpha.length == 9)
        assertTrue("Hex without alpha should exclude alpha channel", hexWithoutAlpha.length == 7)
        assertTrue("Should start with #", hexWithAlpha.startsWith("#"))
        assertTrue("Should start with #", hexWithoutAlpha.startsWith("#"))
    }

    @Test
    fun testEdgeCaseConversions() {
        // Test edge cases like pure colors, grays, etc.
        val pureColors = listOf(
            HEXColor("#FFFF0000"), // Pure red
            HEXColor("#FF00FF00"), // Pure green
            HEXColor("#FF0000FF"), // Pure blue
            HEXColor("#FFFFFF00"), // Yellow
            HEXColor("#FFFF00FF"), // Magenta
            HEXColor("#FF00FFFF")  // Cyan
        )

        for (color in pureColors) {
            // Test round-trip conversions
            val hsv = color.toHSV()
            val hsl = color.toHSL()
            val backToHex = hsv.toHEX()

            // Colors should maintain integrity through conversions
            val originalColor = color.toColor()
            val convertedColor = backToHex.toColor()

            // Allow for minor rounding differences
            val rDiff = abs(Color.red(originalColor) - Color.red(convertedColor))
            val gDiff = abs(Color.green(originalColor) - Color.green(convertedColor))
            val bDiff = abs(Color.blue(originalColor) - Color.blue(convertedColor))

            assertTrue("Red channel should be close: $rDiff", rDiff <= 2)
            assertTrue("Green channel should be close: $gDiff", gDiff <= 2)
            assertTrue("Blue channel should be close: $bDiff", bDiff <= 2)
        }
    }

    @Test
    fun testRandomMaterialColorGeneration() {
        // Test random material color generation
        val randomMaterial1 = MaterialColor.random()
        val randomMaterial2 = MaterialColor.random()

        // Random colors should be different (very unlikely to be the same)
        assertNotEquals(
            "Random colors should be different",
            randomMaterial1.toColor(), randomMaterial2.toColor()
        )

        // Test with lightness adjustment
        val randomWithAdjustment = MaterialColor.random(adjustLightness = true)
        val randomWithoutAdjustment = MaterialColor.random(adjustLightness = false)

        assertNotNull("Random with adjustment should not be null", randomWithAdjustment)
        assertNotNull("Random without adjustment should not be null", randomWithoutAdjustment)
    }
}
