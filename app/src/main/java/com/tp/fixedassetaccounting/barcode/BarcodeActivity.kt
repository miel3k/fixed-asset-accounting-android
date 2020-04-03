package com.tp.fixedassetaccounting.barcode

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class BarcodeActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    private val scannerView by lazy { ZXingScannerView(this) }

    override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        setContentView(scannerView)
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun handleResult(rawResult: Result) {
        val intent = Intent().apply { putExtra(BARCODE, rawResult.text) }
        setResult(REQUEST_CODE, intent)
        finish()
    }

    companion object {
        const val BARCODE = "BARCODE"
        const val REQUEST_CODE = 200
    }
}