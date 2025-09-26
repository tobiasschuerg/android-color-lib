package com.tobiasschuerg.color.material

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.tobiasschuerg.color.models.ColorModel
import com.tobiasschuerg.color.models.HEXColor
import com.tobiasschuerg.color.models.HSLColor
import com.tobiasschuerg.color.random.RandomColor

/**
 * Material Design Color Palette Generator
 *
 * A [MaterialColor] represents a complete Material Design color palette based on a single color.
 * It generates all standard Material Design color variants (100-900) with proper lightness distribution.
 *
 * Internally uses the [HSLColor] color model for optimal color manipulation and consistency.
 *
 * ## Usage Examples:
 * ```kotlin
 * // From any ColorModel
 * val material = MaterialColor(HEXColor("#2196F3"))
 *
 * // Random material color
 * val randomMaterial = MaterialColor.random()
 *
 * // From Android Color int
 * val fromInt = MaterialColor.fromColorInt(Color.BLUE)
 *
 * // From Compose Color
 * val fromCompose = MaterialColor.fromComposeColor(Color.Blue)
 *
 * // Use in UI
 * toolbar.setBackgroundColor(material.get500().toColor())
 * fab.setBackgroundColor(material.get600().toColor())
 * ```
 *
 * @param hsl The base HSL color for generating the material palette
 *
 * @author Tobias Schürg
 * @since 13.08.2016
 * @see <a href="https://material.io/design/color/">Material Design Color System</a>
 * @see <a href="https://stackoverflow.com/questions/28012185/what-are-the-ways-to-programmatically-generate-material-design-color-sets">Stack Overflow inspiration</a>
 */

data class MaterialColor(private val hsl: HSLColor) : ColorModel {
    /**
     * Creates a MaterialColor from any ColorModel.
     * @param color The source color to convert
     */
    constructor(color: ColorModel) : this(color.toHSL())

    /**
     * The initial lightness value, automatically adjusted for optimal Material Design compatibility.
     * If the original lightness is too low (< 0.1), it's increased to 0.25 for better color variants.
     */
    private val initialLightness: Float
        get() =
            if (hsl.lightness < 0.1) {
                // lightness not optimal, so we increase for better material variants
                0.25f
            } else {
                hsl.lightness
            }

    companion object {
        /**
         * Creates a random MaterialColor with good Material Design characteristics.
         *
         * @param adjustLightness if true, automatically adjusts lightness for better material design compatibility
         * @return A new MaterialColor with random hue and saturation
         */
        @JvmStatic
        fun random(adjustLightness: Boolean = true): MaterialColor {
            val randomHsv = RandomColor.randomColor()
            val materialColor = MaterialColor(randomHsv)
            return if (adjustLightness && materialColor.hsl.lightness < 0.1f) {
                MaterialColor(materialColor.hsl.copy(lightness = 0.25f))
            } else {
                materialColor
            }
        }

        /**
         * Creates a MaterialColor from any ColorModel.
         * This is an alias for the constructor for Java compatibility.
         *
         * @param color The source color model
         * @return A new MaterialColor based on the provided color
         */
        @JvmStatic
        fun from(color: ColorModel): MaterialColor = MaterialColor(color)

        /**
         * Creates a MaterialColor from an Android Color integer.
         *
         * @param colorInt The Android color integer (e.g., Color.BLUE, 0xFF2196F3)
         * @return A new MaterialColor based on the provided color
         */
        @JvmStatic
        fun fromColorInt(
            @ColorInt colorInt: Int,
        ): MaterialColor = MaterialColor(HEXColor(colorInt))

        /**
         * Creates a MaterialColor from a Jetpack Compose Color.
         *
         * @param composeColor The Jetpack Compose Color
         * @return A new MaterialColor based on the provided Compose color
         */
        @JvmStatic
        fun fromComposeColor(composeColor: Color): MaterialColor {
            return fromColorInt(composeColor.toArgb())
        }
    }

    // Material Design Color Variants
    // Based on Material Design guidelines for color lightness distribution

    /**
     * Material 100 - Lightest shade (79% lighter than base)
     * @return HSLColor with increased lightness
     */
    fun get100(): HSLColor = hsl.copy(lightness = (initialLightness * 1.79f).coerceAtMost(1.0f))

