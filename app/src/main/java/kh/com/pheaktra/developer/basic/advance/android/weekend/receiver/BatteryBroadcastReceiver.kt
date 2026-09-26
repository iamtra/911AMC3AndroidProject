package kh.com.pheaktra.developer.basic.advance.android.weekend.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import kh.com.pheaktra.developer.model.general.BatteryStatus

class BatteryBroadcastReceiver(
    private val onBatteryStatusChanged: (BatteryStatus) -> Unit,
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != Intent.ACTION_BATTERY_CHANGED) return

        // Battery percentage
        val level = intent.getIntExtra(
            BatteryManager.EXTRA_LEVEL,
            -1
        )

        val scale = intent.getIntExtra(
            BatteryManager.EXTRA_SCALE,
            -1
        )

        val percentage = if (level >= 0 && scale > 0) {
            (level * 100) / scale
        } else {
            0
        }

        // Charging status
        val status = intent.getIntExtra(
            BatteryManager.EXTRA_STATUS,
            BatteryManager.BATTERY_STATUS_UNKNOWN
        )

        val isCharging =
            status == BatteryManager.BATTERY_STATUS_CHARGING ||
            status == BatteryManager.BATTERY_STATUS_FULL

        onBatteryStatusChanged(
            BatteryStatus(
                percentage = percentage,
                isCharging = isCharging,
            )
        )
    }
}