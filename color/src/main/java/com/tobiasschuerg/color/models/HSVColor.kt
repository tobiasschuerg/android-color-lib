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
) : ColorModel<HSVColor> {

    @ColorInt
    override fun toColor(): Int {
        return Color.HSVToColor(floatArrayOf(hue, saturation, value))
    }

    override fun toHSL(): HSLColor {
        return HSLColor.fromColor(toColor())
    }

    override fun toHSV(): HSVColor {
        return this
    }

    companion object {


        fun fromColor(color: Int): HSVColor {
            val c = FloatArray(3)
            Color.colorToHSV(color, c)
            return HSVColor(c[0], c[1], c[2])
        }

        fun from(other: ColorModel<*>): HSVColor {
            return fromColor(other.toColor())
        }

    }


}
