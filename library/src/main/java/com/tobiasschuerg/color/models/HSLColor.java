package com.tobiasschuerg.color.models;

import android.support.v4.graphics.ColorUtils;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public class HSLColor implements ColorModel<HSLColor> {

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


    @Override
    public HSLColor fromColor(int color) {
        float[] c = new float[3];
        ColorUtils.colorToHSL(color, c);
        return new HSLColor(c[0], c[1], c[2]);
    }

    @Override
    public int toColor() {
        return ColorUtils.HSLToColor(new float[]{hue, saturation, lightness});
    }

    @Override
    public HSLColor from(ColorModel other) {
        return fromColor(other.toColor());
    }

    public void lightness(float lightness) {
        this.lightness = lightness;
    }
}
