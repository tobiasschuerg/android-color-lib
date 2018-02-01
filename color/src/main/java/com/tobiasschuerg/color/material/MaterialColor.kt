package com.tobiasschuerg.color.material

import android.graphics.Color
import android.support.annotation.ColorInt

import com.tobiasschuerg.color.ColorCreator
import com.tobiasschuerg.color.models.AbstractColor
import com.tobiasschuerg.color.models.ColorModel
import com.tobiasschuerg.color.models.HSLColor
import com.tobiasschuerg.color.models.HSVColor

/**
 * Internally uses the [HSLColor] color model.
 *
 *
 * Created by Tobias Schürg on 13.08.2016.
 *
 *
 * Inspired by https://stackoverflow.com/questions/28012185/what-are-the-ways-to-programmatically-generate-material-design-color-sets
 */

class MaterialColor(@ColorInt color: Int) : AbstractColor<MaterialColor>() {

    private val hsl: HSLColor

    private var initialLightness: Float

    /**
     * 79% light
     */
    fun get100(): Int = hsl.copy(lightness = initialLightness * 1.79f).toColor()

    /**
     * 33% light
     */
    fun get300(): Int = hsl.copy(lightness = initialLightness * 1.333f).toColor()

    fun get500(): Int = hsl.copy(lightness = initialLightness).toColor()

    /**
     * 42% dark
     */
    fun get700(): Int = hsl.copy(lightness = initialLightness * 0.58f).toColor()

    /**
     * Estimates the best fitting text color if this color is used as background to draw on.
     *
     * @return black or white
     */
    val textBlackWhite: Int
        get() = hsl.getForeGroundColor(AbstractColor.ColorPreference.WHITE)

    fun get900(): Int = hsl.copy(lightness = 0.1f).toColor()

    constructor(hex: String) : this(Color.parseColor(hex)) {}

    init {
        this.hsl = HSLColor.fromColor(color)
        this.initialLightness = hsl.lightness
        if (initialLightness > 0.6f || initialLightness < 0.4f) {
            // Log.w("MaterialColor", "Lightness (" + initialLightness + ")  not optimal");
            if (initialLightness < 0.1) {
                initialLightness = 0.25f
            }
        }
    }

    override fun toHSL(): HSLColor {
        return hsl
    }

    override fun toColor(): Int {
        return hsl.toColor()
    }

    override fun toHSV(): HSVColor {
        return hsl.toHSV()
    }

    companion object {

        fun random(onlyOfficialColors: Boolean): MaterialColor {
            return MaterialColor(ColorCreator.randomColor())
        }

        fun from(other: ColorModel<*>): MaterialColor {
            return MaterialColor(other.toColor())
        }
    }
}
