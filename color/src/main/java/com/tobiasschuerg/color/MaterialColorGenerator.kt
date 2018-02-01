package com.tobiasschuerg.color

import android.graphics.Color
import com.tobiasschuerg.color.models.HEXColor
import com.tobiasschuerg.color.models.HSLColor
import java.util.*

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

@Deprecated("")
// TODO: merge with MaterialColor
object MaterialColorGenerator {

    fun returnMaterialDesignColorSet(colorHex: String): ArrayList<HSLColor> {
        val resultList = ArrayList<HSLColor>()
        val hexColor = HEXColor(colorHex)
        val hsl = HSLColor.from(hexColor).copy(lightness = 0.5f)
        val randomMid = randomWithRange(0.48, 0.52)

        resultList.add(hsl)
        return resultList
    }

    fun randomWithRange(min: Double, max: Double): Double {
        val range = Math.abs(max - min)
        return Math.random() * range + if (min <= max) min else max
    }

    fun colorInt(hex: String): Int {
        return Color.parseColor(hex)
    }

}
