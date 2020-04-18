package com.tp.fixedassetaccounting.feature.asset.domain

import com.tp.fixedassetaccounting.feature.asset.FEATURE_NAME
import com.tp.fixedassetaccounting.feature.asset.domain.usecase.GetAssetsUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${FEATURE_NAME}DomainModule") {

    bind() from singleton { GetAssetsUseCase() }
}