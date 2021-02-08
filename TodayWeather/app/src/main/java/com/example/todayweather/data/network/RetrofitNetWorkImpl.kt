package com.example.todayweather.data.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.todayweather.data.network.response.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class RetrofitNetWorkImpl(
    private val weatherAPIService: WeatherAPIService,
    private val airAPIService: AirAPIService
    ) : RetrofitNetWork {

    var base_time = SimpleDateFormat("HHmm").format(Date())
    var base_date = SimpleDateFormat("yyyyMMdd").format(Date())
    val calendar = Calendar.getInstance()
    var shortTimeWeather = MutableLiveData<ShortTimeWeatherResponse>()
    var shortDayWeather = MutableLiveData<ShortDayWeatherResponse>()

    override fun fetchCurrentWeather(nx: Int, ny: Int) {
        val minute = calendar.get(Calendar.MINUTE)
        if(minute>=0 && minute<30) {
            if(calendar.get(Calendar.HOUR_OF_DAY) == 0) {
                calendar.add(Calendar.DATE, -1)
                base_date = SimpleDateFormat("yyyyMMdd").format(calendar.time)
                base_time = "2330"
            } else {
                calendar.add(Calendar.HOUR_OF_DAY, -1)
                base_time = SimpleDateFormat("HH").format(calendar.time)+"30"
            }
        }
        weatherAPIService.getCurrentWeather(base_date, base_time, nx, ny)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    //초단기예보
    override suspend fun fetchShortermTimeWeather(nx: Int, ny: Int) {
        shortTimeWeather.value = weatherAPIService.getShortermWeather_time(base_date, base_time, nx, ny).body()
    }

    //동네예보
    override suspend fun fetchShortermDayWeather(nx: Int, ny: Int) {
        val current_hour = calendar.get(Calendar.HOUR_OF_DAY)
        if(current_hour == 0 || current_hour ==1) {
            calendar.add(Calendar.DATE, -1)
            base_date = SimpleDateFormat("yyyyMMdd").format(calendar.time)
            base_time = "2300"
        } else {
            base_time = basetimeShortTerm(current_hour.toString())
        }
        shortDayWeather.value = weatherAPIService.getShortermWeather_day(base_date, base_time, nx, ny).body()
    }

    override fun fetchWeeklyWeather(regionCode:String, tempCode:String) {
        val time_num = base_time.toInt()
        //오전 6시전 : 전날 1800, 오전 6시-오후6시 : 오늘 0600, 오후6시-11시 59분 : 오늘 1800
        if(time_num>=0 && time_num<600) {
            calendar.add(Calendar.DATE, -1)
            base_date = SimpleDateFormat("yyyyMMdd").format(calendar.time)+"1800"
        } else if (time_num>=600 && time_num<1800) {
            base_date = base_date+"0600"
        } else {
            base_date = base_date+"1800"
        }
        weatherAPIService.getWeeklyWeather_sky(regionCode, base_date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        weatherAPIService.getWeeklyWeather_temp(tempCode, base_date)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun fetchYesterdayWeather(stnIds: Int) {
        calendar.add(Calendar.DATE, -1)
        base_date = SimpleDateFormat("yyyyMMdd").format(calendar.time)
        weatherAPIService.getYesterdayWeather(base_date, base_date, stnIds)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun fetchFineDust(sidoName: String) {
        airAPIService.getFineDust(sidoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun basetimeShortTerm(now_timeHH : String) : String {
        var result_time= ""
        //0200 0500 0800 1100 1400 1700 2000 2300
        if(now_timeHH == "02" || now_timeHH == "03" || now_timeHH == "04"){
            result_time = "0200"
        } else if(now_timeHH == "05" || now_timeHH == "06" || now_timeHH == "07"){
            result_time = "0500"
        } else if (now_timeHH == "08" || now_timeHH == "09" || now_timeHH == "10") {
            result_time = "0800"
        } else if (now_timeHH == "11" || now_timeHH == "12" || now_timeHH == "13") {
            result_time = "1100"
        } else if (now_timeHH == "14" || now_timeHH == "15" || now_timeHH == "16") {
            result_time = "1400"
        } else if (now_timeHH == "17" || now_timeHH == "18" || now_timeHH == "19") {
            result_time = "1700"
        } else if (now_timeHH == "20" || now_timeHH == "21" || now_timeHH == "22") {
            result_time = "2000"
        } else if (now_timeHH == "23" || now_timeHH == "00" || now_timeHH == "01") {
            result_time = "2300"
        }
        return result_time
    }
}