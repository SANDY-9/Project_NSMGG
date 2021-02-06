package com.example.todayweather.data.model

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

        )