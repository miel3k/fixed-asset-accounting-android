package com.tp.fixedassetaccounting.feature.asset.domain.usecase

import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource

internal class GetAssetsUseCase(private val assetRepository: AssetDataSource) {

    suspend fun execute(): List<AssetDomainModel> {
        return assetRepository.getAssets()
    }
}