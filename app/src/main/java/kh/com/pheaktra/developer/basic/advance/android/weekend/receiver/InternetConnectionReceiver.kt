package kh.com.pheaktra.developer.basic.advance.android.weekend.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.extension.isInternetConnected

class InternetConnectionReceiver(
    private val onConnectionChanged: (isConnected: Boolean) -> Unit
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        onConnectionChanged(context.isInternetConnected())
    }
}