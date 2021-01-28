package com.example.todayweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todayweather.helper.ConvertDateHelper
import com.google.gson.annotations.SerializedName

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-24
 * @desc 주간날씨 조회하는 DataModel
 */
class WeeklyWeather() {

    //지역
    var region: String
        get() {
            return region
        }
        set(value) {
            region = value
        }

    //날짜 (ConvertDateHelper 오브젝트 클래스에 dateFormWeek()함수 만들어놨어요 이 함수 for문 돌려서 저장하면됩니당
    var date: String
        get() {
            return date
        }
        set(value) {
            date = value
        }

    //강수확률
    var rainPossible : Int
        get() {
            return rainPossible
        }
        set(value) {
            rainPossible = value
        }

    //날씨
    var weather : String
        get() {
            return weather
        }
        set(value) {
            weather = value
        }

    //최저기온
    var tempMin : Int
        get() {
            return tempMin
        }
        set(value) {
            tempMin = value
        }

    //최고기온
    var tempMax : Int
        get() {
            return tempMax
        }
        set(value) {
            tempMax = value
        }
}

