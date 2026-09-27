package kh.com.pheaktra.developer.basic.advance.android.weekend.service

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kh.com.pheaktra.developer.basic.advance.android.weekend.MainActivity
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.receiver.NotificationClickReceiver

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
            "From: ${message.from}",
        )

        // Ensure notification channel is created
        NotificationHelper.createNotificationChannel(this)

        val title = message.notification?.title
            ?: message.data["title"]
            ?: getString(R.string.app_name)

        val body = message.notification?.body
            ?: message.data["body"]
            ?: ""

        Log.d("FCM", "Title: $title")
        Log.d("FCM", "Body: $body")

        if (title.isNotEmpty() || body.isNotEmpty() || message.data.isNotEmpty()) {
            showNotification(
                title = title,
                body = body,
                data = message.data,
            )
        }
    }

    private fun sendTokenToServer(token: String) {
        Log.d("FCM", "Sending token to server: $token")
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

        val notificationId = System.currentTimeMillis().toInt()

        val transactionType = data[NotificationClickReceiver.TRANSACTION_TYPE]
            ?: data["transaction_type"]

        // Create PendingIntent launching MainActivity directly (required for Android 12+ API 31+ notification trampoline restriction)
        val intent = Intent(
            this,
            MainActivity::class.java,
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP

            putExtra(
                NotificationClickReceiver.EXTRA_NOTIFICATION_ID,
                notificationId,
            )
            putExtra(
                NotificationClickReceiver.TRANSACTION_TYPE,
                transactionType,
            )
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            notificationId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
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
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(
            notificationId,
            notification,
        )
    }
}