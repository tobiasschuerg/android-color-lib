package com.tobiasschuerg.color.helper

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.support.annotation.ColorInt
import android.support.design.widget.TextInputLayout
import android.widget.EditText

/**
 * Colors an [EditText] and its surrounding [TextInputLayout].
 *
 * Created by Tobias Schürg on 13.08.2016.
 */

object InputLayoutHelper {

    @Throws(NoSuchFieldException::class, IllegalAccessException::class)
    fun setInputTextLayoutColor(editText: EditText, til: TextInputLayout, @ColorInt color: Int) {

        editText.background.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        val fDefaultTextColor = TextInputLayout::class.java.getDeclaredField("mDefaultTextColor")
        fDefaultTextColor.isAccessible = true
        fDefaultTextColor.set(til, ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(color)))

        val fFocusedTextColor = TextInputLayout::class.java.getDeclaredField("mFocusedTextColor")
        fFocusedTextColor.isAccessible = true
        fFocusedTextColor.set(til, ColorStateList(arrayOf(intArrayOf(0)), intArrayOf(color)))
    }

}
