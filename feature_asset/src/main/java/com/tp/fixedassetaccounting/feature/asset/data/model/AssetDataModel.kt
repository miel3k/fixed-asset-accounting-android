package com.tp.fixedassetaccounting.feature.asset.data.model

import com.squareup.moshi.Json
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel

internal data class AssetDataModel(
    @field:Json(name = "assetId") val assetId: String,
    @field:Json(name = "serialNumber") val serialNumber: String,
    @field:Json(name = "categoryId") val categoryId: String,
    @field:Json(name = "quantity") val quantity: Int,
    @field:Json(name = "purchaseDate") val purchaseDate: String?
)

internal fun AssetDataModel.toDomainModel(): AssetDomainModel {
    return AssetDomainModel(
        id = assetId,
        name = assetId,
        code = serialNumber
    )
}