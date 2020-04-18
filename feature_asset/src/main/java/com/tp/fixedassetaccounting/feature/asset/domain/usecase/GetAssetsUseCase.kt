package com.tp.fixedassetaccounting.feature.asset.domain.usecase

import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetRepository
import java.util.*

internal class GetAssetsUseCase {

    suspend fun execute(): List<AssetDomainModel> {
        return listOf(
            AssetDomainModel(UUID.randomUUID().toString(), "Notebook", "00000000"),
            AssetDomainModel(UUID.randomUUID().toString(), "Monitor", "11111111")
        )
        //return assetRepository.getAssets()
    }
}