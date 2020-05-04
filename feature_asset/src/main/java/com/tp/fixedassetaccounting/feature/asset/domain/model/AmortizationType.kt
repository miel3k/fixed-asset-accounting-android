package com.tp.fixedassetaccounting.feature.asset.domain.model

sealed class AmortizationType(val name: String) {
    object Linear : AmortizationType("LINEAR")
    object Digressive : AmortizationType("DIGRESSIVE")
    companion object {
        fun from(type: String) = when (type) {
            Linear.name -> Linear
            Digressive.name -> Digressive
            else -> Linear
        }
    }
}