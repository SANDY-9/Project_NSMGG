package com.example.todayweather.data.model

import com.example.todayweather.helper.ConvertDateHelper
import com.example.todayweather.helper.ConvertWeatherHelper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-07
 * @desc
 */

//현재날씨 조회 데이터 저장하는 DataModel(초단기 실황조회) -> 결과 JSON obsrValue값 전부 String
data class CurrentWeather (
        var date: String,                   //현재시각
        var region: String,                 //지역
        var temperature: String,            //기온
        var feeltemperature : String,       //체감온도
        var rainfall : String,              //강수량
        var humid: String,                  //습도
        var weather: String,                //날씨
        var wind: String,                   //풍속
        var dust: String                    //미세먼지
)

data class DailyWeather (
        var region: String,                 //지역
        var fcstTime : String,              //시간
        var temperature: String,            //온도
        var weather : String,               //날씨
        var wind: String,                   //풍속
        var rainfall : String              //강수량
)

data class WeeklyWeather(
        var region: String,                 //지역
        var date: String,                   //날짜
        var rainPossible : Int,             //강수확률
        var weather : String,               //날씨
        var tempMin : Int,                  //최저기온
        var tempMax : Int                   //최고기온
)