package com.example.todayweather.repository.retrofit

import android.content.Context
import android.util.Log
import com.example.todayweather.model.DustAddrDTO
import com.example.todayweather.model.DustDTO
import com.example.todayweather.model.ResponseDust
import com.example.todayweather.model.ResponseDustAddr
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

    var dustDTO : List<DustDTO>? = null
    var TAG = "DustRetrofit"
    var dustAddrDTO : List<DustAddrDTO>? = null


    //최신 미세먼지 1개 정보조회
    // 서비스키:선누 / 리턴타입:json / numOfRows & pageNo : 1 / dataTerm : DAILY / ver : 1.3
    interface RetrofitDust {
        @GET("B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D&returnType=json&numOfRows=1&pageNo=1&dataTerm=DAILY&ver=1.3")
        fun getRegId(
                @retrofit2.http.Query("stationName") stationName: String,
        ): Call<ResponseDust>
    }

    // 미세먼지 측정소 1개 정보조회
    // 서비스키:선누 / 리턴타입:json / numOfRows & pageNo : 1
    interface RetrofitDustAddr {
        @GET("B552584/MsrstnInfoInqireSvc/getMsrstnList?serviceKey=qVcayJVu5HI9ugEnsD8DMqMSvIaPsRkW4zOTbAYQkEgop3%2FHRG8WRp52RND8MUyG0r%2Fh6VZqDVsCkGk7o%2BWGgg%3D%3D&returnType=json&numOfRows=1&pageNo=1")
        fun getRegId(
                @retrofit2.http.Query("addr") addr: String,
        ): Call<ResponseDustAddr>
    }



    //미세먼지 측정소 불러오는 함수 (미세먼지 측정소 스테이션네임 돌려줌 )
    fun dustAddr(addr: String):String{
        var stationName:String = "바껴라얍"
        //레트로핏 선언언
       val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val dustAddrRetrofit = retrofit.create(DustRetrofit.RetrofitDustAddr::class.java)
        val callGetTemp = dustAddrRetrofit.getRegId(addr = addr)
        callGetTemp.enqueue(object : Callback<ResponseDustAddr> {

            override fun onResponse(call: Call<ResponseDustAddr>, response: Response<ResponseDustAddr>) {
                dustAddrDTO = response.body()!!.response.body.items
                 stationName = dustAddrDTO!![0].stationName
                Log.e(TAG, "onResponse: "+dustAddrDTO)
            }

            override fun onFailure(call: Call<ResponseDustAddr>, t: Throwable) {
                Log.e(TAG, "onFailure: 미세먼지 측정소", )

            }
        })
        return stationName
    }

    //미세먼지 불러오는 함수
    fun dailyDust(stationName: String) {
        //레트로핏 선언
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://apis.data.go.kr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val dustRetrofit = retrofit.create(DustRetrofit.RetrofitDust::class.java)
        val callGetTemp = dustRetrofit.getRegId(stationName = stationName)
        callGetTemp.enqueue(object : Callback<ResponseDust> {
            override fun onResponse(call: Call<ResponseDust>, response: Response<ResponseDust>) {



                Log.e(TAG, "onResponse: "+dustDTO )


                var pm10Grade1h = dustDTO!![0].pm10Grade1h  //미세먼지 점수(1시간)
                var pm10Flag = dustDTO!![0].pm10Flag    //미세먼지 장비점검

                var pm25Value = dustDTO!![0].pm25Value  //초미세먼지농도
                var pm25Grade1h = dustDTO!![0].pm25Grade1h  //초미세먼지 점수(1시간)
                var pm25Flag = dustDTO!![0].pm25Flag  //초미세먼지 장비점검

                //화면에 셋팅해야함!
                if(pm10Flag == null) {
                    var test1 = "미세먼지"
                    var test2 = dustGreade(Integer.parseInt(pm10Grade1h))

                    Log.e(TAG, "onResponse: "+test1+test2)


                } else if(pm25Flag == null) {
                    var test3 = "초미세먼지"
                    var test4 = dustGreade(Integer.parseInt(pm25Grade1h))
                    Log.e(TAG, "onResponse: "+test3+test4)

                } else {
                    var test5 = "시스템 점검"
                    Log.e(TAG, "onResponse: "+test5 )
                }
            }

            override fun onFailure(call: Call<ResponseDust>, t: Throwable) {
                Log.e(TAG, "onFailure: ", )
            }
        })

    }

    //미세먼지 점수 매기는 함수
    fun dustGreade(grade: Int): String {
        var dust : String
        when(grade){
            1 -> dust = "좋음"
            2 -> dust = "보통"
            3 -> dust = "나쁨"
            4 -> dust = "매우나쁨"
            else -> dust = "값이 없음"
        }
        return dust
    }

}