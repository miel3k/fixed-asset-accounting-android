package com.tp.fixedassetaccounting.feature.asset.data.repository.remote

import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST

internal interface AssetService {
    @GET("/api/assets")
    suspend fun getAssetsAsync(): List<AssetDataModel>?

    @POST("/api/assets")
    suspend fun addAssetAsync(
        @Body body: RequestBody,
        @HeaderMap headers: Map<String, String>
    ): Response<Void>
}