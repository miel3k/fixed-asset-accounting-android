package com.tp.fixedassetaccounting.feature.asset.presentation.newasset.viewmodel

import androidx.lifecycle.*
import com.tp.fixedassetaccounting.feature.asset.domain.model.AmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetCategory
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.SelectableAmortizationType
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.AddAssetUseCase
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

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
    val amortizationTypes = MutableLiveData(
        listOf(
            SelectableAmortizationType(AmortizationType.Linear, true),
            SelectableAmortizationType(AmortizationType.Digressive, false)
        )
    )
    val categories = MutableLiveData(AssetCategory.getAll())
    val selectedCategoryIndex =
        MutableLiveData(AssetCategory.getAll().indexOf(AssetCategory.Residence))
    val purchaseDate = MutableLiveData(Instant.now())
    val price = MutableLiveData(String.format("%.2f", 0.0f))

    fun addAsset(name: String, code: String, coefficient: Double) {
        mutableState.value = ViewState(isLoading = true, isSuccess = false)
        val asset = AssetDomainModel(
            code,
            name,
            AssetCategory.getAll()[requireNotNull(selectedCategoryIndex.value)],
            purchaseDate.value ?: Instant.now(),
            price.value?.toDoubleOrNull() ?: 0.0,
            Instant.now(),
            coefficient,
            amortizationTypes.value?.find {
                it.isSelected
            }?.amortizationType ?: AmortizationType.Linear
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
        val oldAmortizationTypes = amortizationTypes.value.orEmpty()
        val newAmortizationTypes = oldAmortizationTypes.map {
            val isSelected = it.amortizationType.name == amortizationType.name
            SelectableAmortizationType(it.amortizationType, isSelected)
        }
        amortizationTypes.value = newAmortizationTypes
        mutableState.value = ViewState(
            isLoading = false,
            isSuccess = false,
            isCoefficientEditEnabled = amortizationType is AmortizationType.Digressive
        )
    }

    fun selectCategory(categoryName: String) {
        val newIndex = categories.value?.indexOfFirst { it.name == categoryName }
        selectedCategoryIndex.value = newIndex
    }

    fun selectPurchaseDate(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val localDate = LocalDate.of(year, monthOfYear, dayOfMonth)
        val date = localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
        purchaseDate.value = date
    }

    fun setNewPrice(newPrice: String) {
        price.value = newPrice
    }

    internal data class ViewState(
        val isLoading: Boolean = false,
        val isSuccess: Boolean = false,
        val isCoefficientEditEnabled: Boolean = false
    )
}