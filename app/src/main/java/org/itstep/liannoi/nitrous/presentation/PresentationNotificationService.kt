package org.itstep.liannoi.nitrous.presentation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import org.itstep.liannoi.nitrous.application.common.notifications.Notification
import org.itstep.liannoi.nitrous.application.common.notifications.Notifier
import org.itstep.liannoi.nitrous.infrastructure.InfrastructureDefaults
import org.itstep.liannoi.nitrous.infrastructure.notifications.DefaultNotificationService

class PresentationNotificationService : Service() {

    inner class Binder : android.os.Binder() {

        val service: PresentationNotificationService
            get() = this@PresentationNotificationService
    }

    private lateinit var binder: Binder
    private lateinit var notifier: Notifier
    private lateinit var notification: Notification

    override fun onCreate() {
        super.onCreate()
        binder = Binder()
        notifier = DefaultNotificationService(this)

        notification = Notification(
            InfrastructureDefaults.DEFAULT_NOTIFICATION_CHANNEL_FIRST_ID,
            "Hello",
            "World"
        )
    }

    override fun onBind(intent: Intent): IBinder = binder

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy: ", "")
    }

    fun alert() {
        notifier.notify(notification)
    }

    fun stop() {
        notifier.cancel(notification)
    }
}
