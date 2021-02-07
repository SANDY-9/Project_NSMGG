package com.example.todayweather.data.network

import android.util.Log
import com.example.todayweather.data.network.response.*
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    @GET("VilageFcstInfoService/getUltraSrtNcst?numOfRows=8&pageNo=1&dataType=json&" +
            "serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D")
    suspend fun getCurrentWeather(
        @Query("base_date") base_date: String,  //현재날짜
        @Query("base_time") base_time: String,  //현재시각으로 요청
        @Query("nx") nx: Int,                  //날씨요청위치 x격자값
        @Query("ny") ny: Int,                  //날씨요청위치 y격자값
    ) : Response<CurrentWeatherResponse>

    //오늘 날씨 요청
    //초단기예보 조회 : 앞으로의 6시간 요청.
    @GET("VilageFcstInfoService/getUltraSrtFcst?numOfRows=25&pageNo=1&dataType=JSON&" +
            "serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D")
    suspend fun getTodayWeather_time(
        @Query("base_date") base_date: String,  //현재날짜
        @Query("base_time") base_time: String,  //현재시각으로 요청
        @Query("nx") nx: Int,                  //날씨요청위치 x격자값
        @Query("ny") ny: Int,                  //날씨요청위치 y격자값
    ) : Response<TodayWeatherTimeResponse>

    //동네예보 조회 : 오늘-내일-모레까지 날씨 정보(base_time 기준+4시간부터 제공)
    @GET("VilageFcstInfoService/getVilageFcst?numOfRows=225&pageNo=1&dataType=JSON&" +
            "serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D")
    suspend fun getTodayWeather_day(
        @Query("base_date") base_date: String,  //현재날짜
        @Query("base_time") base_time: String,  //0200 0500 0800 1100 1400 1700 2000 2300
        @Query("nx") nx: Int,                  //날씨요청위치 x격자값
        @Query("ny") ny: Int,                  //날씨요청위치 y격자값
    ) : Response<TodayWeatherDayResponse>

    //지상 일자료 조회
    @GET("AsosDalyInfoService/getWthrDataList?dateCd=DAY&dataCd=ASOS&numOfRows=10&pageNo=1&dataType=json&"+
            "serviceKey=t1uVDu2qW09XOkvxKCDdhD%2FsgeRsWUNv4sGSNDdoZKNHlcW4rqlJEXBnV8a3lLNx9yAsTJReQ%2BlbDoOhkKFeZA%3D%3D")
    suspend fun getWthrDataList_yesterday(
            @Query("startDt") startDt: String,  //조회 시작 날짜
            @Query("endDt") endDt: String,  //조회 종료 날짜
            @Query("stnIds") stnIds: Int,   //지점번호
    ) : Response<YesterdayWeatherResponse>

    //주간 날씨 요청샤ㅡ
    //강수확률, 날씨(하늘상태) 조회
    @GET("MidFcstInfoService/getMidLandFcst?&numOfRows=1&pageNo=1&dataType=JSON")
    suspend fun getWeeklyWeather_sky(
        @Query("regId") regId: String,         //중기육상예보구역 코드
        @Query("tmFc") tmFc: String,           //tmFc ex)202101281800, 날짜+발표시간(0600, 1800)
        @Query("serviceKey") serviceKey: String
    ) : Response<WeeklyWeatherSkyResoponse>

    //최저, 최고기온 조회
    @GET("MidFcstInfoService/getMidTa?&numOfRows=1&pageNo=1&dataType=JSON")
    suspend fun getWeeklyWeather_temp(
        @Query("regId") regId: String,         //중기기온예보구역 코드
        @Query("tmFc") tmFc: String,           //tmFc ex)202101281800, 날짜+발표시간(0600, 1800)
        @Query("serviceKey") serviceKey: String
    ) : Response<WeeklyWeatherTempResponse>


    companion object {
        operator fun invoke(
                //connectivityInterceptor: ConnectivityInterceptor    //인터넷연결상태 확인하는 intercepter 사용을 위해.
        ) : WeatherAPIService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                        .url()
                        .newBuilder()
                        .build()
                var request = chain.request()
                        .newBuilder()
                        .url(url)
                        .build()
                Log.e("[요청 URL]", url.toString())
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                   // .addInterceptor(connectivityInterceptor)
                    .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://apis.data.go.kr/1360000/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(WeatherAPIService::class.java)
        }


    }

}