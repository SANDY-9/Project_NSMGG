package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayweather.data.model.WeeklyWeather

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 주간 날씨 데이터
 */
class WeeklyWeatherViewModel : ViewModel() {

    //라이브 데이터
    val weeklyWeather : MutableLiveData<WeeklyWeather> = MutableLiveData<WeeklyWeather>()

}