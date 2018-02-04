package com.tobiasschuerg.color.material

import com.tobiasschuerg.color.models.ColorModel
import com.tobiasschuerg.color.models.HSLColor

/**
 * Internally uses the [HSLColor] color model.
 *
 *
 * Created by Tobias Schürg on 13.08.2016.
 *
 *
 * Inspired by https://stackoverflow.com/questions/28012185/what-are-the-ways-to-programmatically-generate-material-design-color-sets
 */

class MaterialColor(val hsl: HSLColor) : ColorModel() {

    constructor(color: ColorModel) : this(color.toHSL())

    private var initialLightness: Float

    init {
        this.initialLightness = hsl.lightness
        if (initialLightness < 0.1) {
            // lightness not optimal, so we increase
            initialLightness = 0.25f
        }
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

    override fun toColor(): Int = hsl.toColor()

}
