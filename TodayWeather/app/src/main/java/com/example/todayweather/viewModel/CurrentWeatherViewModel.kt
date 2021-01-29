package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayweather.model.CurrentWeather
import com.example.todayweather.model.DataModel

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 현재 날씨 관련 정보 데이터
 */
class CurrentWeatherViewModel : BaseViewModel() {

    //라이브 데이터
    val currentWeather : MutableLiveData<CurrentWeather> = MutableLiveData<CurrentWeather>()

}