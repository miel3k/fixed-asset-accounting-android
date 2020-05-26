package com.tp.fixedassetaccounting.feature.asset.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.tp.fixedassetaccounting.feature.asset.domain.model.AmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetCategory
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import java.time.Instant

@Entity(tableName = "Asset")
internal data class AssetDataModel(
    @PrimaryKey
    @ColumnInfo(name = "assetName")
    @field:Json(name = "assetName") val assetName: String,
    @ColumnInfo(name = "documentName")
    @field:Json(name = "documentName") val documentName: String,
    @ColumnInfo(name = "categoryId")
    @field:Json(name = "categoryId") val categoryId: String,
    @ColumnInfo(name = "purchaseDate")
    @field:Json(name = "purchaseDate") val purchaseDate: String,
    @ColumnInfo(name = "purchaseAmountPLN")
    @field:Json(name = "purchaseAmountPLN") val purchaseAmountPLN: Double,
    @ColumnInfo(name = "entryDate")
    @field:Json(name = "entryDate") val entryDate: String,
    @ColumnInfo(name = "digressiveAmortizationCoefficient")
    @field:Json(name = "digressiveAmortizationCoefficient") val digressiveAmortizationCoefficient: Double,
    @ColumnInfo(name = "amortizationType")
    @field:Json(name = "amortizationType") val amortizationType: String,
    @ColumnInfo(name = "invalidationDate")
    @field:Json(name = "invalidationDate") val invalidationDate: String? = null
)

internal fun AssetDataModel.toDomainModel(): AssetDomainModel {
    return AssetDomainModel(
        assetName = assetName,
        documentName = documentName,
        assetCategory = AssetCategory.from(categoryId),
        purchaseDate = Instant.parse(purchaseDate),
        purchaseAmount = purchaseAmountPLN,
        entryDate = Instant.parse(entryDate),
        coefficient = digressiveAmortizationCoefficient,
        amortizationType = AmortizationType.from(amortizationType)
    )
}