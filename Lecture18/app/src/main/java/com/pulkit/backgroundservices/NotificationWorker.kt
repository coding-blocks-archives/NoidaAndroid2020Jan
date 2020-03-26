package com.pulkit.backgroundservices

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(val context: Context, val workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    val nMgr by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun doWork(): Result {
        val data = workerParameters.inputData.getString("title")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nMgr.createNotificationChannel(
                NotificationChannel(
                    "1",
                    "Default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }

        val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification.Builder(context, "1")

        } else {
            Notification.Builder(context)
        }
        val notification = notificationBuilder.apply {
            setSmallIcon(R.drawable.ic_launcher_background)
            setContentText("this is sample notificatioon")
            setContentTitle(data)
            setAutoCancel(true)
        }.build()


        nMgr.notify(System.currentTimeMillis().toInt(), notification)

        return Result.success()


    }
}