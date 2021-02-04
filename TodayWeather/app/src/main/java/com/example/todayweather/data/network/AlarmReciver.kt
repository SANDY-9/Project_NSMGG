package com.example.todayweather.push

import android.app.IntentService
import android.app.Notification
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import com.example.todayweather.R
import com.example.todayweather.ui.main.SplashActivity

/**
 * @author seungwon
 * @email aoqnwnd@naver.com
 * @created 2021-02-03
 * @desc
 */
class AlarmReciver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val mintent = Intent(context, AlarmIntentService::class.java)
        context?.startService(mintent)
        Log.d("[test]","AlarmReciver")
    }
}
class AlarmIntentService : IntentService("AlarmIntentService") {
    val NOTIFICATION_ID = 1001
    override fun onHandleIntent(intent: Intent?) {
        Log.d("[test]","AlarmIntentService")
        Intent(this, SplashActivity::class.java).flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, Intent(this, SplashActivity::class.java), 0)
        val mLargeIconForNoti = BitmapFactory.decodeResource(this.resources, R.drawable.cloud)

        lateinit var builder :Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = Notification.Builder(this, "channel_id")
                    .setSmallIcon(R.drawable.cloud)
                    .setLargeIcon(mLargeIconForNoti)
                    .setContentTitle("오늘 날씨 머꼬?")
                    .setContentText("날씨 확인하기")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
            Log.d("[test]","버전이 맞고 builder함")
            val notificationManager = NotificationManagerCompat.from(this)
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(NOTIFICATION_ID, builder.build())
        }else{
            Log.d("[test]","버전이 안 맞아유")
        }
    }
}