package com.tp.fixedassetaccounting.feature.asset.domain.usecase

import androidx.lifecycle.LiveData
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetAmortizationDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource
import java.time.Instant

internal class GetAssetAmortizationUseCase(private val assetRepository: AssetDataSource) {

    suspend fun get(assetName: String): LiveData<AssetDataModel> {
        return assetRepository.getAsset(assetName)
    }

    suspend fun load(assetName: String, date: Instant): AssetAmortizationDomainModel? {
        return assetRepository.getAssetAmortization(assetName, date.toString())
    }
}