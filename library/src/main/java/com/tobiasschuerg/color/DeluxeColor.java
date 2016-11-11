package com.tobiasschuerg.color;

import android.graphics.Color;

/**
 * Class for doing simple but very useful color manipulations.
 * <p/>
 * Created by Tobias Schürg
 */
public class DeluxeColor implements Comparable<DeluxeColor> {

    private static final double DEFAULT_FACTOR = 0.9;
    private float[] hsv = new float[3];
    private int color = 0;

    public DeluxeColor(String color) {
        setColor(android.graphics.Color.parseColor(color));
    }

    public DeluxeColor(int color) {
        setColor(color);
    }

    public void setColor(int color) {
        this.color = color;
        Color.colorToHSV(color, hsv);
    }

    public int getInt() {
        return Color.HSVToColor(hsv);
    }

    /**
     * Returns the hue, saturation, and value of the color.
     *
     * @return hsv array
     */
    public float[] getHSV() {
        return hsv;
    }

    /**
     * Returns black or white according to given color. Based on
     * http://www.nbdtech.com/Blog/archive/2008/04/27/Calculating-the-Perceived-Brightness-of-a-Color.aspx
     *
     * @return black or white
     */
    public int getReadableForegroundColor() {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        double brightness = Math.sqrt(0.241 * red * red + 0.691 * green * green + 0.068 * blue * blue);
        return brightness < 125 ? Color.WHITE : Color.BLACK;
    }

    @Override
    public int compareTo(DeluxeColor another) {
        int c0 = Double.compare(this.hsv[0], another.hsv[0]);
        if (c0 == 0) {
            int c1 = Double.compare(this.hsv[1], another.hsv[1]);
            if (c1 == 0) {
                return Double.compare(this.hsv[2], another.hsv[2]);
            } else {
                return c1;
            }
        } else {
            return c0;
        }
    }

    @Override
    public int hashCode() {
        return getInt();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeluxeColor) {
            return (((DeluxeColor) o).getInt() == getInt());
        }
        return super.equals(o);
    }

    /**
     * Returns the complimentary (opposite) color.
     * Based on http://www.java2s.com/Code/Android/2D-Graphics/Returnsthecomplimentaryoppositecolor.htm
     *
     * @return int RGB of compliment color
     */
    public int getComplimentColor() {
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
     * Converts an int RGB color representation into a hexadecimal {@link String}.
     * Based on http://www.java2s.com/Code/Android/2D-Graphics/Returnsthecomplimentaryoppositecolor.htm
     *
     * @param argbColor int RGB color
     * @return {@link String} hexadecimal color representation
     */
    public static String getHexStringForARGB(int argbColor) {
        String hexString = "#";
        hexString += ARGBToHex(Color.alpha(argbColor));
        hexString += ARGBToHex(Color.red(argbColor));
        hexString += ARGBToHex(Color.green(argbColor));
        hexString += ARGBToHex(Color.blue(argbColor));

        return hexString;
    }

    /**
     * Converts an int R, G, or B value into a hexadecimal {@link String}.
     * Based on http://www.java2s.com/Code/Android/2D-Graphics/Returnsthecomplimentaryoppositecolor.htm
     *
     * @param rgbVal int R, G, or B value
     * @return {@link String} hexadecimal value
     */
    private static String ARGBToHex(int rgbVal) {
        String hexReference = "0123456789ABCDEF";

        rgbVal = Math.max(0, rgbVal);
        rgbVal = Math.min(rgbVal, 255);
        rgbVal = Math.round(rgbVal);

        return String.valueOf(hexReference.charAt((rgbVal - rgbVal % 16) / 16) + "" + hexReference.charAt(rgbVal % 16));
    }

    /**
     * Based on http://www.java2s.com/Code/Android/2D-Graphics/brighteracolor.htm
     *
     * @param factor
     */
    public void brighten(double factor) {

        int r = Color.red(color);
        int b = Color.blue(color);
        int g = Color.green(color);

        if (r == 0 && b == 0 && g == 0) {
            setColor(Color.DKGRAY);
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

        setColor(Color.rgb(r, g, b));

    }

    public void darken(double factor) {
        int r = Color.red(color);
        int b = Color.blue(color);
        int g = Color.green(color);

        setColor(Color.rgb((int) (r * factor), (int) (g * factor), (int) (b * factor)));
    }

    public void darken() {
        darken(DEFAULT_FACTOR);
    }

    public void brighten() {
        brighten(DEFAULT_FACTOR);
    }
}
