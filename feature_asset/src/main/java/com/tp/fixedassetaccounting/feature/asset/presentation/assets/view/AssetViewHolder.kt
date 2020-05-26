package com.tp.fixedassetaccounting.feature.asset.presentation.assets.view

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetCategory
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import kotlinx.android.synthetic.main.item_asset.view.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

internal class AssetViewHolder(
    itemView: View,
    private val clickListener: (AssetDomainModel) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(asset: AssetDomainModel) {
        setupName(asset.assetName)
        setupDocument(asset.documentName)
        setupPrice(asset.purchaseAmount)
        setupCategoryIcon(asset.assetCategory)
        setupDate(asset.purchaseDate)
        setupClickListener(asset)
    }

    private fun setupName(assetName: String) {
        itemView.tv_name.text = assetName
    }

    private fun setupDocument(documentName: String) {
        itemView.tv_document.text = documentName
    }

    private fun setupPrice(purchaseAmount: Double) {
        val priceText = "$purchaseAmount PLN"
        itemView.tv_price.text = priceText
    }

    private fun setupCategoryIcon(category: AssetCategory) {
        itemView.run {
            val categoryDrawable =
                ContextCompat.getDrawable(context, CATEGORIES.getValue(category.id))
            iv_type.setImageDrawable(categoryDrawable)
        }
    }

    private fun setupDate(purchaseDate: Instant) {
        val date = Date.from(purchaseDate)
        val formatter = SimpleDateFormat.getDateInstance()
        itemView.tv_date.text = formatter.format(date)
    }

    private fun setupClickListener(asset: AssetDomainModel) {
        itemView.setOnClickListener {
            clickListener(asset)
        }
    }

    private companion object {
        val CATEGORIES = mapOf(
            AssetCategory.Residence.id to R.drawable.ic_home_black_24dp,
            AssetCategory.Garage.id to R.drawable.ic_build_black_24dp,
            AssetCategory.SportBuilding.id to R.drawable.ic_domain_black_24dp,
            AssetCategory.PowerBoiler.id to R.drawable.ic_power_black_24dp,
            AssetCategory.GasEngine.id to R.drawable.ic_local_gas_station_black_24dp,
            AssetCategory.GasPump.id to R.drawable.ic_local_gas_station_black_24dp,
            AssetCategory.Crystalizer.id to R.drawable.ic_all_inclusive_black_24dp,
            AssetCategory.TechnicalEquipment.id to R.drawable.ic_apps_black_24dp,
            AssetCategory.Container.id to R.drawable.ic_content_copy_black_24dp,
            AssetCategory.Car.id to R.drawable.ic_directions_car_black_24dp,
            AssetCategory.Bus.id to R.drawable.ic_directions_bus_black_24dp,
            AssetCategory.Tractor.id to R.drawable.ic_directions_railway_black_24dp,
            AssetCategory.MobilePhone.id to R.drawable.ic_smartphone_black_24dp
        ).withDefault { R.drawable.ic_home_black_24dp }
    }
}