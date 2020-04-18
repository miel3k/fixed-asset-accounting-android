package com.tp.fixedassetaccounting.feature.asset.domain.repository

import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel

internal interface AssetDataSource {

    suspend fun getAssets(): List<AssetDomainModel>

    suspend fun addAsset(asset: AssetDataModel): AssetDomainModel?
}