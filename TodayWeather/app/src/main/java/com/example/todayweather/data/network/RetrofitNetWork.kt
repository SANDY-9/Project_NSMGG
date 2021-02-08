package com.example.todayweather.data.network

import androidx.lifecycle.LiveData
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.model.DailyWeather
import com.example.todayweather.data.model.WeeklyWeather

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-04
 * @desc 레트로핏 요청함수 모음
 */
interface RetrofitNetWork {

    fun fetchCurrentWeather(nx : Int, ny : Int)                         //초단기실황
    suspend fun fetchShortermTimeWeather(nx : Int, ny : Int)            //초단기예보
    fun fetchShortermDayWeather(nx : Int, ny : Int)             //동네예보
    fun fetchWeeklyWeather(regionCode:String, tempCode:String)          //주간날씨(중기예보)
    fun fetchYesterdayWeather(stnIds : Int)                             //어제날씨조회(과거날씨조회)
    fun fetchFineDust(sidoName : String)                                //미세먼지조회(대기오염조회)

}