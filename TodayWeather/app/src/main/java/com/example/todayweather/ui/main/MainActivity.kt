package com.example.todayweather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.todayweather.R
import com.example.todayweather.data.network.RetrofitNetWork
import com.example.todayweather.data.network.RetrofitNetWorkImpl
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity() {
    val TAG = "메인"


    override suspend fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /*

        동네 예보조회 요청가능 시간으로부터 3시간까지의
        요청할수 있는 시간
        0200 0500 0800 1100 1400 1700 2000 2300

        전날을 요청할수 있는 조건
        1. 현재시각으로부터 24시간 전은 요청할수있다
        예: 현재 = 날짜 210206 / 시간 1600
            요청 가능시간 = 날짜 210205 / 시간 1700 / 2000 / 2300 요청가능

        2300~0159 : 전날 2300 요청하기

         */





        //var base_time = SimpleDateFormat("HH").format(Date())

        val df: DateFormat = SimpleDateFormat("HH")
        var time:Date = df.parse("24")

        var str: String ="${df.format(time)}"

        Log.e(TAG, "onCreate: "+str )




        val retrofitNetWork : RetrofitNetWork

        //retrofitNetWork.fetchDailyWeather(61,126)



        //var yesterday_date = yesterday()

    }


}