package com.tobiasschuerg.color.helper

import android.content.res.ColorStateList
import android.widget.EditText
import androidx.annotation.ColorInt
import androidx.core.view.ViewCompat
import com.google.android.material.textfield.TextInputLayout


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

