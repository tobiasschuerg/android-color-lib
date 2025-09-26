package com.tobiasschuerg.color.models

import android.graphics.Color
import androidx.annotation.ColorInt

/**
 * Hue, saturation, value color model.
 *
 * @param hue The hue component (0-360 degrees)
 * @param saturation The saturation component (0.0-1.0)
 * @param value The value/brightness component (0.0-1.0)
 *
 * Created by Tobias Schürg on 13.08.2016.
 */
data class HSVColor(
    val hue: Float,
    val saturation: Float,
    val value: Float
) : ColorModel {

    constructor(color: ColorModel) : this(color.toHsvArray())

    constructor(hsvArray: FloatArray) : this(hsvArray[0], hsvArray[1], hsvArray[2])

    @ColorInt
    override fun toColor(): Int {
        return Color.HSVToColor(floatArrayOf(hue, saturation, value))
    }

    override fun equals(other: Any?): Boolean = colorEquals(other)

    override fun hashCode(): Int = colorHashCode()

    /**
     * Creates a copy of this HSVColor with modified values.
     */
    fun setHue(newHue: Float): HSVColor = copy(hue = newHue)
    fun setSaturation(newSaturation: Float): HSVColor = copy(saturation = newSaturation)
    fun setValue(newValue: Float): HSVColor = copy(value = newValue)
}

private fun ColorModel.toHsvArray(): FloatArray {
    val hsvArray = FloatArray(3)
    Color.colorToHSV(this.toColor(), hsvArray)
    return hsvArray
}
