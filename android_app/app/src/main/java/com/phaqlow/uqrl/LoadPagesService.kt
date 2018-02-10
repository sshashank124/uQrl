package com.phaqlow.uqrl

import android.app.IntentService
import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import java.net.URLDecoder

class LoadPagesService : IntentService("LoadPagesService") {
    override fun onHandleIntent(intent: Intent) =
        intent.getStringExtra("URLS")
                .split("|||")
                .map { url ->
                    URLDecoder.decode(url, "UTF-8")
                }.forEach { decoded_url ->
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(decoded_url)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                    SystemClock.sleep(1000)
                }
}
