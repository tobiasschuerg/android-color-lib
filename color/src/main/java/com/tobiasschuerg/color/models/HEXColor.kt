package com.tobiasschuerg.color.models

import androidx.annotation.ColorInt
import androidx.core.graphics.toColorInt

/**
 * Hexadecimal color representation.
 *
 * @param color The Android color integer value
 *
 * Created by Tobias Schürg on 13.08.2016.
 */
data class HEXColor(
    @ColorInt private val color: Int,
) : ColorModel {
    constructor(hex: String) : this(hex.toColorInt())

    @ColorInt
    override fun toColor(): Int = color

    override fun equals(other: Any?): Boolean = colorEquals(other)

    override fun hashCode(): Int = colorHashCode()

    /**
     * Returns the hexadecimal string representation of this color.
     * @param includeAlpha Whether to include alpha channel in the output
     * @return Hexadecimal string (e.g., "#FF2196F3" or "#2196F3")
     */
    fun toHexString(includeAlpha: Boolean = true): String {
        return if (includeAlpha) {
            String.format("#%08X", color)
        } else {
            String.format("#%06X", color and 0xFFFFFF)
        }
    }

    override fun toString(): String = toHexString()
}
