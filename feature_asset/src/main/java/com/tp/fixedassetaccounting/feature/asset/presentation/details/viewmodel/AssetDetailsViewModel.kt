package com.tp.fixedassetaccounting.feature.asset.presentation.details.viewmodel

import androidx.lifecycle.*
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.tp.fixedassetaccounting.feature.asset.data.model.toDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.model.AssetDomainModel
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.GetAssetAmortizationUseCase
import kotlinx.coroutines.launch
import java.time.*

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
    val amortizationBarDataEntries = MutableLiveData<List<DataEntry>>()

    fun setup(newAssetName: String) {
        assetName = newAssetName
    }

    fun selectDate(year: Int, monthOfYear: Int, dayOfMonth: Int, assetName: String) {
        val localDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth)
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
                val entries = amortizationResult.monthEntries.map {
                    val startDate = LocalDateTime
                        .ofInstant(it.monthStartDate, ZoneId.systemDefault())
                        .toLocalDate()
                    ValueDataEntry(
                        MONTHS.getValue(startDate.monthValue) + " " + startDate.year,
                        it.amortizationAmount.toFloat()
                    )
                }
                amortizationBarDataEntries.value = entries
            } else {
                mutableState.value = ViewState(isLoading = false)
            }
        }
    }

    internal data class ViewState(val isLoading: Boolean = false)

    private companion object {
        val MONTHS = mapOf(
            0 to "Jan",
            1 to "Feb",
            2 to "Mar",
            3 to "Apr",
            4 to "May",
            5 to "Jun",
            6 to "Jul",
            7 to "Aug",
            8 to "Sep",
            9 to "Oct",
            10 to "Nov",
            11 to "Dec"
        ).withDefault { "" }
    }
}