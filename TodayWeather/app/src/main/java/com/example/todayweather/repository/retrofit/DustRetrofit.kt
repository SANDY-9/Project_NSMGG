package com.example.todayweather.repository.retrofit

import android.content.Context
import com.example.todayweather.model.ResponseDust
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*


/**
 * @author byel
 * @email ehdzldxn3@naver.com
 * @created 2021-01-30
 * @desc
 */
class DustRetrofit(val context: Context) {



    //최신 미세먼지 1개 정보조회
    // 서비스키:선누 / 리턴타입:json / numOfRows & pageNo : 1 / dataTerm : DAILY / ver : 1.3
    interface RetrofitDust {
        @GET("B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D&returnType=json&numOfRows=1&pageNo=1&dataTerm=DAILY&ver=1.3")
        fun getRegId(
                @retrofit2.http.Query("stationName") stationName: String,
        ): Call<ResponseDust>
    }


    fun dailyDust(stationName: String) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val api = retrofit.create(DustRetrofit.RetrofitDust::class.java)
        val callGetTemp = api.getRegId(stationName = stationName)
        callGetTemp.enqueue(object : Callback<ResponseDust> {
            override fun onResponse(call: Call<ResponseDust>, response: Response<ResponseDust>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<ResponseDust>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }


}