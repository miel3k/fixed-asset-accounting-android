package com.tp.fixedassetaccounting.feature.asset.domain.model

import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import java.time.Instant

internal data class AssetDomainModel(val id: String, val name: String, val code: String)

internal fun AssetDomainModel.toDataModel(): AssetDataModel {
    return AssetDataModel(
        assetId = id,
        serialNumber = code,
        categoryId = "testCategoryId",
        quantity = 1,
        purchaseDate = Instant.now().toString()
    )
}