package com.tp.fixedassetaccounting.feature.asset.data

import androidx.room.Room
import com.tp.fixedassetaccounting.feature.asset.FEATURE_NAME
import com.tp.fixedassetaccounting.feature.asset.data.repository.AssetRepository
import com.tp.fixedassetaccounting.feature.asset.data.repository.local.AssetDatabase
import com.tp.fixedassetaccounting.feature.asset.data.repository.remote.AssetService
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${FEATURE_NAME}DataModule") {

    bind<AssetDatabase>() with singleton {
        Room.databaseBuilder(
            instance(),
            AssetDatabase::class.java,
            "ASSET_DATABASE.db"
        ).build()
    }

    bind<AssetDataSource>() with singleton { AssetRepository(instance(), instance()) }

    bind() from singleton { instance<Retrofit>().create(AssetService::class.java) }

    bind() from singleton { instance<AssetDatabase>().assetDao() }
}