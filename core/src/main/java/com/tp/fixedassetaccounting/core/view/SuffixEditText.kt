package com.tp.fixedassetaccounting.core.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class SuffixEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    AppCompatEditText(context, attrs) {

    var suffix = ""
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val suffixPosition = paint.measureText("$text ") + paddingLeft
        canvas.drawText(suffix, suffixPosition, baseline.toFloat(), paint)
    }
}