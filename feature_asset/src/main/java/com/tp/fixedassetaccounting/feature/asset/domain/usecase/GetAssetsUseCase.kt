package com.tp.fixedassetaccounting.feature.asset.domain.usecase

import androidx.lifecycle.LiveData
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource

internal class GetAssetsUseCase(private val assetRepository: AssetDataSource) {

    suspend fun get(): LiveData<List<AssetDataModel>> {
        return assetRepository.getAssets()
    }

    suspend fun load(): List<AssetDomainModel> {
        return assetRepository.loadAssets()
    }
}