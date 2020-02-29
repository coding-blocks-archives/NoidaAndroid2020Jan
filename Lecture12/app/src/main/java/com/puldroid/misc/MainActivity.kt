package com.puldroid.misc

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val nMgr: NotificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    "1",
                    "Default Channel",
                    NotificationManager.IMPORTANCE_HIGH
            )
            //Heads up notification
            channel.apply {
                enableLights(true)
                enableVibration(true)
            }
            nMgr.createNotificationChannel(channel)
        }

        val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(this, "1")
        } else {
            //Heads up for Devices less than OREO
            Notification.Builder(this)
                    .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_VIBRATE)
        }
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = Uri.parse("http://www.google.com")

        val pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = notificationBuilder.setContentTitle("This is a simple notification")
                .setContentText("This is a sample text")
                .setContentIntent(pi)
                .addAction(R.drawable.ic_launcher_foreground,"Click Me",pi)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
        nMgr.notify(System.currentTimeMillis().toInt(),notification)
        nMgr.notify(System.currentTimeMillis().toInt(), notification)

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        Log.i("Notifications","reqcode : $requestCode, rescode : $resultCode")
//        super.onActivityResult(requestCode, resultCode, data)
//
//    }
//
}
