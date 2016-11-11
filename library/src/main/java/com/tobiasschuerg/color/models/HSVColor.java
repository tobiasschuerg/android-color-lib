package com.tobiasschuerg.color.models;

import android.graphics.Color;

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


    @Override
    public HSVColor fromColor(int color) {
        float[] c = new float[3];
        Color.colorToHSV(color, c);
        return new HSVColor(c[0], c[1], c[2]);
    }

    @Override
    public int toColor() {
        return Color.HSVToColor(new float[]{hue, saturation, value});
    }

    @Override
    public HSVColor from(ColorModel other) {
        return fromColor(other.toColor());
    }
}
