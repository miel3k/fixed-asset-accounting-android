package com.tp.fixedassetaccounting.feature.asset.presentation.newasset.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.AddAssetUseCase
import kotlinx.coroutines.launch
import java.util.*

internal class NewAssetViewModel(private val addAssetUseCase: AddAssetUseCase) : ViewModel() {

    fun addAsset() {
        val asset = AssetDomainModel(
            UUID.randomUUID().toString(),
            "testName",
            "00001111"
        )
        viewModelScope.launch {
            addAssetUseCase.execute(asset)
        }
    }
}