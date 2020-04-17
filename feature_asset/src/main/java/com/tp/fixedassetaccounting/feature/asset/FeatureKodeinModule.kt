package com.tp.fixedassetaccounting.feature.asset

import com.tp.fixedassetaccounting.di.KodeinModuleProvider
import com.tp.fixedassetaccounting.feature.asset.data.dataModule
import com.tp.fixedassetaccounting.feature.asset.domain.domainModule
import com.tp.fixedassetaccounting.feature.asset.presentation.presentationModule
import org.kodein.di.Kodein

internal const val FEATURE_NAME = "Asset"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${FEATURE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}