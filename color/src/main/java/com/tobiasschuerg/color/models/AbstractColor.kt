package com.tobiasschuerg.color.models

import android.graphics.Color

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

abstract class AbstractColor<T> : ColorModel<T>, Comparable<AbstractColor<*>> {

    /**
     * Returns black or white according to given color. Based on
     * http://www.nbdtech.com/Blog/archive/2008/04/27/Calculating-the-Perceived-Brightness-of-a-Color.aspx
     *
     * @return black or white
     */
    // Log.d("Color", "Brightness: " + brightness);
    fun getBrightness(): Double {
        val color = toColor()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Math.sqrt(0.241 * red.toDouble() * red.toDouble() + 0.691 * green.toDouble() * green.toDouble() + 0.068 * blue.toDouble() * blue.toDouble())
    }

    fun getForeGroundColor(): Int = getForeGroundColor(ColorPreference.NONE)


    /**
     * Returns the complimentary (opposite) color.
     * Based on http://www.java2s.com/Code/Android/2D-Graphics/Returnsthecomplimentaryoppositecolor.htm
     *
     * @return int RGB of compliment color
     */
    // find compliments
    fun getComplimentColor(): Int {
        val color = toColor()
        val alpha = Color.alpha(color)
        var red = Color.red(color)
        var blue = Color.blue(color)
        var green = Color.green(color)
        red = red.inv() and 0xff
        blue = blue.inv() and 0xff
        green = green.inv() and 0xff

        return Color.argb(alpha, red, green, blue)
    }

    enum class ColorPreference {
        NONE, BLACK, WHITE
    }

    /**
     * See [.getBrightness]
     *
     * @param cp preference: none, black or white
     * @return #Color.WHITE or #Color.BLACK
     */
    fun getForeGroundColor(cp: ColorPreference): Int {
        when (cp) {
            AbstractColor.ColorPreference.NONE  -> return if (getBrightness() < 130) Color.WHITE else Color.BLACK

            AbstractColor.ColorPreference.BLACK -> return if (getBrightness() < 75) Color.WHITE else Color.BLACK

            AbstractColor.ColorPreference.WHITE -> return if (getBrightness() < 180) Color.WHITE else Color.BLACK
            else                                -> return if (getBrightness() < 130) Color.WHITE else Color.BLACK
        }
    }


    /**
     * Compares this color to another using [HSVColor] internally.
     */
    override fun compareTo(other: AbstractColor<*>): Int {
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
        return if (other is AbstractColor<*>) {
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
