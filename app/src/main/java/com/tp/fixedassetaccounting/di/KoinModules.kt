package com.tp.fixedassetaccounting.di

import com.tp.fixedassetaccounting.asset.AssetViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class KoinModules {

    private val viewModelsModule = module {
        viewModel { AssetViewModel() }
    }

    fun getAllModules() = listOf(viewModelsModule)
}