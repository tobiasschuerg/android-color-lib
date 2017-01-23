package com.tobiasschuerg.color.material;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import com.tobiasschuerg.color.ColorCreator;
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
        this.hsl = new HSLColor().fromColor(color);
        this.initialLightness = hsl.setLightness();
        if (initialLightness > 0.6f || initialLightness < 0.4f) {
            // Log.w("MaterialColor", "Lightness (" + initialLightness + ")  not optimal");
            if (initialLightness < 0.1) {
                initialLightness = 0.25f;
            }
        }
    }

    public static MaterialColor random(boolean onlyOfficialColors) {
        return new MaterialColor(ColorCreator.getRandomColor());
    }

    /**
     * 79% light
     */
    public int get100() {
        return hsl.setLightness(initialLightness * 1.79f).toColor();
    }

    /**
     * 33% light
     */
    public int get300() {
        return hsl.setLightness(initialLightness * 1.333f).toColor();
    }

    public int get500() {
        return hsl.setLightness(initialLightness).toColor();
    }

    /**
     * 42% dark
     */
    public int get700() {
        return hsl.setLightness(initialLightness * 0.58f).toColor();
    }

    /**
     * Estimates the best fitting text color if this color is used as background to draw on.
     *
     * @return black or white
     */
    public int getTextBlackWhite() {
        return hsl.getForeGroundColor(AbstractColor.ColorPreference.WHITE);
    }

    public int get900() {
        return hsl.setLightness(0.1f).toColor();
    }

    @NonNull
    public HSLColor toHSL() {
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
    public MaterialColor from(@NonNull ColorModel other) {
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
