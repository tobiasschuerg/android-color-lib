package com.tobiasschuerg.color.models

import android.support.annotation.ColorInt

/**
 * Defines methods of a color model.
 *
 * Created by Tobias Schürg on 13.08.2016.
 */

interface ColorModel<T> {

//    fun fromColor(@ColorInt color: Int): T
//
//    fun from(other: ColorModel<*>): T

    @ColorInt
    fun toColor(): Int

    fun toHSL(): HSLColor

    fun toHSV(): HSVColor

}
