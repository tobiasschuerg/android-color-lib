package com.tobiasschuerg.color

import com.tobiasschuerg.color.models.HSVColor
import com.tobiasschuerg.color.random.RandomColor

/**
 * Created by Tobias
 */
object ColorCreator {

    /*
    Returns a random color
     */
    fun randomColor() = getRandomColors(1)[0]

    /**
     * Returns an array of count random color values
     */
    fun getRandomColors(count: Int): Array<out HSVColor> {
        val randomColor = RandomColor()
        return randomColor.randomColor(count)
    }


    /*
       Returns an array of ten green colors
     */
    fun getSimilarRandomColors(color: RandomColor.Color, count: Int): Array<out HSVColor> {
        val randomColor = RandomColor()
        return randomColor.random(color, count)
    }
}


