package com.tobiasschuerg.color.helper

import android.content.res.ColorStateList
import android.support.annotation.ColorInt
import android.support.design.widget.TextInputLayout
import android.support.v4.view.ViewCompat
import android.widget.EditText


/**
 * Colors an [EditText] and its surrounding [TextInputLayout].
 *
 * Created by Tobias Schürg on 13.08.2016.
 */

@Throws(NoSuchFieldException::class, IllegalAccessException::class)
fun TextInputLayout.setAccentColor(@ColorInt color: Int) {
    // update underline color
    editText?.apply {
        val colorStateList = ColorStateList.valueOf(color)
        ViewCompat.setBackgroundTintList(this, colorStateList)
    }

    // update text color
    defaultHintTextColor = ColorStateList.valueOf(color)
}

