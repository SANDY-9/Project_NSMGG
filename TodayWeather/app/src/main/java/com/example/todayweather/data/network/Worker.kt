package com.example.todayweather.data.network

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.example.todayweather.R
import com.example.todayweather.ui.main.StartActivity
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * @author seungwon
 * @email aoqnwnd@naver.com
 * @created 2021-02-15
 * @desc
 */

class SimpleWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    val context = context
    override fun doWork(): Result {

// 다음날 같은 시간에 알림 만들기
        val dailyWorkRequeset = OneTimeWorkRequestBuilder<SimpleWorker>()
            .setInitialDelay(1, TimeUnit.DAYS)
            .addTag("notify_day_by_day")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(dailyWorkRequeset)

// 시간이 되면 실제 푸쉬 알림 생성 코드
        val pendingIntent = PendingIntent.getActivity(context, 0, Intent(context, StartActivity::class.java), 0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val builder = Notification.Builder(applicationContext, "channel_id")
                .setSmallIcon(R.drawable.cloud)
                .setContentTitle("오늘 날씨 머꼬?")
                .setContentText("날씨 확인하기 ")
                .setContentIntent(pendingIntent)
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
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

object WorkerUtil{
    private var dailyWorkRequeset : OneTimeWorkRequest? = null

    private val workManager: WorkManager by lazy {
        WorkManager.getInstance()
    }

    fun DailyWorkRequeset(result_hour: Int, result_min: Int){
        dailyWorkRequeset = OneTimeWorkRequestBuilder<SimpleWorker>()
                .setInitialDelay(getTimeUsingInWorkRequest(result_hour, result_min), TimeUnit.MILLISECONDS)
                .addTag("notify_day_by_day")
                .build()
        workManager.enqueue(dailyWorkRequeset!!)
    }

    fun getTimeUsingInWorkRequest(hour:Int, min:Int): Long {
        val currentDate = Calendar.getInstance()
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }
        if (calendar.before(currentDate)) {
            Log.d("[test]","내일 알람")
            calendar.add(Calendar.DATE, 1)
        }
        Log.d("[test_time]","${calendar.timeInMillis- currentDate.timeInMillis}ms")
        return calendar.timeInMillis- currentDate.timeInMillis
    }

    fun cancelAllWorker() {
        Log.d("[test_cancel]","취소됨")
        workManager.cancelAllWork()
    }

}