package com.tp.fixedassetaccounting.feature.asset.data.model

import com.squareup.moshi.Json
import com.tp.fixedassetaccounting.feature.asset.domain.model.AmortizationMonthEntryDomainModel
import java.time.Instant

internal data class AmortizationMonthEntryDataModel(
    @field:Json(name = "monthStartDate") val monthStartDate: String,
    @field:Json(name = "amortizationType") val amortizationType: String,
    @field:Json(name = "amortizedAmountPLN") val amortizedAmountPLN: Double,
    @field:Json(name = "amortizationBasePLN") val amortizationBasePLN: Double
)

internal fun AmortizationMonthEntryDataModel.toDomainModel(): AmortizationMonthEntryDomainModel {
    return AmortizationMonthEntryDomainModel(
        monthStartDate = Instant.parse(monthStartDate),
        amortizationAmount = amortizedAmountPLN
    )
}