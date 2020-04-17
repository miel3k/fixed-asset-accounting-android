package com.tp.fixedassetaccounting.feature.asset.presentation

import androidx.fragment.app.Fragment
import com.tp.fixedassetaccounting.core.ViewModelSupplier
import com.tp.fixedassetaccounting.feature.asset.FEATURE_NAME
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${FEATURE_NAME}PresentationModule") {
    bind<AssetViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        ViewModelSupplier.of(context) { AssetViewModel() }
    }
}