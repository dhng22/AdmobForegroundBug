package com.example.viewapptest

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

/**
 * Declares this class in `AndroidManifest.xml` as application's name. In case of custom koin DI,
 * extends this class and use the secondary constructor
 */
open class Application : Application() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "solace"
        val scope: CoroutineScope by lazy { CoroutineScope(Dispatchers.Default + SupervisorJob()) }
    }

    override fun onCreate() {
        super.onCreate()

         applicationContexts = this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                applicationContext.getSystemService(NotificationManager::class.java)

            val notificationChannel =
                NotificationChannel(NOTIFICATION_CHANNEL_ID, "test Notification", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}

lateinit var applicationContexts: Context