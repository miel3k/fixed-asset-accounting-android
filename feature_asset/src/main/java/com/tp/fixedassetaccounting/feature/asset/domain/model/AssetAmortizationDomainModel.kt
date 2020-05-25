package com.tp.fixedassetaccounting.feature.asset.domain.model

import java.time.Instant

internal data class AssetAmortizationDomainModel(
    val amortizationRate: Double,
    val amortizedAmount: Double,
    val calculationDate: Instant,
    val monthEntries: List<AmortizationMonthEntryDomainModel>
)