package com.tobiasschuerg.color.models;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public interface ColorModel<T> {

    @NonNull
    T fromColor(@ColorInt int color);

    @NonNull
    T from(@NonNull ColorModel other);

    @ColorInt
    int toColor();

    @NonNull
    HSLColor toHSL();

    @NonNull
    HSVColor toHSV();

}
