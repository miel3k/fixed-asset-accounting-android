package com.tp.fixedassetaccounting

import android.app.Application
import com.tp.fixedassetaccounting.di.KoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FixedAssetAccountingApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@FixedAssetAccountingApplication)
            modules(KoinModules().getAllModules())
        }
    }
}