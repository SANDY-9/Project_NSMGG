package com.example.todayweather.model

import com.example.todayweather.helper.ConvertDateHelper
import com.example.todayweather.helper.ConvertWeatherHelper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-28
 * @desc 현재날씨 조회 데이터 저장하는 DataModel(초단기 실황조회) -> 결과 JSON obsrValue값 전부 String
 */

open class CurrentWeather {

    //현재시각
    var date: String = ""
        get() {
            return ConvertDateHelper.dateFormToday()
        }

    //지역
    var region: String
        get() {
           return region
        }
        set(value) {}

    //기온
    var temperature: String
        get() {
            return temperature
        }
        set(value) {
            temperature = value
        }

    //강수량
    var rainfall: String
        get() {
            return rainfall
        }
        set(value) {
            rainfall = ConvertWeatherHelper.convertRainfall(value)
        }

    //습도
    var humid: String
        get() {
           return humid
        }
        set(value) {
            humid = value
        }

    //강수 형태
    var rain: String
        get() {
            return rain
        }
        set(value) {
            rain = ConvertWeatherHelper.convertPTY(value)
        }

    //풍속
    var wind: String
        get() {
            return wind
        }
        set(value) {

        }

    //미세먼지
    var dust: String
        get() {
            return dust
        }
        set(value) {
            dust = ConvertWeatherHelper.convertDust(value)
        }
}