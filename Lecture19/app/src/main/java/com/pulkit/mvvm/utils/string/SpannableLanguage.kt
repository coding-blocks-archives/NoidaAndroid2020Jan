package com.pulkit.mvvm.utils.string

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import com.google.android.material.resources.TextAppearance
import com.pulkit.mvvm.R

class SpannableLanguage(context: Context, name: String) : SpannableString(name) {
    init {
        setSpan(
            TextAppearanceSpan(
                context,
                R.style.TextAppearance_MaterialComponents_Body1
            ),
            0,
            name.length,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
    }
}