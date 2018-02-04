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

data class HEXColor(@ColorInt private val color: Int) : ColorModel() {

    constructor(hex: String) : this(Color.parseColor(hex))

    override fun toColor(): Int = color

}
