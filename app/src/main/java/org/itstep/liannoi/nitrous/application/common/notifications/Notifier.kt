package org.itstep.liannoi.nitrous.application.common.notifications

interface Notifier {

    fun notify(notification: Notification)
    fun cancel(notification: Notification)
}
