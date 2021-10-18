package com.example.todoapp.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val itemId = p1?.getLongExtra("itemId", 1)
        if (itemId != null && p0 != null) {
            NotificationManagerCustom.showNewItemNotification(itemId = itemId, context = p0)
        }
    }
}