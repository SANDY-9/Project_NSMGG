package com.example.todayweather.data.network

import android.app.Notification
import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.example.todayweather.R
import com.example.todayweather.ui.setting.SettingsFragment
import com.example.todayweather.ui.setting.SettingsFragment.Companion.setHour
import com.example.todayweather.ui.setting.SettingsFragment.Companion.setMin
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * @author seungwon
 * @email aoqnwnd@naver.com
 * @created 2021-02-15
 * @desc
 */

class SimpleWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {

//        val mHandler = Handler(Looper.getMainLooper())
//        mHandler.post(Runnable {
//            val dailyWorkRequeset = OneTimeWorkRequestBuilder<SimpleWorker>()
//                .setInitialDelay(SettingsFragment().getTimeUsingInWorkRequest(setHour, setMin), TimeUnit.MILLISECONDS)
//                .addTag("notify_day_by_day")
//                .build()
//            WorkManager.getInstance(applicationContext).enqueue(dailyWorkRequeset)
//        })
        // 이곳에 처리해야할 작업 코드를 추가합니다
        Log.d("[test]", "진입은 하니?")
//        SettingsFragment().createNotificationChannel()
        //...네트워크 처리...
        lateinit var builder : Notification.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = Notification.Builder(applicationContext, "channel_id")
                .setSmallIcon(R.drawable.cloud)
                .setContentTitle("오늘 날씨 머꼬?")
                .setContentText("날씨 확인하기")
                .setAutoCancel(true)
            Log.d("[test]", "버전이 맞고 builder함")
            val notificationManager = NotificationManagerCompat.from(applicationContext)
            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1001, builder.build())
        }else{
            Log.d("[test]", "버전이 안 맞아유")
        }

        return Result.success()
    }

}