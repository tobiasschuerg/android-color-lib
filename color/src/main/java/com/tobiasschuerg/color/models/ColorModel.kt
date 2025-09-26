package com.tobiasschuerg.color.models

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import com.tobiasschuerg.color.material.MaterialColor

/**
 * Interface defining the contract for color models.
 *
 * A color model represents a color in a specific color space (HSV, HSL, HEX, etc.)
 * and provides methods for conversion between different color representations.
 *
 * All implementations must provide a way to convert to an Android color integer,
 * and inherit utility methods for conversions and comparisons.
 *
 * @author Tobias Schürg
 * @since 13.08.2016
 */
interface ColorModel : Comparable<ColorModel> {
    /**
     * Converts this color model to an Android color integer.
     * @return Android color integer representation
     */
    @ColorInt
    fun toColor(): Int

    /**
     * Converts this color model to HSL color space.
     * @return HSLColor representation of this color
     */
    fun toHSL(): HSLColor =
        when (this) {
            is HSLColor -> this
            else -> HSLColor(this)
        }

    /**
     * Converts this color model to HSV color space.
     * @return HSVColor representation of this color
     */
    fun toHSV(): HSVColor =
        when (this) {
            is HSVColor -> this
            else -> HSVColor(this)
        }

    /**
     * Converts this color model to HEX representation.
     * @return HEXColor representation of this color
     */
    fun toHEX(): HEXColor = HEXColor(toColor())

    /**
     * Converts this color model to a Jetpack Compose Color.
     * @return Compose Color representation of this color
     */
    fun toComposeColor(): Color = Color(toColor())

    /**
     * Creates a MaterialColor palette from this color.
     * @return MaterialColor palette based on this color
     */
    fun toMaterialColor(): MaterialColor = MaterialColor(this)

    /**
     * Compares this color to another using HSV color space internally.
     * Colors are compared by hue, then saturation, then value/brightness.
     */
    override fun compareTo(other: ColorModel): Int {
        val hsv1 = this.toHSV()
        val hsv2 = other.toHSV()

        val c0 = hsv1.hue.compareTo(hsv2.hue)
        if (c0 == 0) {
            val c1 = hsv1.saturation.compareTo(hsv2.saturation)
            return if (c1 == 0) {
                hsv1.value.compareTo(hsv2.value)
            } else {
                c1
            }
        }
        return c0
    }

    /**
     * Returns a hash code based on the Android color integer representation.
     * This ensures that colors with the same RGB values have the same hash code
     * regardless of their color space representation.
     */
    fun colorHashCode(): Int = toColor()

    /**
     * Checks if this color is equal to another color based on their RGB values.
     * Colors are considered equal if they produce the same Android color integer,
     * regardless of their color space representation.
     */
    fun colorEquals(other: Any?): Boolean =
        when (other) {
            is ColorModel -> this.toColor() == other.toColor()
            else -> false
        }

    /**
     * Returns the luminance of this color according to WCAG guidelines.
     * Useful for determining contrast ratios.
     * @return Luminance value between 0.0 (black) and 1.0 (white)
     */
    fun getLuminance(): Double {
        val color = toColor()
        val r = android.graphics.Color.red(color) / 255.0
        val g = android.graphics.Color.green(color) / 255.0
        val b = android.graphics.Color.blue(color) / 255.0

        val rLin = if (r <= 0.03928) r / 12.92 else Math.pow((r + 0.055) / 1.055, 2.4)
        val gLin = if (g <= 0.03928) g / 12.92 else Math.pow((g + 0.055) / 1.055, 2.4)
        val bLin = if (b <= 0.03928) b / 12.92 else Math.pow((b + 0.055) / 1.055, 2.4)

        return 0.2126 * rLin + 0.7152 * gLin + 0.0722 * bLin
    }

    /**
     * Calculates the contrast ratio between this color and another color.
     * Useful for accessibility compliance (WCAG guidelines).
     * @param other The color to compare against
     * @return Contrast ratio between 1.0 (no contrast) and 21.0 (maximum contrast)
     */
    fun getContrastRatio(other: ColorModel): Double {
        val lum1 = this.getLuminance()
        val lum2 = other.getLuminance()
        val lighter = maxOf(lum1, lum2)
        val darker = minOf(lum1, lum2)
        return (lighter + 0.05) / (darker + 0.05)
    }

    /**
     * Returns the appropriate text color (black or white) for maximum contrast
     * against this color background.
     * @return HEXColor.BLACK or HEXColor.WHITE
     */
    fun getContrastingTextColor(): HEXColor {
        return if (getLuminance() > 0.5) {
            HEXColor(android.graphics.Color.BLACK)
        } else {
            HEXColor(android.graphics.Color.WHITE)
        }
    }
}
