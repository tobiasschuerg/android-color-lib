package com.tobiasschuerg.color.material;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;

import com.tobiasschuerg.color.DeluxeColorCreator;
import com.tobiasschuerg.color.models.AbstractColor;
import com.tobiasschuerg.color.models.HSLColor;

/**
 * Created by Tobias Schürg on 13.08.2016.
 * <p>
 * Inspired by https://stackoverflow.com/questions/28012185/what-are-the-ways-to-programmatically-generate-material-design-color-sets
 */

public class MaterialColor implements Comparable<MaterialColor> {

    private final HSLColor hsl;
    private float initialLightness;

    public MaterialColor(int color) {
        this.hsl = new HSLColor().fromColor(color);
        this.initialLightness = hsl.lightness();
        if (initialLightness > 0.6f || initialLightness < 0.4f) {
            // Log.w("MaterialColor", "Lightness (" + initialLightness + ")  not optimal");
            if (initialLightness < 0.1) {
                initialLightness = 0.25f;
            }
        }
    }

    public MaterialColor(String hex) {
        this(Color.parseColor(hex));
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

    public int get900() {
        return hsl.lightness(0.1f).toColor();
    }

    public int getTextBlackWhite() {
        return hsl.getForeGroundColor(AbstractColor.ColorPreference.WHITE);
    }

    public static MaterialColor random(boolean onlyOfficialColors) {
        return new MaterialColor(DeluxeColorCreator.getRandomColor());
    }

    @Override
    public int compareTo(@NonNull MaterialColor materialColor) {
        return hsl.compareTo(materialColor.toHSL());
    }

    private HSLColor toHSL() {
        return hsl;
    }
}
