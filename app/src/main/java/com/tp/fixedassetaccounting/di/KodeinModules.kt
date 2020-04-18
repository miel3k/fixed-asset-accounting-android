package com.tp.fixedassetaccounting.di

import com.tp.fixedassetaccounting.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object KodeinModules {

    private const val featurePackagePrefix = "com.tp.fixedassetaccounting.feature"

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

        bind<Retrofit.Builder>() with singleton { Retrofit.Builder() }

        bind<Retrofit>() with singleton {
            instance<Retrofit.Builder>()
                .baseUrl("http://10.0.2.2:8180")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }

    fun getAllModules() = featureModules + appModule
}