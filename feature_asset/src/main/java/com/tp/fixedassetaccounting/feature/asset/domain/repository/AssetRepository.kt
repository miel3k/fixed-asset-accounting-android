package com.tp.fixedassetaccounting.feature.asset.domain.repository

import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel

internal interface AssetRepository {

    suspend fun getAssets(): List<AssetDomainModel>
}