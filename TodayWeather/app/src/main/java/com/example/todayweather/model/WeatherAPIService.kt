package com.example.todayweather.model

import com.example.todayweather.model.response.*
import com.google.firebase.inject.Deferred
import okhttp3.Interceptor
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author seungwon
 * @email aoqnwnd@naver.com
 * @created 2021-01-28
 * @desc 기상청 날씨 API를 레트로핏 라이브러리와 맞춰서 사용하기 위한 인터페이스
 */

interface WeatherAPIService {

    //현재 날씨 요청
    //초단기실황 조회
    @GET("VilageFcstInfoService/getUltraSrtNcst?numOfRows=8&pageNo=1&dataType=JSON")
    fun getCurrentWeather (
            @Query("base_date") base_date: String,  //현재날짜
            @Query("base_time") base_time: String,  //현재시각으로 요청
            @Query("nx") nx : Int,                  //날씨요청위치 x격자값
            @Query("ny") ny : Int,                  //날씨요청위치 y격자값
            @Query("serviceKey") serviceKey : String
    ) : kotlinx.coroutines.Deferred<CurrentWeatherResponse>

    //오늘 날씨 요청
    //초단기예보 조회 : 앞으로의 6시간 요청.
    @GET("VilageFcstInfoService/getUltraSrtFcst?numOfRows=25&pageNo=1&dataType=JSON")
    fun getTodayWeather_time (
            @Query("base_date") base_date: String,  //현재날짜
            @Query("base_time") base_time: String,  //현재시각으로 요청
            @Query("nx") nx : Int,                  //날씨요청위치 x격자값
            @Query("ny") ny : Int,                  //날씨요청위치 y격자값
            @Query("serviceKey") serviceKey : String
    ) : TodayWeatherTimeResponse

    //동네예보 조회 : 오늘-내일-모레까지 날씨 정보(base_time 기준+4시간부터 제공)
    @GET("VilageFcstInfoService/getVilageFcst?numOfRows=225&pageNo=1&dataType=JSON")
    fun getTodayWeather_day (
            @Query("base_date") base_date: String,  //현재날짜
            @Query("base_time") base_time: String,  //0200 0500 0800 1100 1400 1700 2000 2300
            @Query("nx") nx : Int,                  //날씨요청위치 x격자값
            @Query("ny") ny : Int,                  //날씨요청위치 y격자값
            @Query("serviceKey") serviceKey : String
    ) : TodayWeatherDayResponse


    //주간 날씨 요청
    //강수확률, 날씨(하늘상태) 조회
    @GET("MidFcstInfoService/getMidLandFcst?&numOfRows=1&pageNo=1&dataType=JSON")
    fun getWeeklyWeather_sky (
            @Query("regId") regId : String,         //중기육상예보구역 코드
            @Query("tmFc") tmFc : String,           //tmFc ex)202101281800, 날짜+발표시간(0600, 1800)
            @Query("serviceKey") serviceKey : String
    ) : WeeklyWeatherSkyResoponse

    //최저, 최고기온 조회
    @GET("MidFcstInfoService/getMidTa?&numOfRows=1&pageNo=1&dataType=JSON")
    fun getWeeklyWeather_temp (
            @Query("regId") regId : String,         //중기기온예보구역 코드
            @Query("tmFc") tmFc : String,           //tmFc ex)202101281800, 날짜+발표시간(0600, 1800)
            @Query("serviceKey") serviceKey : String
    ) : WeeklyWeatherTempResponse

    companion object {
        operator fun invoke() : WeatherAPIService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                        .url()
                        .newBuilder()
                        .build()
                var request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                return@Interceptor chain.proceed(request)
            }
        }
    }

}