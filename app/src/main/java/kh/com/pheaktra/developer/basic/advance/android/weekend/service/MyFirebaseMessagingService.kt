package kh.com.pheaktra.developer.basic.advance.android.weekend.service

import android.app.NotificationManager
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kh.com.pheaktra.developer.basic.advance.android.weekend.R

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("FCM", "FCM Token: $token")

        // Send token to your backend if you're using
        // token-based device targeting.
        sendTokenToServer(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d(
            "FCM",
            "From: ${message.from}"
        )

        // Notification payload
        message.notification?.let { notification ->

            val title = notification.title.orEmpty()
            val body = notification.body.orEmpty()

            Log.d("FCM", "Title: $title")
            Log.d("FCM", "Body: $body")

            showNotification(
                title = title,
                body = body,
                data = message.data,
            )
        }

        // Data payload
        if (message.data.isNotEmpty()) {

            Log.d(
                "FCM",
                "Data: ${message.data}"
            )

            // If you send data-only messages,
            // you may want to create the notification here.
        }
    }

    private fun sendTokenToServer(token: String) {
        // Example:
        //
        // notificationRepository.registerToken(token)
    }

    private fun showNotification(
        title: String,
        body: String,
        data: Map<String, String>,
    ) {

        val notificationManager =
            getSystemService(
                NotificationManager::class.java
            )

        val notification = NotificationCompat.Builder(
            this,
            NotificationHelper.CHANNEL_ID,
        )
            .setSmallIcon(R.drawable.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            System.currentTimeMillis().toInt(),
            notification,
        )
    }
}