    /**
     * Material 200 - Very light shade (56% lighter than base)
     * @return HSLColor with increased lightness
     */
    fun get200(): HSLColor = hsl.copy(lightness = (initialLightness * 1.56f).coerceAtMost(1.0f))

    /**
     * Material 300 - Light shade (33% lighter than base)
     * @return HSLColor with increased lightness
     */
    fun get300(): HSLColor = hsl.copy(lightness = (initialLightness * 1.333f).coerceAtMost(1.0f))

    /**
     * Material 400 - Slightly light shade (16% lighter than base)
     * @return HSLColor with slightly increased lightness
     */
    fun get400(): HSLColor = hsl.copy(lightness = (initialLightness * 1.16f).coerceAtMost(1.0f))

    /**
     * Material 500 - Primary/Base color
     * This is the main color of your material palette.
     * @return HSLColor with base lightness
     */
    fun get500(): HSLColor = hsl.copy(lightness = initialLightness)

    /**
     * Material 600 - Slightly dark shade (18% darker than base)
     * @return HSLColor with decreased lightness
     */
    fun get600(): HSLColor = hsl.copy(lightness = (initialLightness * 0.82f).coerceAtLeast(0.0f))

    /**
     * Material 700 - Dark shade (42% darker than base)
     * @return HSLColor with decreased lightness
     */
    fun get700(): HSLColor = hsl.copy(lightness = (initialLightness * 0.58f).coerceAtLeast(0.0f))

    /**
     * Material 800 - Very dark shade (62% darker than base)
     * @return HSLColor with significantly decreased lightness
     */
    fun get800(): HSLColor = hsl.copy(lightness = (initialLightness * 0.38f).coerceAtLeast(0.0f))

    /**
     * Material 900 - Darkest shade (81% darker than base)
     * @return HSLColor with minimal lightness
     */
    fun get900(): HSLColor = hsl.copy(lightness = (initialLightness * 0.19f).coerceAtLeast(0.0f))

    // Utility Methods

    /**
     * Returns the appropriate text color (black or white) for maximum contrast
     * against the primary color (500).
     *
     * @return Color.WHITE or Color.BLACK as appropriate
     */
    @ColorInt
    fun getTextColor(): Int {
        val luminance = calculateLuminance(get500().toColor())
        return if (luminance > 0.5) android.graphics.Color.BLACK else android.graphics.Color.WHITE
    }

    /**
     * Returns all material color variants as a list.
     * Useful for displaying color palettes or programmatic access.
     *
     * @return List of HSLColor containing all variants from 100 to 900
     */
    fun getAllVariants(): List<HSLColor> =
        listOf(
            get100(), get200(), get300(), get400(), get500(),
            get600(), get700(), get800(), get900(),
        )

    /**
     * Converts this MaterialColor to a Jetpack Compose Color.
     * Returns the primary color (500) as a Compose Color.
     *
     * @return Jetpack Compose Color representation of the primary color
     */
    override fun toComposeColor(): Color = Color(get500().toColor())

    /**
     * Calculates the relative luminance of a color for contrast calculations.
     * Based on WCAG guidelines.
     */
    private fun calculateLuminance(
        @ColorInt color: Int,
    ): Double {
        val r = android.graphics.Color.red(color) / 255.0
        val g = android.graphics.Color.green(color) / 255.0
        val b = android.graphics.Color.blue(color) / 255.0

        val rLin = if (r <= 0.03928) r / 12.92 else Math.pow((r + 0.055) / 1.055, 2.4)
        val gLin = if (g <= 0.03928) g / 12.92 else Math.pow((g + 0.055) / 1.055, 2.4)
        val bLin = if (b <= 0.03928) b / 12.92 else Math.pow((b + 0.055) / 1.055, 2.4)

        return 0.2126 * rLin + 0.7152 * gLin + 0.0722 * bLin
    }

    override fun equals(other: Any?): Boolean = colorEquals(other)

    override fun hashCode(): Int = colorHashCode()

    /**
     * Returns the Android color integer representation of the primary color (500).
     * @return Android color integer
     */
    @ColorInt
    override fun toColor(): Int = hsl.toColor()

    /**
     * Returns a string representation of this MaterialColor showing the base HSL values.
     * @return String representation
     */
    override fun toString(): String = "MaterialColor(hsl=$hsl, lightness=$initialLightness)"
}
