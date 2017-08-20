package com.tobiasschuerg.color

import com.tobiasschuerg.color.random.RandomColor

/**
 * Created by Tobias
 */
object ColorCreator {

    /*
    Returns a random color
     */
    fun randomColor() = getRandomColors(1).first()

    /**
     * Returns an array of count random color values
     */
    fun getRandomColors(count: Int): IntArray {
        val randomColor = RandomColor()
        return randomColor.randomColor(count)
    }


    /*
       Returns an array of ten green colors
     */
    fun getSimilarRandomColors(color: RandomColor.Color, count: Int): IntArray {
        val randomColor = RandomColor()
        return randomColor.random(color, count)
    }
}


