package com.tp.fixedassetaccounting.feature.asset.presentation.newasset.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tp.feature_asset.R
import com.tp.fixedassetaccounting.core.extension.requestPermission
import com.tp.fixedassetaccounting.core.extension.toast
import com.tp.fixedassetaccounting.feature.asset.presentation.newasset.viewmodel.NewAssetViewModel
import kotlinx.android.synthetic.main.fragment_new_asset.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class NewAssetFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val viewModel: NewAssetViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_new_asset, container, false)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == BarcodeActivity.REQUEST_CODE) {
            val barcode = data?.extras?.getString(BarcodeActivity.BARCODE)
            //TODO Handle barcode
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_message.text = "Asset Fragment"
        setupScanButton()
    }

    private fun setupScanButton() {
        btn_scan.setOnClickListener {
            activity?.requestPermission(
                Manifest.permission.CAMERA,
                onGranted = ::startBarcodeActivity,
                onDenied = { toast("No camera permission") }
            )
        }
    }

    private fun startBarcodeActivity() {
        val intent = Intent(context, BarcodeActivity::class.java)
        startActivityForResult(intent, BarcodeActivity.REQUEST_CODE)
    }
}