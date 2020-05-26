package com.tp.fixedassetaccounting.feature.asset.presentation.assets.viewmodel

import androidx.lifecycle.*
import com.tp.fixedassetaccounting.feature.asset.data.model.toDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.GetAssetsUseCase
import kotlinx.coroutines.launch

internal class AssetsViewModel(private val getAssetsUseCase: GetAssetsUseCase) : ViewModel() {

    val assets: LiveData<List<AssetDomainModel>> by lazy {
        MediatorLiveData<List<AssetDomainModel>>().apply {
            viewModelScope.launch {
                addSource(getAssetsUseCase.get()) { assets ->
                    value = assets.map { it.toDomainModel() }
                }
            }
        }
    }
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
        mutableState.value = ViewState(isLoading = true, isError = false)
        viewModelScope.launch {
            val assets = getAssetsUseCase.load()
            mutableState.value = if (assets.isNotEmpty()) {
                ViewState(isLoading = false, isError = false)
            } else {
                ViewState(isLoading = false, isError = true)
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false
    )
}