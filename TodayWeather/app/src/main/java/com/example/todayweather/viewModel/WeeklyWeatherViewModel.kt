package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.todayweather.model.DailyWeather
import com.example.todayweather.model.DataModel
import com.example.todayweather.model.WeeklyWeather

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 일주일 날씨 데이터
 */
class WeeklyWeatherViewModel : BaseViewModel() {

    //라이브 데이터
    val weeklyWeather : MutableLiveData<WeeklyWeather> = MutableLiveData<WeeklyWeather>()

}