package com.tobiasschuerg.color.models;

import android.graphics.Color;
import android.support.annotation.NonNull;

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


    /**
     * Compares this color to another using {@link HSVColor} internally.
     */
    @Override
    public int compareTo(@NonNull AbstractColor another) {
        HSVColor hsv1 = this.toHSV();
        HSVColor hsv2 = another.toHSV();

        int c0 = Double.compare(hsv1.getHue(), hsv2.getHue());
        if (c0 == 0) {
            int c1 = Double.compare(hsv1.getSaturation(), hsv2.getSaturation());
            if (c1 == 0) {
                return Double.compare(hsv1.getValue(), hsv2.getValue());
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

    /**
     * Based on http://www.java2s.com/Code/Android/2D-Graphics/brighteracolor.htm
     *
     * @param factor
     */
    public void brighten(double factor) {

        int r = Color.red(toColor());
        int b = Color.blue(toColor());
        int g = Color.green(toColor());

        if (r == 0 && b == 0 && g == 0) {
            fromColor(Color.DKGRAY);
        }

        if (r < 3 && r != 0) {
            r = 3;
        } else {
            r = (int) (r / factor);
            r = (r > 255) ? 255 : r;
        }

        if (b < 3 && b != 0) {
            b = 3;
        } else {
            b = (int) (b / .7);
            b = (b > 255) ? 255 : b;
        }

        if (g < 3 && g != 0) {
            g = 3;
        } else {
            g = (int) (g / .7);
            g = (g > 255) ? 255 : g;
        }

        fromColor(Color.rgb(r, g, b));

    }

    public void darken(double factor) {
        int r = Color.red(toColor());
        int b = Color.blue(toColor());
        int g = Color.green(toColor());

        fromColor(Color.rgb((int) (r * factor), (int) (g * factor), (int) (b * factor)));
    }

    public void darken() {
        darken(0.9);
    }

    public void brighten() {
        brighten(0.9);
    }

}
