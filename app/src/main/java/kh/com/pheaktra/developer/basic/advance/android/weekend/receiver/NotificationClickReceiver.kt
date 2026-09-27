package kh.com.pheaktra.developer.basic.advance.android.weekend.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import kh.com.pheaktra.developer.basic.advance.android.weekend.MainActivity

class NotificationClickReceiver : BroadcastReceiver() {

    override fun onReceive(
        context: Context,
        intent: Intent,
    ) {
        val notificationId = intent.getIntExtra(
            EXTRA_NOTIFICATION_ID,
            -1,
        )

        val transactionType = intent.getStringExtra(TRANSACTION_TYPE)
        Log.d("NotificationClickReceiver", "=====> NotificationClickReceiver $transactionType")

        if (notificationId != -1) {
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
            notificationManager?.cancel(notificationId)
        }

        // Example: open your application
        val activityIntent = Intent(
            context,
            MainActivity::class.java,
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP

            putExtra(EXTRA_NOTIFICATION_ID, notificationId)
            putExtra(TRANSACTION_TYPE, transactionType)
        }

        context.startActivity(activityIntent)
    }

    companion object {
        const val EXTRA_NOTIFICATION_ID = "notification_id"
        const val TRANSACTION_TYPE = "transaction_type"
    }
}