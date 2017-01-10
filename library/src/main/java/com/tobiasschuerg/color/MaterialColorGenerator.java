package com.tobiasschuerg.color;

import android.content.Context;
import android.graphics.Color;

import com.tobiasschuerg.color.models.HEXColor;
import com.tobiasschuerg.color.models.HSLColor;

import java.util.ArrayList;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

@Deprecated
// TODO: mrege with MaterialColor
public class MaterialColorGenerator {

    private final Context context;

    public MaterialColorGenerator(Context context) {
        this.context = context;
    }

    public static ArrayList<HSLColor> returnMaterialDesignColorSet(String colorHex) {
        ArrayList<HSLColor> resultList = new ArrayList<>();
        HSLColor hsl = new HSLColor((new HEXColor(colorHex)));
        hsl.lightness(0.5f);
        double randomMid = randomWithRange(0.48, 0.52);

        resultList.add(hsl);
        return resultList;
    }

    public static double randomWithRange(double min, double max) {
        double range = Math.abs(max - min);
        return (Math.random() * range) + (min <= max ? min : max);
    }

    public static int colorInt(String hex) {
        return Color.parseColor(hex);
    }

}
