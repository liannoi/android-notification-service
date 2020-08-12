package org.itstep.liannoi.nitrous.infrastructure.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.itstep.liannoi.nitrous.R
import org.itstep.liannoi.nitrous.application.common.notifications.Notification
import org.itstep.liannoi.nitrous.application.common.notifications.Notifier
import org.itstep.liannoi.nitrous.application.common.notifications.NotifierChannel
import org.itstep.liannoi.nitrous.infrastructure.InfrastructureDefaults

open class DefaultNotificationService(private val context: Context) : Notifier {

    class Channel(private val manager: NotificationManager) : NotifierChannel {

        override fun create() {
            manager.createNotificationChannel(prepare())
        }

        private fun prepare(): NotificationChannel =
            NotificationChannel(
                InfrastructureDefaults.DEFAULT_NOTIFICATION_CHANNEL_ID,
                InfrastructureDefaults.DEFAULT_NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                enableVibration(true)
                vibrationPattern = longArrayOf(100, 200, 100, 200)
                description = InfrastructureDefaults.DEFAULT_NOTIFICATION_CHANNEL_DESCRIPTION
            }
    }

    override fun notify(notification: Notification) {
        with(NotificationManagerCompat.from(context)) {
            notify(notification.id, prepare(notification).build())
        }
    }

    override fun cancel(notification: Notification) {
        with(NotificationManagerCompat.from(context)) { cancel(notification.id) }
    }

    protected open fun prepare(notification: Notification): NotificationCompat.Builder =
        NotificationCompat.Builder(context, InfrastructureDefaults.DEFAULT_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_cloud_queue_24)
            .setContentTitle(notification.title)
            .setContentText(notification.text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
}
