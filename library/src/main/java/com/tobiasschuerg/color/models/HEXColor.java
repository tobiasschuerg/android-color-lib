package com.tobiasschuerg.color.models;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * Hue
 * Saturation
 * Value / Brightness
 * <p>
 * Created by Tobias Schürg on 13.08.2016.
 */

public class HEXColor implements ColorModel<HEXColor> {

    @NonNull
    private final String hex;

    @ColorInt
    private final int color;

    public HEXColor(@NonNull String hex) {
        this.hex = hex;
        this.color = Color.parseColor(hex);
    }

    @NonNull
    @Override
    public HEXColor fromColor(int color) {
        return new HEXColor(String.format("#%06X", (0xFFFFFF & color)));

    }

    @NonNull
    @Override
    public HEXColor from(@NonNull ColorModel other) {
        return fromColor(other.toColor());
    }

    @Override
    public int toColor() {
        return color;
    }

    @NonNull
    @Override
    public HSLColor toHSL() {
        return new HSLColor().fromColor(toColor());
    }

    @NonNull
    @Override
    public HSVColor toHSV() {
        return new HSVColor(0, 0, 0).fromColor(color);
    }
}
