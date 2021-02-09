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
            base_time = basetimeShortTerm(current_hour)
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

    fun basetimeShortTerm(current_hour : Int) : String {
        var base_time = ""
        //동네예보 base_time : 0200 0500 0800 1100 1400 1700 2000 2300
        when(current_hour) {
            2, 3, 4 -> base_time = "0200"
            5, 6, 7 -> base_time = "0500"
            8, 9, 10 -> base_time = "0800"
            11, 12, 13 -> base_time = "1100"
            14, 15, 16 -> base_time = "1400"
            17, 18, 19 -> base_time = "1700"
            20, 21, 22 -> base_time = "2000"
            23, 0, 1 -> base_time = "2300"
        }
        return base_time
    }
}