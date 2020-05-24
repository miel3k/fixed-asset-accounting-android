package com.tp.fixedassetaccounting.feature.asset.domain.model

import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import java.time.Instant

internal data class AssetDomainModel(
    val assetName: String,
    val documentName: String,
    val assetCategory: AssetCategory,
    val purchaseDate: Instant,
    val purchaseAmount: Double,
    val entryDate: Instant,
    val coefficient: Double,
    val amortizationType: AmortizationType
)

internal fun AssetDomainModel.toDataModel(): AssetDataModel {
    return AssetDataModel(
        assetName = assetName,
        documentName = documentName,
        categoryId = assetCategory.id.toString(),
        purchaseDate = purchaseDate.toString(),
        purchaseAmountPLN = purchaseAmount,
        entryDate = Instant.now().toString(),
        digressiveAmortizationCoefficient = coefficient,
        amortizationType = amortizationType.name
    )
}