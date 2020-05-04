package com.tp.fixedassetaccounting.feature.asset.presentation.assets.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.core.extension.observe
import com.tp.fixedassetaccounting.core.extension.toast
import com.tp.fixedassetaccounting.feature.asset.presentation.assets.viewmodel.AssetsViewModel
import kotlinx.android.synthetic.main.fragment_assets.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class AssetsFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModel: AssetsViewModel by instance()

    private val assetsAdapter by lazy {
        AssetsAdapter {
            toast("Asset ${it.assetName} clicked")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_assets, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupFloatingActionButton()
        setupStateObserver()
        viewModel.loadData()
    }

    private fun setupRecyclerView() {
        rv_assets.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = assetsAdapter
        }
    }

    private fun setupFloatingActionButton() {
        fab_new_asset.setOnClickListener {
            val navDirections = AssetsFragmentDirections.openNewAssetFragment()
            findNavController().navigate(navDirections)
        }
    }

    private fun setupStateObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            assetsAdapter.updateAssets(it.assets)
            pb_loading.isVisible = it.isLoading
            iv_no_assets.isVisible = it.isError
        }
    }
}