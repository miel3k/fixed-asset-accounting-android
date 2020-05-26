package com.tp.fixedassetaccounting.feature.asset.data.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel

@Dao
internal interface AssetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsset(asset: AssetDataModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAssets(assets: List<AssetDataModel>)

    @Query("SELECT * FROM Asset")
    fun getAll(): LiveData<List<AssetDataModel>>

    @Query("SELECT * FROM Asset WHERE assetName=:name ")
    fun get(name: String): LiveData<AssetDataModel>
}