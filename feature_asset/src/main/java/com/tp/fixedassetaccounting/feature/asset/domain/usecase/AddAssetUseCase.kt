package com.tp.fixedassetaccounting.feature.asset.domain.usecase

import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.toDataModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource

internal class AddAssetUseCase(private val assetRepository: AssetDataSource) {

    suspend fun execute(asset: AssetDomainModel): AssetDomainModel? {
        return assetRepository.addAsset(asset.toDataModel())
    }
}