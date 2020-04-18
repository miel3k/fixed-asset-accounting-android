package com.tp.fixedassetaccounting.core.utils

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

inline fun <reified T : Any> getDiffCallback(
    noinline compare: ((oldItem: T, newItem: T) -> Boolean)? = null
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) =
        compare?.invoke(oldItem, newItem) ?: false

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}