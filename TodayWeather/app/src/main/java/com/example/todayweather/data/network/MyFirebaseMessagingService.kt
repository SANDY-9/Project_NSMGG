package com.example.todayweather.data.network

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Message
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.todayweather.R
import com.example.todayweather.view.main.SplashActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService  :  FirebaseMessagingService() {

    private val TAG = "FirebaseService"
    private var tokenId :String ?= null


    override fun onNewToken(token: String) { // 토큰 생성기
        super.onNewToken(token)
        tokenId = token
        Log.i(TAG, "Represhed token: " + token)

        //필요하면 이 토큰을 앱서버에 저장하는 과정을 거치면 된다.
//        sendToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) { //
        if(remoteMessage.notification != null) {
            var msg = remoteMessage.notification?.title
            var title = remoteMessage.notification?.body
            Log.d(TAG, "From: " + remoteMessage.from)
            Log.d(TAG, "Notification Message Body: ${remoteMessage.notification?.body}")
            sendNotification(title, msg)
        }
    }

    fun sendNotification(title: String?, body: String?) {
        val intent = Intent(this, SplashActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("channel_id", body)
        }

        var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        var notificationBuilder = NotificationCompat.Builder(this, "channel_id")
            .setSmallIcon(R.drawable.icon_cloud)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setSound(notificationSound)
            .setContentIntent(pendingIntent)

        var notificationManager: NotificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}