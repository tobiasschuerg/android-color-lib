package com.tobiasschuerg.color;

import com.tobiasschuerg.color.random.RandomColor;

/**
 * Created by Tobias
 */
public class ColorCreator {

    /*
    Returns a random color
     */
    public static int getRandomColor() {
        return getRandomColors(1)[0];
    }

    /**
     * Returns an array of x random color values
     */
    public static int[] getRandomColors(int x) {
        RandomColor randomColor = new RandomColor();
        return randomColor.randomColor(x);
    }


    /*
       Returns an array of ten green colors
     */
    public static int[] getSimilarRandomColors(RandomColor.Color color, int count) {
        RandomColor randomColor = new RandomColor();
        return randomColor.random(color, count);
    }
}


