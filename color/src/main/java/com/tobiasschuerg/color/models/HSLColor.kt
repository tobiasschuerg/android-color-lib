package com.tobiasschuerg.color.models

import android.support.annotation.ColorInt
import android.support.v4.graphics.ColorUtils

/**
 * Hue, Saturation, Lightness
 *
 * Created by Tobias Schürg on 13.08.2016.
 */

data class HSLColor(
        val hue: Float,
        val saturation: Float,
        val lightness: Float
) : AbstractColor<HSLColor>() {

    override fun toColor(): Int {
        return ColorUtils.HSLToColor(floatArrayOf(hue, saturation, lightness))
    }

    override fun toHSL(): HSLColor {
        return this
    }

    override fun toHSV(): HSVColor {
        return HSVColor.fromColor(toColor())
    }

    companion object {

        fun from(other: ColorModel<*>): HSLColor {
            return fromColor(other.toColor())
        }

        fun fromColor(@ColorInt color: Int): HSLColor {
            val c = FloatArray(3)
            ColorUtils.colorToHSL(color, c)
            return HSLColor(c[0], c[1], c[2])
        }
    }


}
