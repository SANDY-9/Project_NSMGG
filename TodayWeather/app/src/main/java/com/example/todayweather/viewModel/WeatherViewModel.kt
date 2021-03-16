package com.example.todayweather.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.model.DailyWeather
import com.example.todayweather.data.model.WeeklyWeather
import com.example.todayweather.helper.ConvertDateHelper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-05
 * @desc 날씨 관련 모든 처리 뷰모델(현재, 오늘, 주간날씨 모두 처리)
 */
class WeatherViewModel(application: Application) : ViewModel() {

    val currentWeather = MutableLiveData<CurrentWeather>()
    val dailyWeather = MutableLiveData<DailyWeather>()
    val weeklyWeather = MutableLiveData<WeeklyWeather>()

    init {
        currentWeather.value = CurrentWeather(application)
        dailyWeather.value = DailyWeather()
        weeklyWeather.value = WeeklyWeather()
    }
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return WeatherViewModel(application) as T
        }
    }
    fun setCurrentWeather () {
        
    }
}