package com.tp.fixedassetaccounting.core.view

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

class DecimalDigitsInputFilter(digitsBeforeZero: Int, digitsAfterZero: Int) : InputFilter {

    private val regex =
        "-?[0-9]{0,$digitsBeforeZero}+((([.,])[0-9]{0,$digitsAfterZero})?)||(([.,]))?"
    private val decimalPattern = Pattern.compile(regex)

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val replacement = source.subSequence(start, end).toString()
        val firstPart = dest.subSequence(0, dstart).toString()
        val secondPart = dest.subSequence(dend, dest.length).toString()
        val newVal = firstPart + replacement + secondPart
        val matcher = decimalPattern.matcher(newVal)

        return when {
            matcher.matches() -> null
            source.isEmpty() -> dest.subSequence(dstart, dend)
            else -> EMPTY_STRING
        }
    }

    companion object {
        private const val EMPTY_STRING = ""
    }
}