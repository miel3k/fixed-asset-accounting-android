package com.tp.fixedassetaccounting.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object ViewModelSupplier {
    inline fun <reified T : ViewModel> of(fragment: Fragment, crossinline factory: () -> T): T {
        @Suppress("UNCHECKED_CAST")
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
        }

        return ViewModelProvider(fragment, vmFactory).get(T::class.java)
    }

    inline fun <reified T : ViewModel> of(
        fragmentActivity: FragmentActivity,
        crossinline factory: () -> T
    ): T {
        @Suppress("UNCHECKED_CAST")
        val vmFactory = object : ViewModelProvider.Factory {
            override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
        }

        return ViewModelProvider(fragmentActivity, vmFactory).get(T::class.java)
    }
}