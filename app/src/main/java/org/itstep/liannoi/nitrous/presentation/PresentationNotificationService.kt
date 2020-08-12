package org.itstep.liannoi.nitrous.presentation

import android.app.Service
import android.content.Intent
import android.os.IBinder
import org.itstep.liannoi.nitrous.application.common.notifications.Notification
import org.itstep.liannoi.nitrous.application.common.notifications.Notifier
import org.itstep.liannoi.nitrous.infrastructure.InfrastructureDefaults
import org.itstep.liannoi.nitrous.infrastructure.notifications.ProgressNotificationService

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
        notifier = ProgressNotificationService(this)

        notification = Notification(
            InfrastructureDefaults.DEFAULT_NOTIFICATION_CHANNEL_FIRST_ID,
            "Lorem ipsum",
            "dolor sit amet"
        )
    }

    override fun onBind(intent: Intent): IBinder = binder

    fun alert() {
        notifier.notify(notification)
    }

    fun stop() {
        notifier.cancel(notification)
    }
}
