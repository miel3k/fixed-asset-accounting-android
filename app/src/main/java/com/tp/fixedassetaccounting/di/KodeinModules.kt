package com.tp.fixedassetaccounting.di

import com.tp.fixedassetaccounting.BuildConfig
import org.kodein.di.Kodein

object KodeinModules {

    private const val featurePackagePrefix = "com.igorwojda.showcase.feature"

    private val featureModules = BuildConfig.FEATURE_MODULE_NAMES
        .map { "$featurePackagePrefix.$it.FeatureKodeinModule" }
        .map {
            try {
                Class.forName(it).kotlin.objectInstance as KodeinModuleProvider
            } catch (e: ClassNotFoundException) {
                throw ClassNotFoundException("Kodein module class not found $it")
            }
        }
        .map { it.kodeinModule }

    private val appModule = Kodein.Module("appModule") {

    }

    fun getAllModules() = featureModules + appModule
}