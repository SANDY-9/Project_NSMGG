package com.example.todayweather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.model.DailyWeather
import com.example.todayweather.data.model.WeeklyWeather
import com.example.todayweather.data.network.response.CurrentWeatherResponse
import com.example.todayweather.data.network.response.CurrentWeatherResponse.Response.Body.Items.Item
import java.text.SimpleDateFormat
import java.util.*

class RetrofitNetWorkImpl(
        private val weatherAPIService : WeatherAPIService,
        private val airAPIService: AirAPIService
) : RetrofitNetWork {

    val base_time = SimpleDateFormat("hhmm").format(Date())
    val base_date = SimpleDateFormat("yyyyMMdd").format(Date())

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
            val currentWeather = CurrentWeather()
            val item : List<Item> = fetchCurrentWeather.body()!!.response.body.items.item
            //미세먼지 요청


        } catch (e : ConnectivityInterceptorImpl.NoConnectivityException) {
            Log.e("[ERROR]", "네트워크 연결이 되어 있지 않음")
        }
    }

    override suspend fun fetchDailyWeather(nx: Int, ny: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun fetchDownloadWeather(nx: Int, ny: Int, addr: String) {
        TODO("Not yet implemented")
    }
}