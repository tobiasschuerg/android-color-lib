package com.tobiasschuerg.color.models

import android.graphics.Color
import android.support.annotation.ColorInt

/**
 * Based on
 * http://www.nbdtech.com/Blog/archive/2008/04/27/Calculating-the-Perceived-Brightness-of-a-Color.aspx
 *
 */
fun ColorModel.getBrightness(): Double {
    val color = toColor()
    val red = Color.red(color)
    val green = Color.green(color)
    val blue = Color.blue(color)
    return Math.sqrt(0.241 * red.square() + 0.691 * green.square() + 0.068 * blue.square())
}

private fun Int.square() = this * this

/**
 * Returns the complimentary (opposite) color.
 * Based on http://www.java2s.com/Code/Android/2D-Graphics/Returnsthecomplimentaryoppositecolor.htm
 *
 * @return int RGB of compliment color
 */
// calculate compliments
fun ColorModel.getComplimentColor(): HEXColor {
    val color = toColor()
    val alpha = Color.alpha(color)
    var red = Color.red(color)
    var blue = Color.blue(color)
    var green = Color.green(color)
    red = red.inv() and 0xff
    blue = blue.inv() and 0xff
    green = green.inv() and 0xff
    val compliment = Color.argb(alpha, red, green, blue)
    return HEXColor(compliment)
}

/**
 * Estimates the best fitting text color if this color is used as background to draw on.
 *
 * @param preference: none, black or white
 * @return #Color.WHITE or #Color.BLACK
 */
fun ColorModel.getTextBlackWhite(@ColorInt preference: Int? = Color.WHITE): Int = when (preference) {
    Color.BLACK -> if (getBrightness() < 75) Color.WHITE else Color.BLACK
    null        -> if (getBrightness() < 130) Color.WHITE else Color.BLACK
    Color.WHITE -> if (getBrightness() < 180) Color.WHITE else Color.BLACK
    else        -> throw IllegalArgumentException("Color must be Color.Black or Color.White")
}