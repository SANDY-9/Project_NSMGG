package com.example.todayweather.repository.model

import com.example.todayweather.helper.ConvertDateHelper
import com.example.todayweather.helper.ConvertWeatherHelper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-23
 * @desc 오늘 날씨 조회하는 DataModel(초단기 예보조회, 동네예보 조회) -> 결과 JSON obsrValue값 전부 String. 용도 : 가로 리사이클러뷰
 */

open class DailyWeather {

    //지역
    var region: String
        get() {
            return region
        }
        set(value) {}

    //시간
    var fcstTime : String
        get() {
            return fcstTime
        }
        set(value) {
            fcstTime = ConvertDateHelper.dateFormTime(value)
        }

    //기온
    var temperature: String
        get() {
            return temperature
        }
        set(value) {
            temperature = value
        }

    var SKY : String
        get() {
            return SKY
        }
        set(value) {
            SKY = value
        }

    var PTY : String
        get() {
            return PTY
        }
        set(value) {
            PTY = value
        }

    //날씨
    var weather : String = ""
        get() {
            return ConvertWeatherHelper.convertWeather(SKY, PTY)
        }
}

