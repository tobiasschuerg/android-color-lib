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

    /**
     * @return Hue [0 .. 360)
     */
    public float getHue() {
        return hue;
    }

    /**
     * @param hue Hue [0 .. 360)
     */
    public HSVColor setHue(float hue) {
        this.hue = hue;
        return this;
    }

    /**
     * @return Saturation [0...1]
     */
    public float getSaturation() {
        return saturation;
    }

    /**
     * @param saturation Saturation [0...1]
     */
    public HSVColor setSaturation(float saturation) {
        this.saturation = saturation;
        return this;
    }

    /**
     * @return Value [0...1]
     */
    public float getValue() {
        return value;
    }

    /**
     * @param value Value [0...1]
     */
    public HSVColor setValue(float value) {
        this.value = value;
        return this;
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
    public HSLColor toHSL() {
        return new HSLColor().fromColor(toColor());
    }

    @NonNull
    @Override
    public HSVColor toHSV() {
        return this;
    }

    @NonNull
    @Override
    public HSVColor from(@NonNull ColorModel other) {
        return fromColor(other.toColor());
    }
}
