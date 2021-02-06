package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.network.AirAPIService
import com.example.todayweather.data.network.RetrofitNetWork
import com.example.todayweather.data.network.RetrofitNetWorkImpl
import com.example.todayweather.data.network.WeatherAPIService

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-05
 * @desc 날씨 관련 모든 처리 뷰모델(현재, 오늘, 주간날씨 모두 처리)
 */
class WeatherViewModel : ViewModel(){

    val currentWeather : MutableLiveData<CurrentWeather> = MutableLiveData<CurrentWeather>()

    val retrofitNetWork : RetrofitNetWork = RetrofitNetWorkImpl(WeatherAPIService.invoke(), AirAPIService.invoke())

    fun getCurrentWeather(nx:Double, ny:Double, addr:String) {
    }
}