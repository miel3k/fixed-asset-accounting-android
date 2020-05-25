package com.tp.fixedassetaccounting.feature.asset.domain.usecase

import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetAmortizationDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource
import java.time.Instant

internal class GetAssetAmortizationUseCase(private val assetRepository: AssetDataSource) {

    suspend fun execute(assetName: String, date: Instant): AssetAmortizationDomainModel? {
        return assetRepository.getAssetAmortization(assetName, date.toString())
    }
}