package com.example.todayweather.data.network

import android.util.Log
import com.example.todayweather.data.network.response.FineDustResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-03
 * @desc 미세먼지 정보를 위해 환경공단 대기상태 API를 요청하기 위한 레트로핏 형식을 저장한 인터페이스 (응답 형식 : XML)
 */

interface AirAPIService {

    //시군구별 실시간 미세먼지 조회
    @GET("getCtprvnMesureSidoLIst?&numOfRows=35&pageNo=1&searchCondition=HOUR&" +
            "serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D")
    fun getFineDust (
            @Query("sidoName") sidoName : String           // 시군구 이름
    ) : Observable<FineDustResponse>


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
                .baseUrl("http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/")
                .addConverterFactory(SimpleXmlConverterFactory.create())     // xml 파싱을 위한 컨버터
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(AirAPIService::class.java)
        }
    }
}