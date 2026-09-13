package kh.com.pheaktra.developer.basic.advance.android.weekend

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kh.com.pheaktra.developer.basic.advance.android.weekend.service.NotificationHelper

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NotificationHelper.createNotificationChannel(this)
    }
}