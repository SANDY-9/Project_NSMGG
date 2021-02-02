package com.example.todayweather.push

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.todayweather.view.main.SplashActivity

/**
 * @author seungwon
 * @email aoqnwnd@naver.com
 * @created 2021-02-03
 * @desc
 */
class AlarmReciver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val mintent = Intent(context, SplashActivity::class.java)

        mintent.putExtra("year", mintent.getStringExtra("year"))
        mintent.putExtra("month", mintent.getStringExtra("month"))
        mintent.putExtra("day", mintent.getStringExtra("day"))
        mintent.putExtra("hour", mintent.getStringExtra("hour"))
        mintent.putExtra("min", mintent.getStringExtra("min"))

        context?.startService(mintent)
    }
}