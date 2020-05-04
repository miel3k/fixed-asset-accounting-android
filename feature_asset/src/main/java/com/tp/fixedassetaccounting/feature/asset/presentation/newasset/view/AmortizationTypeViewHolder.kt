package com.tp.fixedassetaccounting.feature.asset.presentation.newasset.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tp.fixedassetaccounting.feature.asset.domain.model.AmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.model.SelectableAmortizationType
import kotlinx.android.synthetic.main.item_amortization_type.view.*

internal class AmortizationTypeViewHolder(
    itemView: View,
    private val clickListener: (AmortizationType) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(selectableAmortizationType: SelectableAmortizationType) {
        setupRadioButton(selectableAmortizationType)
        setupClickListener(selectableAmortizationType.amortizationType)
    }

    private fun setupRadioButton(selectableAmortizationType: SelectableAmortizationType) {
        itemView.rb_amortization_type.run {
            text = selectableAmortizationType.amortizationType.name
            isChecked = selectableAmortizationType.isSelected
        }
    }

    private fun setupClickListener(amortizationType: AmortizationType) {
        itemView.rb_amortization_type.setOnClickListener {
            clickListener(amortizationType)
        }
    }
}