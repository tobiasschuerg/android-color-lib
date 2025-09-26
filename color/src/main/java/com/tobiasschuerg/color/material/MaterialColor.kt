package com.tobiasschuerg.color.material

import com.tobiasschuerg.color.models.ColorModel
import com.tobiasschuerg.color.models.HSLColor
import com.tobiasschuerg.color.random.RandomColor

/**
 * Internally uses the [HSLColor] color model.
 *
 *
 * Created by Tobias Schürg on 13.08.2016.
 *
 *
 * Inspired by https://stackoverflow.com/questions/28012185/what-are-the-ways-to-programmatically-generate-material-design-color-sets
 */

data class MaterialColor(private val hsl: HSLColor) : ColorModel() {

    constructor(color: ColorModel) : this(color.toHSL())

    private val initialLightness: Float
        get() = if (hsl.lightness < 0.1) {
            // lightness not optimal, so we increase
            0.25f
        } else {
            hsl.lightness
        }

    /* 100 - 79% light  */
    fun get100(): HSLColor = hsl.copy(lightness = initialLightness * 1.79f)

    /* 200 - 56% light  */
    fun get200(): HSLColor = hsl.copy(lightness = initialLightness * 1.56f)

    /* 300 - 33% light  */
    fun get300(): HSLColor = hsl.copy(lightness = initialLightness * 1.333f)

    /* 400 - 16% light  */
    fun get400(): HSLColor = hsl.copy(lightness = initialLightness * 1.16f)

    /* 500 - Primary */
    fun get500(): HSLColor = hsl.copy(lightness = initialLightness)

    /* 600 - 18% dark */
    fun get600(): HSLColor = hsl.copy(lightness = initialLightness * 0.82f)

    /* 700 - 42% dark */
    fun get700(): HSLColor = hsl.copy(lightness = initialLightness * 0.58f)

    /* 800 - 62% dark */
    fun get800(): HSLColor = hsl.copy(lightness = initialLightness * 0.38f)

    /* 900 - Dark */
    fun get900(): HSLColor = hsl.copy(lightness = initialLightness * 0.19f)

    companion object {
        /**
         * Creates a random MaterialColor.
         * @param adjustLightness if true, adjusts the lightness for better material design compatibility
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
         */
        @JvmStatic
        fun from(color: ColorModel): MaterialColor = MaterialColor(color)
    }

    override fun toColor(): Int = hsl.toColor()

}
