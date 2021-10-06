package com.example.todoapp.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.net.toUri
import com.example.todoapp.R
import com.example.todoapp.common.Constants
import com.example.todoapp.common.Constants.channelId
import com.example.todoapp.presentation.MainActivity

object NotificationManagerCustom {
    private var notificationManager: NotificationManager? = null

    fun createNotificationChannel(context: Context) {
        notificationManager = getSystemService(context, NotificationManager::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notification channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "This is a notification test channel"
            }

            notificationManager?.createNotificationChannel(channel)
        }
    }

    fun showNewItemNotification(itemId: Long, context: Context) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            "${Constants.notificationUrl}/$itemId".toUri(),
            context,
            MainActivity::class.java
        )

        val pending = TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_baseline_done_24)
            .setContentTitle("An item has been added")
            .setContentText("Go and check it!")
            .setContentIntent(pending)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        val notificationId = (1..999).random()

        notificationManager?.notify(notificationId, notification)

    }
}