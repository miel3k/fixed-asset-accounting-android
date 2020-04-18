package com.tp.fixedassetaccounting.feature.asset.data.repository

import com.squareup.moshi.Moshi
import com.tp.fixedassetaccounting.core.MediaTypes
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import com.tp.fixedassetaccounting.feature.asset.data.model.toDomainModel
import com.tp.fixedassetaccounting.feature.asset.data.repository.remote.AssetService
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource
import okhttp3.MediaType
import okhttp3.RequestBody

internal class AssetRepository(private val assetService: AssetService) : AssetDataSource {

    override suspend fun getAssets(): List<AssetDomainModel> {
        return assetService.getAssetsAsync()?.map { it.toDomainModel() }.orEmpty()
    }

    override suspend fun addAsset(asset: AssetDataModel): AssetDomainModel? {
        val headers = mutableMapOf<String, String>()
        val adapter = Moshi.Builder().build().adapter(AssetDataModel::class.java)
        val json = adapter.toJson(asset)
        val body = RequestBody.create(MediaType.parse(MediaTypes.APPLICATION_JSON), json)
        val response = assetService.addAssetAsync(body, headers)
        return if (response.isSuccessful) {
            asset.toDomainModel()
        } else {
            null
        }
    }
}