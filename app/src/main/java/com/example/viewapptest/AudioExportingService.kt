package com.example.viewapptest

import android.annotation.SuppressLint
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.ServiceCompat
import androidx.core.graphics.drawable.IconCompat
import kotlinx.coroutines.runBlocking

@SuppressLint("RestrictedApi")
class AudioExportingService : Service() {
    companion object {
        const val EXPORTING_AUDIO_ID = 223
    }

    private val applicationIcon by lazy {
        IconCompat.createWithResource(applicationContext, R.drawable.ic_launcher_foreground)
    }

    private val notificationBuilder by lazy {
        androidx.core.app.NotificationCompat.Builder(applicationContexts,
                                                     Application.NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(applicationIcon)
            .setVisibility(androidx.core.app.NotificationCompat.VISIBILITY_PUBLIC)
            .setForegroundServiceBehavior(androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
            .setCategory(Notification.CATEGORY_PROGRESS)
    }

    override fun onCreate() {
        super.onCreate()

        notificationBuilder.setContentTitle("test")
        notificationBuilder.setProgress(100, 1, true).setOngoing(true)
        ServiceCompat.startForeground(this,
                                      EXPORTING_AUDIO_ID,
                                      notificationBuilder.build(),
                                      1 shl 0)

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}