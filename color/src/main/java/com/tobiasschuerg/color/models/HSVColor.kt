package com.tobiasschuerg.color.models

import android.graphics.Color
import android.support.annotation.ColorInt

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

data class HSVColor(
        val hue: Float,
        val saturation: Float,
        val value: Float
) : ColorModel() {

    constructor(color: ColorModel) : this(color.toHsvArray())

    constructor(hsvArray: FloatArray) : this(hsvArray[0], hsvArray[1], hsvArray[2])

    @ColorInt
    override fun toColor(): Int {
        return Color.HSVToColor(floatArrayOf(hue, saturation, value))
    }

}

private fun ColorModel.toHsvArray(): FloatArray {
    val hsvArray = FloatArray(3)
    Color.colorToHSV(this.toColor(), hsvArray)
    return hsvArray
}
