package com.tp.fixedassetaccounting.feature.asset.presentation.newasset.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.core.utils.getDiffCallback
import com.tp.fixedassetaccounting.feature.asset.domain.model.AmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.model.SelectableAmortizationType

internal class AmortizationTypesAdapter(private val clickListener: (AmortizationType) -> Unit) :
    RecyclerView.Adapter<AmortizationTypeViewHolder>() {

    private val asyncDiffer by lazy {
        val diffCallback = getDiffCallback<SelectableAmortizationType> { oldItem, newItem ->
            oldItem.amortizationType.name == newItem.amortizationType.name
        }
        AsyncListDiffer(this, diffCallback)
    }

    override fun getItemViewType(position: Int) = R.layout.item_amortization_type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmortizationTypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return AmortizationTypeViewHolder(view, clickListener)
    }

    override fun getItemCount() = asyncDiffer.currentList.size

    override fun onBindViewHolder(holder: AmortizationTypeViewHolder, position: Int) {
        holder.bind(asyncDiffer.currentList[position])
    }

    fun updateAmortizationTypes(newAmortizationTypes: List<SelectableAmortizationType>) {
        asyncDiffer.submitList(newAmortizationTypes)
    }
}