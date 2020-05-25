package com.tp.fixedassetaccounting.feature.asset.data.model

import com.squareup.moshi.Json

internal data class AmortizationMetaDataModel(
    @field:Json(name = "amortizationMonthEntries") val amortizationMonthEntries: List<AmortizationMonthEntryDataModel>
)