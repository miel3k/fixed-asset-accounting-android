package com.tp.fixedassetaccounting.feature.asset.presentation.details.viewmodel

import androidx.lifecycle.*
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.tp.fixedassetaccounting.feature.asset.data.model.toDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.GetAssetAmortizationUseCase
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.*

internal class AssetDetailsViewModel(private val getAssetAmortizationUseCase: GetAssetAmortizationUseCase) :
    ViewModel() {

    private var assetName: String? = null
    private val mutableState = MutableLiveData(ViewState())
    val state: LiveData<ViewState> by lazy {
        MediatorLiveData<ViewState>().apply {
            addSource(mutableState) {
                value = it
            }
        }
    }
    val asset: LiveData<AssetDomainModel> by lazy {
        MediatorLiveData<AssetDomainModel>().apply {
            viewModelScope.launch {
                assetName?.let {
                    addSource(getAssetAmortizationUseCase.get(it)) {
                        value = it.toDomainModel()
                    }
                }
            }
        }
    }
    val amortizationDate = MutableLiveData(Instant.now())
    val amortizationBarData = MutableLiveData<BarData>()

    fun setup(newAssetName: String) {
        assetName = newAssetName
    }

    fun selectDate(year: Int, monthOfYear: Int, dayOfMonth: Int, assetName: String) {
        val localDate = LocalDate.of(year, monthOfYear, dayOfMonth)
        val date = localDate.atStartOfDay().toInstant(ZoneOffset.UTC)
        amortizationDate.value = date
        loadAssetAmortization(assetName, date)
    }

    private fun loadAssetAmortization(assetName: String, date: Instant) {
        viewModelScope.launch {
            mutableState.value = ViewState(isLoading = true)
            val amortizationResult = getAssetAmortizationUseCase.load(assetName, date)
            if (amortizationResult != null) {
                mutableState.value = ViewState(isLoading = false)
                val entries = amortizationResult.monthEntries.mapIndexed { index, item ->
                    val startDate = Date.from(item.monthStartDate)
                    BarEntry((startDate.month).toFloat(), item.amortizationAmount.toFloat())
                }
                val set = BarDataSet(entries, "Label").apply {
                    setColors(*ColorTemplate.VORDIPLOM_COLORS)
                }
                amortizationBarData.value = BarData(set)
            } else {
                mutableState.value = ViewState(isLoading = false)
            }
        }
    }

    internal data class ViewState(val isLoading: Boolean = false)
}