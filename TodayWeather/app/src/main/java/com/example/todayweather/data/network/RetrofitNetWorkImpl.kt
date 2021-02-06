package com.example.todayweather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.model.DailyWeather
import com.example.todayweather.data.model.WeeklyWeather
import com.example.todayweather.data.network.response.CurrentWeatherResponse
import com.example.todayweather.data.network.response.CurrentWeatherResponse.Response.Body.Items.Item
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RetrofitNetWorkImpl(
        private val weatherAPIService : WeatherAPIService,
        private val airAPIService: AirAPIService
) : RetrofitNetWork {

    val base_time = SimpleDateFormat("hhmm").format(Date())
    val base_date = SimpleDateFormat("yyyyMMdd").format(Date())

    val TAG = "RetrofitNetWorkImpl"

    override val downloadCurrentWeather: LiveData<CurrentWeather>
        get() = MutableLiveData<CurrentWeather>()

    override val downloadDailyWeather: LiveData<DailyWeather>
        get() = MutableLiveData<DailyWeather>()

    override val downloadWeeklyWeather: LiveData<WeeklyWeather>
        get() = MutableLiveData<WeeklyWeather>()

    override suspend fun fetchCurrentWeather(nx: Int, ny: Int, addr: String) {
        try {
            //현재 날씨 요청
            val fetchCurrentWeather = weatherAPIService
                    .getCurrentWeather(base_date, base_time, nx, ny)
            //미세먼지 요청
            val dustStationSearch = airAPIService.getDustFindStation(
                    addr).await()
            val dustCurrent = airAPIService.getDustStation(
                    dustStationSearch.response!!.body.items[0].stationName
            ).await()

        } catch (e : ConnectivityInterceptorImpl.NoConnectivityException) {
            Log.e("[ERROR]", "네트워크 연결이 되어 있지 않음")
        }
    }


    /*

    동네 예보조회 요청가능 시간으로부터 3시간까지의
    요청할수 있는 시간
    0200 0500 0800 1100 1400 1700 2000 2300

    전날을 요청할수 있는 조건
    1. 현재시각으로부터 24시간 전은 요청할수있다
    예: 현재 = 날짜 210206 / 시간 1600
        요청 가능시간 = 날짜 210205 / 시간 1700 / 2000 / 2300 요청가능

    2400~0159 : 전날 2300 요청하기
     */

    //오늘 날씨 요청
    override suspend fun fetchDailyWeather(nx: Int, ny: Int) {
        var base_time = SimpleDateFormat("HH").format(Date())
        var base_date = SimpleDateFormat("yyyyMMdd").format(Date())
        Log.e("fetchDailyWeather", base_date )

        //00시거나 01시면 전날로 되돌리기
        if(base_time == "00" || base_time == "01") {base_date = yesterday()}
        Log.e(TAG, base_date)

        val fetchDailyWeather = weatherAPIService.getTodayWeather_day(base_date,base_time,nx,ny)
    }

    override suspend fun fetchWeeklyWeather(nx: Int, ny: Int, addr: String) {
        TODO("Not yet implemented")
    }

    //전날로 바꿔주는 함수
    fun yesterday():String{
        val cal = Calendar.getInstance()
        cal.time = Date()
        val day: DateFormat = SimpleDateFormat("yyyyMMdd")
        cal.add(Calendar.DATE, -1)
        return day.format(cal.time)
    }
}