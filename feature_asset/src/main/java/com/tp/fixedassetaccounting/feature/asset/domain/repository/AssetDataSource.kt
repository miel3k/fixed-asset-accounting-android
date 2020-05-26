package com.tp.fixedassetaccounting.feature.asset.domain.repository

import androidx.lifecycle.LiveData
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetAmortizationDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel

internal interface AssetDataSource {

    suspend fun loadAssets(): List<AssetDomainModel>

    suspend fun getAssets(): LiveData<List<AssetDataModel>>

    suspend fun getAsset(assetName: String): LiveData<AssetDataModel>

    suspend fun addAsset(asset: AssetDataModel): AssetDomainModel?

    suspend fun getAssetAmortization(
        assetName: String,
        date: String
    ): AssetAmortizationDomainModel?
}