package com.tp.fixedassetaccounting.feature.asset.presentation.assets.viewmodel

import androidx.lifecycle.*
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.GetAssetsUseCase
import kotlinx.coroutines.launch

internal class AssetsViewModel(private val getAssetsUseCase: GetAssetsUseCase) : ViewModel() {

    private val mutableState = MutableLiveData<ViewState>().apply {
        value = ViewState()
    }
    val state: LiveData<ViewState> by lazy {
        MediatorLiveData<ViewState>().apply {
            addSource(mutableState) {
                value = it
            }
        }
    }

    fun loadData() {
        getAssets()
    }

    private fun getAssets() {
        mutableState.value = ViewState(isLoading = true, isError = false, assets = listOf())
        viewModelScope.launch {
            val assets = getAssetsUseCase.execute()
            mutableState.value = if (assets.isNotEmpty()) {
                ViewState(isLoading = false, isError = false, assets = assets)
            } else {
                ViewState(isLoading = false, isError = true, assets = listOf())
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val assets: List<AssetDomainModel> = listOf()
    )
}