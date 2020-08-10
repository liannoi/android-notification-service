package org.itstep.liannoi.nitrous

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle.Event.ON_STOP
import com.jakewharton.rxbinding4.view.clicks
import com.trello.rxlifecycle4.android.lifecycle.kotlin.bindUntilEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_button.clicks()
                .bindUntilEvent(this, ON_STOP)
                .subscribe()
    }
}
