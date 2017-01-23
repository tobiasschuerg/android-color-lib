package com.tobiasschuerg.color.helper;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.support.annotation.ColorInt;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.lang.reflect.Field;

/**
 * Created by Tobias Schürg on 13.08.2016.
 */

public class InputLayoutHelper {

    /**
     * Colors an {@link EditText} and its surrounding {@link TextInputLayout}.
     */
    public static void setInputTextLayoutColor(EditText editText, TextInputLayout til, @ColorInt int color)
            throws NoSuchFieldException, IllegalAccessException {

        editText.getBackground().setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

        Field fDefaultTextColor = TextInputLayout.class.getDeclaredField("mDefaultTextColor");
        fDefaultTextColor.setAccessible(true);
        fDefaultTextColor.set(til, new ColorStateList(new int[][]{{0}}, new int[]{color}));

        Field fFocusedTextColor = TextInputLayout.class.getDeclaredField("mFocusedTextColor");
        fFocusedTextColor.setAccessible(true);
        fFocusedTextColor.set(til, new ColorStateList(new int[][]{{0}}, new int[]{color}));
    }

}
