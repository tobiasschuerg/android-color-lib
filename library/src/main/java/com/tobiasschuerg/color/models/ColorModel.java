package com.tobiasschuerg.color.models;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public interface ColorModel<T> {

    T fromColor(int color);

    T from(ColorModel other);

    int toColor();

}
