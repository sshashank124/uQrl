package com.phaqlow.uqrl

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_main.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build


class MainActivity : AppCompatActivity(), ZXingScannerView.ResultHandler {

    companion object {
        const val PERMISSION_REQUEST_CAMERA = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CAMERA -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    qr_scanner.setResultHandler(this)
                    qr_scanner.startCamera()
                } else { finish() }
            }
            else -> { }
        }
    }

    override fun onResume() {
        super.onResume()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
                return
            }
        }

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