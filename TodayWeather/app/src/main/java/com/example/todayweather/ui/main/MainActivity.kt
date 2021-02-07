package com.example.todayweather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todayweather.R
import com.example.todayweather.data.network.AirAPIService
import com.example.todayweather.data.network.RetrofitNetWork
import com.example.todayweather.data.network.RetrofitNetWorkImpl
import com.example.todayweather.data.network.WeatherAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    val TAG = "메인"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var now_timeMM = SimpleDateFormat("mm").format(Date())
        Log.e(TAG, now_timeMM )


        val retrofitNetWork = RetrofitNetWorkImpl(WeatherAPIService.invoke(), AirAPIService.invoke())

        GlobalScope.launch(Dispatchers.Main) {
            retrofitNetWork.fetchDailyWeather(61,126)
        }




    }
}