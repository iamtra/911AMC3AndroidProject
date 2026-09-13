package kh.com.pheaktra.developer.basic.advance.android.weekend.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

object NotificationHelper {

    const val CHANNEL_ID = "general_notification"

    fun createNotificationChannel(
        context: Context,
    ) {

        val channel = NotificationChannel(
            CHANNEL_ID,
            "General Notifications",
            NotificationManager.IMPORTANCE_HIGH,
        ).apply {
            description = "General application notifications"
        }

        val notificationManager =
            context.getSystemService(
                NotificationManager::class.java
            )

        notificationManager.createNotificationChannel(
            channel
        )
    }
}