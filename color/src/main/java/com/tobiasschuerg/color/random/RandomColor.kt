package com.tobiasschuerg.color.random

import com.tobiasschuerg.color.models.HSVColor
import java.util.*

/**
 * Creates a random color.
 *
 *
 * Created by bruce on 15/2/5.
 */
object RandomColor {

    private val colors = HashMap<String, ColorInfo>()

    init {
        loadColorBounds()
    }

    fun randomColor(): HSVColor {
        val hueRange = Range(0, 360)
        val hue = doPickHue(hueRange)
        val saturation = pickSaturation(hue)
        val brightness = pickBrightness(hue, saturation)

        return HSVColor(hue.toFloat(), saturation.toFloat(), brightness.toFloat())
    }

    fun randomColors(count: Int): List<HSVColor> {
        if (count <= 0) {
            throw IllegalArgumentException("count must be greater than 0")
        }

        val colors = mutableListOf<HSVColor>()
        for (i in 0 until count) {
            colors.add(randomColor())
        }

        return colors
    }

    internal fun randomColor(color: Color): HSVColor {
        val hueRange = getHueRange(color.name)
        val hue = hueRange.let(this::doPickHue)
        val saturation = pickSaturation(color)
        val brightness = pickBrightness(color, saturation)
        return HSVColor(hue.toFloat(), saturation.toFloat(), brightness.toFloat())
    }

    internal fun random(color: Color, count: Int): List<HSVColor> {
        if (count <= 0) {
            throw IllegalArgumentException("count must be greater than 0")
        }

        val colors = mutableListOf<HSVColor>()
        for (i in 0 until count) {
            colors[i] = randomColor(color)
        }

        return colors
    }

    private fun doPickHue(hueRange: Range): Int {
        var hue = randomWithin(hueRange)

        // Instead of storing red as two separate ranges,
        // we group them, using negative numbers
        if (hue < 0) {
            hue += 360
        }

        return hue
    }

    private fun getHueRange(name: String): Range {
        if (colors.containsKey(name)) {
            val hueRange = colors[name]!!.hueRange
            if (hueRange != null) {
                return hueRange
            }
        }
        return Range(0, 360)
    }

    private fun pickSaturation(hue: Int): Int {
        return pickSaturation(getColorInfo(hue))
    }

    private fun pickSaturation(color: Color): Int {
        val colorInfo: ColorInfo? = colors[color.name]
        return pickSaturation(colorInfo)
    }

    private fun pickSaturation(colorInfo: ColorInfo?): Int {
        if (colorInfo == null) {
            return 0
        }
        val saturationRange = colorInfo.saturationRange
        return randomWithin(saturationRange)
    }

    private fun pickBrightness(hue: Int, saturation: Int): Int {
        val colorInfo = getColorInfo(hue)
        return pickBrightness(colorInfo, saturation)
    }

    private fun pickBrightness(color: Color, saturation: Int): Int {
        val colorInfo: ColorInfo = colors[color.name]!!

        return pickBrightness(colorInfo, saturation)
    }

    private fun pickBrightness(colorInfo: ColorInfo?, saturation: Int): Int {
        val min = getMinimumBrightness(colorInfo, saturation)
        val max = 100
        return randomWithin(Range(min, max))
    }

    private fun getMinimumBrightness(colorInfo: ColorInfo?, saturation: Int): Int {
        if (colorInfo == null) {
            return 0
        }

        val lowerBounds = colorInfo.lowerBounds
        for (i in 0 until lowerBounds.size - 1) {

            val s1 = lowerBounds[i].start
            val v1 = lowerBounds[i].end

            if (i == lowerBounds.size - 1) {
                break
            }
            val s2 = lowerBounds[i + 1].start
            val v2 = lowerBounds[i + 1].end

            if (saturation >= s1 && saturation <= s2) {

                val m = (v2 - v1) / (s2 - s1).toFloat()
                val b = v1 - m * s1

                return (m * saturation + b).toInt()
            }

        }

        return 0
    }

    private fun getColorInfo(hue1: Int): ColorInfo? {
        var hue = hue1
        // Maps red colors to make picking hue easier
        if (hue >= 334 && hue <= 360) {
            hue -= 360
        }

        for (key in colors.keys) {
            val colorInfo: ColorInfo = colors[key]!!
            if (colorInfo.hueRange != null && colorInfo.hueRange!!.contains(hue)) {
                return colorInfo
            }
        }

        return null
    }

    private fun randomWithin(range: Range): Int {
        return Math.floor(range.start + Math.random() * (range.end + 1 - range.start)).toInt()
    }

