package com.tp.fixedassetaccounting.feature.asset.domain.model

import java.time.Instant

internal data class AmortizationMonthEntryDomainModel(
    val monthStartDate: Instant,
    val amortizationAmount: Double
)