package com.tobiasschuerg.color.models

import android.graphics.Color
import android.support.annotation.ColorInt

/**
 * Hue
 * Saturation
 * Value / Brightness
 *
 *
 * Created by Tobias Schürg on 13.08.2016.
 */

data class HEXColor(private val hex: String) : ColorModel<HEXColor> {

    @ColorInt
    private val color: Int

    init {
        this.color = Color.parseColor(hex)
    }

    override fun toColor(): Int {
        return color
    }

    override fun toHSL(): HSLColor {
        return HSLColor.fromColor(toColor())
    }

    override fun toHSV(): HSVColor {
        return HSVColor.fromColor(color)
    }

    companion object {

        fun fromColor(color: Int): HEXColor {
            return HEXColor(String.format("#%06X", 0xFFFFFF and color))

        }

        fun from(other: ColorModel<*>): HEXColor {
            return fromColor(other.toColor())
        }
    }
}
