package com.tp.fixedassetaccounting.feature.asset.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tp.fixedassetaccounting.feature.asset.data.model.AssetDataModel

@Database(entities = [AssetDataModel::class], version = 1, exportSchema = false)
internal abstract class AssetDatabase : RoomDatabase() {
    abstract fun assetDao(): AssetDao
}