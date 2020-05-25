package com.tp.fixedassetaccounting.feature.asset.data.model

import com.squareup.moshi.Json
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetAmortizationDomainModel
import java.time.Instant

internal data class AssetAmortizationDataModel(
    @field:Json(name = "amortizationRateInPercentages") val amortizationRateInPercentages: Double,
    @field:Json(name = "amountAmortized") val amountAmortized: Double,
    @field:Json(name = "amortizationCalculationDate") val amortizationCalculationDate: String,
    @field:Json(name = "amortizationMeta") val amortizationMeta: AmortizationMetaDataModel
)

internal fun AssetAmortizationDataModel.toDomainModel(): AssetAmortizationDomainModel {
    return AssetAmortizationDomainModel(
        amortizationRate = amortizationRateInPercentages,
        amortizedAmount = amountAmortized,
        calculationDate = Instant.parse(amortizationCalculationDate),
        monthEntries = amortizationMeta.amortizationMonthEntries.map { it.toDomainModel() }
    )
}