package com.tobiasschuerg.color.models;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public abstract class AbstractColor<T> implements ColorModel<T>, Comparable<AbstractColor> {

    public enum ColorPreference {NONE, BLACK, WHITE}

    /**
     * Returns black or white according to given color. Based on
     * http://www.nbdtech.com/Blog/archive/2008/04/27/Calculating-the-Perceived-Brightness-of-a-Color.aspx
     *
     * @return black or white
     */
    public double getBrightness() {
        int color = toColor();
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        double brightness = Math.sqrt(0.241 * red * red + 0.691 * green * green + 0.068 * blue * blue);
        // Log.d("Color", "Brightness: " + brightness);
        return brightness;
    }

    public int getForeGroundColor() {
        return getForeGroundColor(ColorPreference.NONE);
    }

    /**
     * See {@link #getBrightness()}
     *
     * @param cp preference: none, black or white
     * @return #Color.WHITE or #Color.BLACK
     */
    public int getForeGroundColor(ColorPreference cp) {
        switch (cp) {
            default:
            case NONE:
                return getBrightness() < 130 ? Color.WHITE : Color.BLACK;

            case BLACK:
                return getBrightness() < 75 ? Color.WHITE : Color.BLACK;

            case WHITE:
                return getBrightness() < 180 ? Color.WHITE : Color.BLACK;
        }
    }

    /**
     * Returns the complimentary (opposite) color.
     * Based on http://www.java2s.com/Code/Android/2D-Graphics/Returnsthecomplimentaryoppositecolor.htm
     *
     * @return int RGB of compliment color
     */
    public int getComplimentColor() {
        int color = toColor();
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);

        // find compliments
        red = (~red) & 0xff;
        blue = (~blue) & 0xff;
        green = (~green) & 0xff;

        return Color.argb(alpha, red, green, blue);
    }


    @Override
    public int compareTo(@NonNull AbstractColor another) {
        HSVColor hsv1 = new HSVColor().fromColor(this.toColor());
        HSVColor hsv2 = new HSVColor().fromColor(another.toColor());

        int c0 = Double.compare(hsv1.hue(), hsv2.hue());
        if (c0 == 0) {
            int c1 = Double.compare(hsv1.saturation(), hsv2.saturation());
            if (c1 == 0) {
                return Double.compare(hsv1.value(), hsv2.value());
            } else {
                return c1;
            }
        } else {
            return c0;
        }
    }

    @Override
    public int hashCode() {
        return toColor();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AbstractColor) {
            return (((AbstractColor) o).toColor() == toColor());
        }
        return super.equals(o);
    }

}
