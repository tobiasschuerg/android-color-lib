package com.tobiasschuerg.color.models

import android.support.annotation.ColorInt

/**
 * Defines methods of a color model.
 *
 * Created by Tobias Schürg on 13.08.2016.
 */

abstract class ColorModel : Comparable<ColorModel> {

    @ColorInt
    abstract fun toColor(): Int

    fun toHSL(): HSLColor = when (this) {
        is HSLColor -> this
        else        -> HSLColor(this)
    }

    fun toHSV(): HSVColor = when (this) {
        is HSVColor -> this
        else        -> HSVColor(this)
    }

    /**
     * Compares this color to another using [HSVColor] internally.
     */
    override fun compareTo(other: ColorModel): Int {
        val hsv1 = this.toHSV()
        val hsv2 = other.toHSV()

        val c0 = java.lang.Double.compare(hsv1.hue.toDouble(), hsv2.hue.toDouble())
        if (c0 == 0) {
            val c1 = java.lang.Double.compare(hsv1.saturation.toDouble(), hsv2.saturation.toDouble())
            return if (c1 == 0) {
                java.lang.Double.compare(hsv1.value.toDouble(), hsv2.value.toDouble())
            } else {
                c1
            }
        } else {
            return c0
        }
    }

    override fun hashCode(): Int {
        return toColor()
    }

    override fun equals(other: Any?): Boolean {
        return if (other is ColorModel) {
            this.toColor() == other.toColor()
        } else {
            super.equals(other)
        }
    }

    //    /**
//     * Based on http://www.java2s.com/Code/Android/2D-Graphics/brighteracolor.htm
//     *
//     * @param factor
//     */
//    @JvmOverloads
//    fun brighten(factor: Double = 0.9) {
//
//        var r = Color.red(toColor())
//        var b = Color.blue(toColor())
//        var g = Color.green(toColor())
//
//        if (r == 0 && b == 0 && g == 0) {
//           return  fromColor(Color.DKGRAY)
//        }
//
//        if (r < 3 && r != 0) {
//            r = 3
//        } else {
//            r = (r / factor).toInt()
//            r = if (r > 255) 255 else r
//        }
//
//        if (b < 3 && b != 0) {
//            b = 3
//        } else {
//            b = (b / .7).toInt()
//            b = if (b > 255) 255 else b
//        }
//
//        if (g < 3 && g != 0) {
//            g = 3
//        } else {
//            g = (g / .7).toInt()
//            g = if (g > 255) 255 else g
//        }
//
//        fromColor(Color.rgb(r, g, b))
//
//    }
//
//    @JvmOverloads
//    fun darken(factor: Double = 0.9) {
//        val r = Color.red(toColor())
//        val b = Color.blue(toColor())
//        val g = Color.green(toColor())
//
//        fromColor(Color.rgb((r * factor).toInt(), (g * factor).toInt(), (b * factor).toInt()))
//    }

}
