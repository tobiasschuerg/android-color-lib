package com.tobiasschuerg.color.models;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public class HSVColor implements ColorModel<HSVColor> {

    private float hue;
    private float saturation;
    private float value;

    public HSVColor(float h, float s, float v) {
        hue = h;
        saturation = s;
        value = v;
    }

    public float getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getValue() {
        return value;
    }


    @NonNull
    @Override
    public HSVColor fromColor(int color) {
        float[] c = new float[3];
        Color.colorToHSV(color, c);
        return new HSVColor(c[0], c[1], c[2]);
    }

    @Override
    @ColorInt
    public int toColor() {
        return Color.HSVToColor(new float[]{hue, saturation, value});
    }

    @NonNull
    @Override
    public HSVColor toHSV() {
        return this;
    }

    @NonNull
    @Override
    public HSVColor from(ColorModel other) {
        return fromColor(other.toColor());
    }
}
