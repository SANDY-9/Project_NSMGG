package com.example.todayweather.data.network

import androidx.lifecycle.LiveData
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.model.DailyWeather
import com.example.todayweather.data.model.WeeklyWeather

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-04
 * @desc 레트로핏 처리
 */
interface RetrofitNetWork {

    val downloadCurrentWeather : LiveData<CurrentWeather>
    val downloadDailyWeather : LiveData<DailyWeather>
    val downloadWeeklyWeather : LiveData<WeeklyWeather>

    suspend fun fetchCurrentWeather(nx : Int, ny : Int, addr: String)
    suspend fun fetchDailyWeather(nx : Int, ny : Int)
    suspend fun fetchWeeklyWeather(nx : Int, ny : Int, addr : String)

}