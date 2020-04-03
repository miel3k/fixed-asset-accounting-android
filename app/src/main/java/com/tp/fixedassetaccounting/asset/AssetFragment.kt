package com.tp.fixedassetaccounting.asset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tp.fixedassetaccounting.R
import kotlinx.android.synthetic.main.fragment_asset.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AssetFragment : Fragment() {

    private val viewModel by viewModel<AssetViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_asset, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_message.text = "Asset Fragment"
    }
}