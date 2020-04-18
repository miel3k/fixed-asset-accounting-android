package com.tp.fixedassetaccounting.feature.asset.domain.usecase

import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource

internal class GetAssetsUseCase(private val assetRepository: AssetDataSource) {

    suspend fun execute(): List<AssetDomainModel> {
//        return listOf(
//            AssetDomainModel(UUID.randomUUID().toString(), "Notebook", "00000000"),
//            AssetDomainModel(UUID.randomUUID().toString(), "Macbook", "11111111")
//        )
        return assetRepository.getAssets()
    }
}