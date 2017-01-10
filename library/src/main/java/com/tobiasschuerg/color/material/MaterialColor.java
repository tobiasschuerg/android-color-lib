package com.tobiasschuerg.color.material;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.tobiasschuerg.color.DeluxeColorCreator;
import com.tobiasschuerg.color.models.AbstractColor;
import com.tobiasschuerg.color.models.ColorModel;
import com.tobiasschuerg.color.models.HSLColor;
import com.tobiasschuerg.color.models.HSVColor;

/**
 * Internally uses the {@link HSLColor} color model.
 * <p>
 * Created by Tobias Schürg on 13.08.2016.
 * <p>
 * Inspired by https://stackoverflow.com/questions/28012185/what-are-the-ways-to-programmatically-generate-material-design-color-sets
 */

public class MaterialColor extends AbstractColor<MaterialColor> {

    @NonNull
    private final HSLColor hsl;

    @NonNull
    private Float initialLightness;

    public MaterialColor(String hex) {
        this(Color.parseColor(hex));
    }

    public MaterialColor(@ColorInt int color) {
        this.hsl = new HSLColor(color);
        this.initialLightness = hsl.lightness();
        if (initialLightness > 0.6f || initialLightness < 0.4f) {
            // Log.w("MaterialColor", "Lightness (" + initialLightness + ")  not optimal");
            if (initialLightness < 0.1) {
                initialLightness = 0.25f;
            }
        }
    }

    public static MaterialColor random(boolean onlyOfficialColors) {
        return new MaterialColor(DeluxeColorCreator.getRandomColor());
    }

    /**
     * 79% light
     */
    public int get100() {
        return hsl.lightness(initialLightness * 1.79f).toColor();
    }

    /**
     * 33% light
     */
    public int get300() {
        return hsl.lightness(initialLightness * 1.333f).toColor();
    }

    public int get500() {
        return hsl.lightness(initialLightness).toColor();
    }

    /**
     * 42% dark
     */
    public int get700() {
        return hsl.lightness(initialLightness * 0.58f).toColor();
    }

//    public int getTextBlackWhite() {
//        return hsl.getForeGroundColor(AbstractColor.ColorPreference.WHITE);
//    }

    public int get900() {
        return hsl.lightness(0.1f).toColor();
    }

    private HSLColor toHSL() {
        return hsl;
    }

    @NonNull
    @Override
    public MaterialColor fromColor(int color) {
        hsl.fromColor(color);
        return this;
    }

    @NonNull
    @Override
    public MaterialColor from(ColorModel other) {
        hsl.from(other);
        return this;
    }

    @Override
    public int toColor() {
        return hsl.toColor();
    }

    @NonNull
    @Override
    public HSVColor toHSV() {
        return hsl.toHSV();
    }
}
