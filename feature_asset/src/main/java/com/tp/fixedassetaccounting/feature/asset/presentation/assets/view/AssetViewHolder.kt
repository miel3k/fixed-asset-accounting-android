package com.tp.fixedassetaccounting.feature.asset.presentation.assets.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import kotlinx.android.synthetic.main.item_asset.view.*

internal class AssetViewHolder(
    itemView: View,
    private val clickListener: (AssetDomainModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(asset: AssetDomainModel) {
        setupName(asset.assetName)
        setupCode(asset.code)
        setupClickListener(asset)
    }

    private fun setupName(name: String) {
        itemView.tv_name.text = name
    }

    private fun setupCode(code: String) {
        val codeText = "Code: $code"
        itemView.tv_code.text = codeText
    }

    private fun setupClickListener(asset: AssetDomainModel) {
        itemView.setOnClickListener {
            clickListener(asset)
        }
    }
}