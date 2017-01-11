package com.tobiasschuerg.color.models;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.graphics.ColorUtils;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public class HSLColor extends AbstractColor<HSLColor> {

    private float hue;
    private float saturation;
    private float lightness;

    public HSLColor(float h, float s, float l) {
        hue = h;
        saturation = s;
        lightness = l;
    }

    public HSLColor(ColorModel model) {
        from(model);
    }

    public HSLColor() {

    }

    @NonNull
    @Override
    public HSLColor fromColor(@ColorInt int color) {
        float[] c = new float[3];
        ColorUtils.colorToHSL(color, c);
        return new HSLColor(c[0], c[1], c[2]);
    }

    @Override
    public int toColor() {
        return ColorUtils.HSLToColor(new float[]{hue, saturation, lightness});
    }

    @NonNull
    @Override
    public HSLColor toHSL() {
        return this;
    }

    @NonNull
    @Override
    public HSVColor toHSV() {
        return new HSVColor(0, 0, 0).fromColor(toColor());
    }

    @NonNull
    @Override
    public HSLColor from(@NonNull ColorModel other) {
        return fromColor(other.toColor());
    }

    public HSLColor setLightness(float lightness) {
        this.lightness = lightness;
        return this;
    }

    public float setLightness() {
        return lightness;
    }
}
