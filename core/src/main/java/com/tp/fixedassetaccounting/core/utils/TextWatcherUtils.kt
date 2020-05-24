package com.tp.fixedassetaccounting.core.utils

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.widget.EditText
import com.tp.fixedassetaccounting.core.view.DecimalDigitsInputFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object TextWatcherUtils {

    const val DEFAULT_TIMEOUT_MILLIS = 50L

    fun getOnTextChangedListener(observer: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit): TextWatcher {
        return object : TextWatcher, CoroutineScope {
            override val coroutineContext = Dispatchers.Main
            private var searchFor = ""

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s.toString().trim().let {
                    if (it == searchFor) return
                    searchFor = it
                    launch {
                        delay(DEFAULT_TIMEOUT_MILLIS)
                        if (it != searchFor) return@launch
                        observer(s, start, before, count)
                    }
                }
            }
        }
    }

    fun EditText.addCurrencyFilter() {
        val digitsBeforeZero = 8
        val digitsAfterZero = 2
        filters = arrayOf<InputFilter>(DecimalDigitsInputFilter(digitsBeforeZero, digitsAfterZero))
    }
}

fun EditText.setTextSilently(newText: String, textWatcher: TextWatcher) {
    removeTextChangedListener(textWatcher)
    var selectionEnd = selectionEnd
    val textLength = newText.length
    if (textLength < selectionEnd) selectionEnd = textLength
    setText(newText)
    setSelection(selectionEnd)
    addTextChangedListener(textWatcher)
}