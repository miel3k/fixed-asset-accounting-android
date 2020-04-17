package com.tp.feature.asset

import com.tp.feature.asset.data.dataModule
import com.tp.feature.asset.domain.domainModule
import com.tp.feature.asset.presentation.presentationModule
import com.tp.fixedassetaccounting.di.KodeinModuleProvider
import org.kodein.di.Kodein

internal const val FEATURE_NAME = "Asset"

object FeatureKodeinModule : KodeinModuleProvider {

    override val kodeinModule = Kodein.Module("${FEATURE_NAME}Module") {
        import(presentationModule)
        import(domainModule)
        import(dataModule)
    }
}