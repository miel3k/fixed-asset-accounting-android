package com.tp.fixedassetaccounting.feature.asset.presentation.assets.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.core.utils.getDiffCallback
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel

internal class AssetsAdapter(private val clickListener: (AssetDomainModel) -> Unit) :
    RecyclerView.Adapter<AssetViewHolder>() {

    private val asyncDiffer by lazy {
        val diffCallback = getDiffCallback<AssetDomainModel> { oldItem, newItem ->
            oldItem.id == newItem.id
        }
        AsyncListDiffer(this, diffCallback)
    }

    override fun getItemViewType(position: Int) = R.layout.item_asset

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return AssetViewHolder(view, clickListener)
    }

    override fun getItemCount() = asyncDiffer.currentList.size

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val asset = asyncDiffer.currentList[position]
        holder.bind(asset)
    }

    fun updateAssets(newAssets: List<AssetDomainModel>) {
        asyncDiffer.submitList(newAssets)
    }
}