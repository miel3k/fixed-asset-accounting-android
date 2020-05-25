package com.tp.fixedassetaccounting.feature.asset.data.repository.remote

import com.tp.fixedassetaccounting.feature.asset.data.model.AssetAmortizationDataModel
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

internal interface AssetService {
    @GET("/api/assets")
    suspend fun getAssetsAsync(): List<AssetDataModel>?

    @POST("/api/assets")
    suspend fun addAssetAsync(
        @Body body: RequestBody,
        @HeaderMap headers: Map<String, String>
    ): Response<Void>

    @GET("/api/assets/{$ASSET_NAME}/amortize")
    suspend fun getAssetAmortizationAsync(
        @HeaderMap headers: Map<String, String>,
        @Path(ASSET_NAME) assetName: String,
        @QueryMap query: Map<String, String>
    ): AssetAmortizationDataModel?

    private companion object {
        const val ASSET_NAME = "assetName"
    }
}