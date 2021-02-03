package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.todayweather.repository.model.WeeklyWeather

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 주간 날씨 데이터
 */
class WeeklyWeatherViewModel : BaseViewModel() {

    //라이브 데이터
    val weeklyWeather : MutableLiveData<WeeklyWeather> = MutableLiveData<WeeklyWeather>()

}