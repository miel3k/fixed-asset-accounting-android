package com.tp.fixedassetaccounting.feature.asset.data

import com.tp.fixedassetaccounting.feature.asset.FEATURE_NAME
import com.tp.fixedassetaccounting.feature.asset.data.repository.AssetRepository
import com.tp.fixedassetaccounting.feature.asset.data.repository.remote.AssetService
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${FEATURE_NAME}DataModule") {

    bind<AssetDataSource>() with singleton { AssetRepository(instance()) }

    bind() from singleton { instance<Retrofit>().create(AssetService::class.java) }
}