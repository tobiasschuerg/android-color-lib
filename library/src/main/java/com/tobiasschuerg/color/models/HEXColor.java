package com.tobiasschuerg.color.models;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public class HEXColor implements ColorModel<HEXColor> {

    private final String hex;
    private final int color;

    public HEXColor(String hex) {
        this.hex = hex;
        this.color = Color.parseColor(hex);
    }

    @Override
    public HEXColor fromColor(int color) {
        return new HEXColor(String.format("#%06X", (0xFFFFFF & color)));

    }

    @Override
    public HEXColor from(ColorModel other) {
        return fromColor(other.toColor());
    }

    @Override
    public int toColor() {
        return color;
    }
}
