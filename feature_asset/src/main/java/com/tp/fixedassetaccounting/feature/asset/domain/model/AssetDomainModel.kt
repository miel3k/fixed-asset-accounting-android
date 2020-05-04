package com.tp.fixedassetaccounting.feature.asset.domain.model

import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import java.time.Instant

internal data class AssetDomainModel(
    val assetName: String,
    val code: String,
    val categoryId: String,
    val purchaseDate: Instant,
    val purchaseAmount: Double,
    val entryDate: Instant,
    val amortizationType: AmortizationType
)

internal fun AssetDomainModel.toDataModel(): AssetDataModel {
    return AssetDataModel(
        assetName = assetName,
        documentName = code,
        categoryId = categoryId,
        purchaseDate = purchaseDate.toString(),
        purchaseAmountPLN = purchaseAmount,
        entryDate = Instant.now().toString(),
        amortizationType = amortizationType.name
    )
}