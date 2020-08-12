package org.itstep.liannoi.nitrous.infrastructure.notifications

import android.content.Context
import androidx.core.app.NotificationCompat
import org.itstep.liannoi.nitrous.application.common.notifications.Notification

class ProgressNotificationService(context: Context) : DefaultNotificationService(context) {

    override fun prepare(notification: Notification): NotificationCompat.Builder =
        super.prepare(notification)
            .setProgress(100, 1, true)
}
