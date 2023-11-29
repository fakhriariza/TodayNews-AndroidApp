package com.example.todaynews

import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

//    override fun onMessageReceived(remoteMessage: RemoteMessage) {
//        val title = remoteMessage.notification!!.title
//        val text = remoteMessage.notification!!.body
//        val CHANNEL_ID = "HEADS_UP_NOTIFICATION"
//        val channel = NotificationChannel(
//            CHANNEL_ID,
//            "Heads Up Notification",
//            NotificationManager.IMPORTANCE_HIGH
//        )
//        val notification: Notification.Builder = Builder(this, CHANNEL_ID)
//            .setContentTitle(title)
//            .setContentText(text)
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setAutoCancel(true)
//        NotificationManagerCompat.from(this).notify(1, notification.build())
//        super.onMessageReceived(remoteMessage)
//    }
}