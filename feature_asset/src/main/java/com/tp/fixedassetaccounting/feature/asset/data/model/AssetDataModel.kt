package com.tp.fixedassetaccounting.feature.asset.data.model

import com.squareup.moshi.Json
import com.tp.fixedassetaccounting.feature.asset.domain.model.AmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import java.time.Instant

internal data class AssetDataModel(
    @field:Json(name = "assetName") val assetName: String,
    @field:Json(name = "documentName") val documentName: String,
    @field:Json(name = "categoryId") val categoryId: String,
    @field:Json(name = "purchaseDate") val purchaseDate: String,
    @field:Json(name = "purchaseAmountPLN") val purchaseAmountPLN: Double,
    @field:Json(name = "entryDate") val entryDate: String,
    @field:Json(name = "amortizationType") val amortizationType: String,
    @field:Json(name = "invalidationDate") val invalidationDate: String? = null
)

internal fun AssetDataModel.toDomainModel(): AssetDomainModel {
    return AssetDomainModel(
        assetName = assetName,
        code = documentName,
        categoryId = categoryId,
        purchaseDate = Instant.parse(purchaseDate),
        purchaseAmount = purchaseAmountPLN,
        entryDate = Instant.parse(entryDate),
        amortizationType = AmortizationType.from(amortizationType)
    )
}