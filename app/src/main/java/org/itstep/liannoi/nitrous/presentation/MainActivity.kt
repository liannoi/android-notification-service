package org.itstep.liannoi.nitrous.presentation

import android.app.NotificationManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jakewharton.rxbinding4.view.clicks
import com.trello.rxlifecycle4.android.lifecycle.kotlin.bindToLifecycle
import kotlinx.android.synthetic.main.activity_main.*
import org.itstep.liannoi.nitrous.R
import org.itstep.liannoi.nitrous.infrastructure.notifications.DefaultNotificationService

class MainActivity : AppCompatActivity() {

    private lateinit var notificationService: PresentationNotificationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prepareChannel()
        subscribeStart(prepareIntent(), prepareConnection())
        subscribeStop()
        subscribeShow()
    }

    ///////////////////////////////////////////////////////////////////////////
    // Helpers
    ///////////////////////////////////////////////////////////////////////////

    private fun subscribeShow() {
        show_button.clicks()
            .bindToLifecycle(this)
            .subscribe(
                { notificationService.alert() },
                { Toast.makeText(this, "The service isn't running", Toast.LENGTH_LONG).show() })
    }

    private fun subscribeStop() {
        stop_button.clicks()
            .bindToLifecycle(this)
            .subscribe {
                notificationService.stop()
                Toast.makeText(this, "Notification removed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun subscribeStart(intent: Intent, serviceConnection: ServiceConnection) {
        start_button.clicks()
            .bindToLifecycle(this)
            .subscribe { bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE) }
    }

    private fun prepareIntent(): Intent =
        Intent(this, PresentationNotificationService::class.java)

    private fun prepareConnection(): ServiceConnection =
        object : ServiceConnection {

            override fun onServiceDisconnected(name: ComponentName?) {}

            override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
                notificationService =
                    (binder as PresentationNotificationService.Binder).service
            }
        }

    private fun prepareChannel() {
        DefaultNotificationService.Channel(getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            .create()
    }
}
