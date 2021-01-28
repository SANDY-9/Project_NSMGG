package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayweather.helper.CalculationHelper
import com.example.todayweather.helper.ConvertWeatherHelper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 현재 날씨 관련 정보 데이터
 */
class CurrentWeatherViewModel : ViewModel() {

    val date : MutableLiveData<String> = MutableLiveData<String>()

    init {
     //   date.value =
    }

    //    var date : String
//        get() {
//            return date
//        }
//        set(value) {
//            date = value
//        }

    var region : String
        get() {
            return region
        }
        set(value) {
            region = value
        }

    //현재 기온
    var temperature : Int
        get() {
            return temperature
        }
        set(value) {
            temperature = value
        }

    //체감온도
    var feeltemperature : String
        get() {
            return feeltemperature
        }
        set(value) {
            val feeltemperature_calc = CalculationHelper.convertFeelTemperature(temperature, wind)
            if (feeltemperature_calc < 0) {
                feeltemperature = "영하 ${feeltemperature_calc}도"
            } else {
                feeltemperature = "${feeltemperature_calc}도"
            }
        }

    //현재 날씨(모델에서 데이터 받아올 때 날씨로 해석해서 String 자료형으로 저장)
    var weather : String
        get() {
            return weather
        }
        set(value) {
            weather = value
        }

    //풍속
    var wind : Double
        get() {
            return wind
        }
        set(value) {
            wind = value
        }

    //강수량
    var rainfall : String
        get() {
            return rainfall
        }
        set(value) {
            rainfall = ConvertWeatherHelper.convertRainfall(value)
        }

    //습도
    var humidity : Int
        get() {
            return humidity
        }
        set(value) {
            humidity = value
        }

    //미세먼지
    var dust : String
        get() {
            return dust
        }
        set(value) {}

}