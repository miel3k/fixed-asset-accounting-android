package com.tp.fixedassetaccounting.feature.asset.data.repository

import androidx.lifecycle.LiveData
import com.squareup.moshi.Moshi
import com.tp.fixedassetaccounting.core.MediaTypes
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import com.tp.fixedassetaccounting.feature.asset.data.model.toDomainModel
import com.tp.fixedassetaccounting.feature.asset.data.repository.local.AssetDao
import com.tp.fixedassetaccounting.feature.asset.data.repository.remote.AssetService
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetAmortizationDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.repository.AssetDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody

internal class AssetRepository(
    private val assetService: AssetService,
    private val assetDao: AssetDao
) : AssetDataSource {

    override suspend fun loadAssets(): List<AssetDomainModel> {
        val assets = assetService.getAssetsAsync()
        if (assets != null) {
            withContext(Dispatchers.IO) {
                assetDao.insertAllAssets(assets)
            }
        }
        return assets?.map { it.toDomainModel() }.orEmpty()
    }

    override suspend fun getAssets(): LiveData<List<AssetDataModel>> {
        return assetDao.getAll()
    }

    override suspend fun getAsset(assetName: String): LiveData<AssetDataModel> {
        return assetDao.get(assetName)
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

    override suspend fun getAssetAmortization(
        assetName: String,
        date: String
    ): AssetAmortizationDomainModel? {
        val headers = mutableMapOf<String, String>()
        val query = mapOf("date" to date)
        return assetService.getAssetAmortizationAsync(headers, assetName, query)?.toDomainModel()
    }
}