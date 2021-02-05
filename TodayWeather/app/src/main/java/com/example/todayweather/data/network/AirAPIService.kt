package com.example.todayweather.data.network

import android.util.Log
import com.example.todayweather.data.network.response.DustFindStationResponse
import com.example.todayweather.data.network.response.DustLocalResponse
import com.example.todayweather.data.network.response.DustStationResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-03
 * @desc 환경공단 대기상태 API를 레트로핏 라이브러리와 맞춰서 사용하기 위한 인터페이스
 */
interface AirAPIService {

    //case 1 측정소로 미세먼지 조회(동기 요청)
    //측정소 검색 : 검색 결과에서 측정소 1개만 가져옴
    @GET("MsrstnInfoInqireSvc/getMsrstnList?numOfRows=1&pageNo=1&returnType=JSON&" +
            "serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D")
    fun getDustFindStation (
            @Query("addr") addr : String                     // 주소에서 locality부분
    ) : Deferred<DustFindStationResponse>

    //측정소로 미세먼지 검색
    @GET("ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?numOfRows=1&pageNo=1&dataTerm=DAILY&ver=1.3&returnType=JSON&" +
            "serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D")
    fun getDustStation (
            @Query("stationName") stationName : String       // 측정소이름
    ) : Deferred<DustStationResponse>


    //case 2 시도별 미세먼지 조회
    @GET("ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?numOfRows=100&pageNo=1&ver=1.3&returnType=JSON")
    fun getDustLocal (
            @Query("sidoName") sidoName : String,           // 시 도 이름
            @Query("serviceKey") serviceKey : String
    ) : Deferred<DustLocalResponse>


    companion object {
        operator fun invoke() : AirAPIService {
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                Log.e("[요청 URL]", url.toString())
                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://apis.data.go.kr/B552584/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())   // Deferred<DustFindStationResponse>를 위함
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AirAPIService::class.java)
        }
    }
}