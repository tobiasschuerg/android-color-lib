package com.tobiasschuerg.color

import com.tobiasschuerg.color.models.HSVColor
import com.tobiasschuerg.color.random.Color
import com.tobiasschuerg.color.random.RandomColor

/**
 * Created by Tobias
 */
object ColorCreator {
    /*
    Returns a random color
     */
    fun randomColor() = RandomColor.randomColor()

    /**
     * Returns an array of count random color values
     */
    fun getRandomColors(count: Int): List<HSVColor> = RandomColor.randomColors(count)

    /*
       Returns an array of ten green colors
     */
    internal fun getSimilarRandomColors(
        color: Color,
        count: Int,
    ): List<HSVColor> {
        return RandomColor.random(color, count)
    }
}