    fun defineColor(name: String, hueRange: Range?, lowerBounds: List<Range>) {
        val sMin = lowerBounds[0].start
        val sMax = lowerBounds[lowerBounds.size - 1].start
        val bMin = lowerBounds[lowerBounds.size - 1].end
        val bMax = lowerBounds[0].end

        colors[name] = ColorInfo(hueRange, Range(sMin, sMax), Range(bMin, bMax), lowerBounds)
    }

    private fun loadColorBounds() {
        val lowerBounds1 = ArrayList<Range>()
        lowerBounds1.add(Range(0, 0))
        lowerBounds1.add(Range(100, 0))
        defineColor(
                Color.MONOCHROME.name,
                null,
                lowerBounds1
        )

        val lowerBounds2 = ArrayList<Range>()
        lowerBounds2.add(Range(20, 100))
        lowerBounds2.add(Range(30, 92))
        lowerBounds2.add(Range(40, 89))
        lowerBounds2.add(Range(50, 85))
        lowerBounds2.add(Range(60, 78))
        lowerBounds2.add(Range(70, 70))
        lowerBounds2.add(Range(80, 60))
        lowerBounds2.add(Range(90, 55))
        lowerBounds2.add(Range(100, 50))
        defineColor(
                Color.RED.name,
                Range(-26, 18),
                lowerBounds2
        )

        val lowerBounds3 = ArrayList<Range>()
        lowerBounds3.add(Range(20, 100))
        lowerBounds3.add(Range(30, 93))
        lowerBounds3.add(Range(40, 88))
        lowerBounds3.add(Range(50, 86))
        lowerBounds3.add(Range(60, 85))
        lowerBounds3.add(Range(70, 70))
        lowerBounds3.add(Range(100, 70))
        defineColor(
                Color.ORANGE.name,
                Range(19, 46),
                lowerBounds3
        )

        val lowerBounds4 = ArrayList<Range>()
        lowerBounds4.add(Range(25, 100))
        lowerBounds4.add(Range(40, 94))
        lowerBounds4.add(Range(50, 89))
        lowerBounds4.add(Range(60, 86))
        lowerBounds4.add(Range(70, 84))
        lowerBounds4.add(Range(80, 82))
        lowerBounds4.add(Range(90, 80))
        lowerBounds4.add(Range(100, 75))

        defineColor(
                Color.YELLOW.name,
                Range(47, 62),
                lowerBounds4
        )

        val lowerBounds5 = ArrayList<Range>()
        lowerBounds5.add(Range(30, 100))
        lowerBounds5.add(Range(40, 90))
        lowerBounds5.add(Range(50, 85))
        lowerBounds5.add(Range(60, 81))
        lowerBounds5.add(Range(70, 74))
        lowerBounds5.add(Range(80, 64))
        lowerBounds5.add(Range(90, 50))
        lowerBounds5.add(Range(100, 40))

        defineColor(
                Color.GREEN.name,
                Range(63, 178),
                lowerBounds5
        )

        val lowerBounds6 = ArrayList<Range>()
        lowerBounds6.add(Range(20, 100))
        lowerBounds6.add(Range(30, 86))
        lowerBounds6.add(Range(40, 80))
        lowerBounds6.add(Range(50, 74))
        lowerBounds6.add(Range(60, 60))
        lowerBounds6.add(Range(70, 52))
        lowerBounds6.add(Range(80, 44))
        lowerBounds6.add(Range(90, 39))
        lowerBounds6.add(Range(100, 35))

        defineColor(
                Color.BLUE.name,
                Range(179, 257),
                lowerBounds6
        )

        val lowerBounds7 = ArrayList<Range>()
        lowerBounds7.add(Range(20, 100))
        lowerBounds7.add(Range(30, 87))
        lowerBounds7.add(Range(40, 79))
        lowerBounds7.add(Range(50, 70))
        lowerBounds7.add(Range(60, 65))
        lowerBounds7.add(Range(70, 59))
        lowerBounds7.add(Range(80, 52))
        lowerBounds7.add(Range(90, 45))
        lowerBounds7.add(Range(100, 42))

        defineColor(
                Color.PURPLE.name,
                Range(258, 282),
                lowerBounds7
        )

        val lowerBounds8 = ArrayList<Range>()
        lowerBounds8.add(Range(20, 100))
        lowerBounds8.add(Range(30, 90))
        lowerBounds8.add(Range(40, 86))
        lowerBounds8.add(Range(60, 84))
        lowerBounds8.add(Range(80, 80))
        lowerBounds8.add(Range(90, 75))
        lowerBounds8.add(Range(100, 73))

        defineColor(
                Color.PINK.name,
                Range(283, 334),
                lowerBounds8
        )
    }

}
