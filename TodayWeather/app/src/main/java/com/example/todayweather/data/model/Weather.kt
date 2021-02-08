package com.example.todayweather.data.model

import com.example.todayweather.R
import com.example.todayweather.helper.CalculationHelper
import com.example.todayweather.helper.ConvertDateHelper
import com.example.todayweather.helper.ConvertWeatherHelper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-07
 * @desc
 */

//현재 날씨
class CurrentWeather {
        val date: String = ConvertDateHelper.dateFormToday()       //현재시각
        var region: String? = null                 //지역
        var temperature: String? = null            //기온
        val feeltemperature: String? = null        //체감온도
        var rainfall: String? = null               //강수량
        var humid: String? = null                  //습도
        var weather: String? = null                //날씨
        var wind: String? = null                   //풍속
        var dust: String? = null                   //미세먼지
        var icon : Int = R.drawable.icon_sun       //날씨 아이콘
        override fun toString(): String {
                return "CurrentWeather(date='$date', region=$region, temperature=$temperature, feeltemperature=$feeltemperature, rainfall=$rainfall, humid=$humid, weather=$weather, wind=$wind, dust=$dust)"
        }

}

//오늘 날씨
class DailyWeather {
        var date: String? = null                   //날짜
        var region: String? = null                 //지역
        var fcstTime: String? = null               //시간
        var temperature: String? = null            //온도
        var weather: String? = null                //날씨
        var wind: String? = null                   //풍속
        var rainfall: String? = null               //강수량
        var icon : Int = R.drawable.icon_sun       //날씨 아이콘
        override fun toString(): String {
                return "DailyWeather(date=$date, region=$region, fcstTime=$fcstTime, temperature=$temperature, weather=$weather, wind=$wind, rainfall=$rainfall)"
        }
}

//주간 날씨
class WeeklyWeather {
        var region: String? = null                 //지역
        var date: String? = null                   //날짜
        var rainPossible: Int? = null             //강수확률
        var weather: String? = null               //날씨
        var tempMin: Int? = null                  //최저기온
        var tempMax: Int? = null                  //최고기온
        var icon : Int = R.drawable.icon_sun      //날씨 아이콘
        override fun toString(): String {
                return "WeeklyWeather(region='$region', date='$date', rainPossible=$rainPossible, weather='$weather', tempMin=$tempMin, tempMax=$tempMax)"
        }

}