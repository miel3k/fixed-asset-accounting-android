package com.tp.fixedassetaccounting.feature.asset.domain.model

data class SelectableAmortizationType(
    val amortizationType: AmortizationType,
    val isSelected: Boolean
)