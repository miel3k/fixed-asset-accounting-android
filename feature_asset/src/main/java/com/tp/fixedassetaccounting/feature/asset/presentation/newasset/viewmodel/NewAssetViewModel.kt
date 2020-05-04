package com.tp.fixedassetaccounting.feature.asset.presentation.newasset.viewmodel

import androidx.lifecycle.*
import com.tp.fixedassetaccounting.feature.asset.domain.model.AmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.SelectableAmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.AddAssetUseCase
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.*

internal class NewAssetViewModel(private val addAssetUseCase: AddAssetUseCase) : ViewModel() {

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

    fun addAsset(name: String, code: String) {
        mutableState.value = ViewState(isLoading = true, isSuccess = false)
        val asset = AssetDomainModel(
            UUID.randomUUID().toString(),
            name,
            code,
            Instant.now(),
            0.0,
            Instant.now(),
            AmortizationType.Linear
        )
        viewModelScope.launch {
            val assetResult = addAssetUseCase.execute(asset)
            if (assetResult != null) {
                mutableState.value = ViewState(isLoading = false, isSuccess = true)
            } else {
                mutableState.value = ViewState(isLoading = false, isSuccess = false)
            }
        }
    }

    fun selectAmortizationType(amortizationType: AmortizationType) {
        val oldAmortizationTypes = state.value?.amortizationTypes.orEmpty()
        val newAmortizationTypes = oldAmortizationTypes.map {
            val isSelected = it.amortizationType.name == amortizationType.name
            SelectableAmortizationType(it.amortizationType, isSelected)
        }
        mutableState.value = ViewState(
            isLoading = false,
            isSuccess = false,
            amortizationTypes = newAmortizationTypes
        )
    }

    internal data class ViewState(
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val amortizationTypes: List<SelectableAmortizationType> = listOf(
            SelectableAmortizationType(AmortizationType.Linear, true),
            SelectableAmortizationType(AmortizationType.Digressive, false)
        )
    )
}