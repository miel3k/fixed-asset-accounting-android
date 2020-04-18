package com.tp.fixedassetaccounting.core.extension

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

@MainThread
inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<T> {
    val wrappedObserver = Observer<T> { observer -> onChanged.invoke(observer) }
    observe(owner, wrappedObserver)
    return wrappedObserver
}