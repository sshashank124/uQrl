package com.phaqlow.uqrl

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.content.Intent


class MainActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        qr_scanner.setResultHandler(this)
        qr_scanner.startCamera()
    }

    override fun onPause() {
        super.onPause()
        qr_scanner.stopCamera()
    }

    override fun handleResult(result: Result) {
        startService(Intent(this, LoadPagesService::class.java).putExtra("URLS", result.text))
    }
}