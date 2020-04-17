package com.tp.fixedassetaccounting

import android.app.Application
import com.tp.fixedassetaccounting.di.KodeinModules
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class FixedAssetAccountingApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@FixedAssetAccountingApplication))
        importAll(KodeinModules.getAllModules())
    }

    override fun onCreate() {
        super.onCreate()
    }
}