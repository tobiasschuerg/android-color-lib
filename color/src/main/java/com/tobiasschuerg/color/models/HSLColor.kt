package com.tobiasschuerg.color.models

import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

/**
 * Hue, Saturation, Lightness color model.
 *
 * @param hue The hue component (0-360 degrees)
 * @param saturation The saturation component (0.0-1.0)
 * @param lightness The lightness component (0.0-1.0)
 *
 * Created by Tobias Schürg on 13.08.2016.
 */
data class HSLColor(
    val hue: Float,
    val saturation: Float,
    val lightness: Float,
) : ColorModel {
    constructor(colorModel: ColorModel) : this(colorModel.toHslArray())

    constructor(hsl: FloatArray) : this(hsl[0], hsl[1], hsl[2])

    @ColorInt
    override fun toColor(): Int {
        return ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
    }

    override fun equals(other: Any?): Boolean = colorEquals(other)

    override fun hashCode(): Int = colorHashCode()

    /**
     * Creates a copy of this HSLColor with modified values.
     */
    fun setHue(newHue: Float): HSLColor = copy(hue = newHue)

    fun setSaturation(newSaturation: Float): HSLColor = copy(saturation = newSaturation)

    fun setLightness(newLightness: Float): HSLColor = copy(lightness = newLightness)
}

private fun ColorModel.toHslArray(): FloatArray {
    val c = FloatArray(3)
    ColorUtils.colorToHSL(this.toColor(), c)
    return c
}
