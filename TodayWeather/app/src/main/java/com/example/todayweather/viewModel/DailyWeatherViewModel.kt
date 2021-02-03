package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.todayweather.repository.model.DailyWeather

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 오늘 날씨 데이터(오늘-모레까지 3시간 단위. 오늘 날씨의 경우는 현재시간으로 부터 2시간 데이터도 가져와야함)
 */
class DailyWeatherViewModel : BaseViewModel() {

    //라이브데이터
    val dailyWeather : MutableLiveData<DailyWeather> = MutableLiveData<DailyWeather>()

}