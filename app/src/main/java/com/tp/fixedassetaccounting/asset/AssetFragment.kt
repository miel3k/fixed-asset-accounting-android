package com.tp.fixedassetaccounting.asset

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tp.fixedassetaccounting.R
import com.tp.fixedassetaccounting.barcode.BarcodeActivity
import com.tp.fixedassetaccounting.utils.requestPermission
import com.tp.fixedassetaccounting.utils.toast
import kotlinx.android.synthetic.main.fragment_asset.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AssetFragment : Fragment() {

    private val viewModel by viewModel<AssetViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_asset, container, false)

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