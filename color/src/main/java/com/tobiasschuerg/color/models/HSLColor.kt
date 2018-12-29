package com.tobiasschuerg.color.models

import androidx.core.graphics.ColorUtils

/**
 * Hue, Saturation, Lightness
 *
 * Created by Tobias Schürg on 13.08.2016.
 */

data class HSLColor(
        val hue: Float,
        val saturation: Float,
        val lightness: Float
) : ColorModel() {

    constructor(colorModel: ColorModel) : this(colorModel.toHslArray())

    constructor(hsl: FloatArray) : this(hsl[0], hsl[1], hsl[2])

    override fun toColor(): Int {
        return ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
    }

}

private fun ColorModel.toHslArray(): FloatArray {
    val c = FloatArray(3)
    ColorUtils.colorToHSL(this.toColor(), c)
    return c
}
