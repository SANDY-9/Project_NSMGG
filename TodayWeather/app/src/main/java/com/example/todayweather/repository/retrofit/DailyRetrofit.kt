package com.example.todayweather.repository.retrofit

import android.util.Log
import com.example.todayweather.model.NowDTO
import com.example.todayweather.model.ResponseDTONow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author seungwon
 * @email aoqnwnd@naver.com
 * @created 2021-01-28
 * @desc
 */

class DailyRetrofit( cnt :Int ) {

    var check = cnt

    var nowDTO : List<NowDTO>? = null

    // 현재 온도
    interface RetrofitNowService {
        @GET("getUltraSrtNcst?ServiceKey=dzreunK7XtadmHxjf8A9XrzxaHM%2FcnS7Iw2DuFjQRc4Q1BFGOkCmIhIRzbUOK7V0Ey8CNQJKci4xoJ6Bslps%2BQ%3D%3D&pageNo=1&dataType=json&numOfRows=8")
        fun getRegId(
            @retrofit2.http.Query("base_time") base_time : String,
            @retrofit2.http.Query("base_date") base_date : String,
            @retrofit2.http.Query("nx") nx : Int,
            @retrofit2.http.Query("ny") ny : Int
        ): Call<ResponseDTONow>
    }
    // 기상청 API 호출
    fun weather(nx: Int, ny: Int) { // nx,ny = 기상청 api에서 제공하는 값을 도출한 값
        val date = Date()
        val sdf = SimpleDateFormat("yyyyMMdd")
        val format2 = SimpleDateFormat("HHmm")
        val format3 = SimpleDateFormat("mm")
        var mm = format3.format(date).toInt()
        val baseDate = sdf.format(date) //조회하고싶은 날짜
        var baseTime: String?

        // api제공시간이 매시간 40분에 제공하기에 40분 이전에는 1시간 전 자료 찾기
        if (mm < 40) {
            mm = format2.format(date).toInt() - 100
            baseTime = mm.toString() //API 제공 시간
        } else {
            baseTime = format2.format(date)
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        if (check==1){
            val api = retrofit.create(RetrofitNowService::class.java)
            val callGetTemp = api.getRegId(baseTime!!, baseDate, nx, ny)
            callGetTemp.enqueue(object : Callback<ResponseDTONow> {
                override fun onResponse(
                    call: Call<ResponseDTONow>,
                    response: Response<ResponseDTONow>
                ) {
                    if (response.body()!!.response.body.items.item == null){
                        Log.d("[test_response]", "null값 }")
                        return
                    }
                    nowDTO = response.body()!!.response.body.items.item
                    Log.d("[test_response]", "성공 : ${response.raw()}")

                    var str : String? =  ""

                    for (i in 0..nowDTO!!.size-1){
                        if (nowDTO!![i].category ==  "PTY"){

                            // 강수형태 : 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4), 빗방울(5), 빗방울/눈날림(6), 눈날림(7)
                            nowDTO!![i].category = "강수형태"
                            if (nowDTO!![i].obsrValue == "0"){
                                nowDTO!![i].obsrValue = "없음"
                            } else if (nowDTO!![i].obsrValue == "1"){
                                nowDTO!![i].obsrValue = "비"
                            } else if (nowDTO!![i].obsrValue == "2"){
                                nowDTO!![i].obsrValue = "진눈개비(비/눈)"
                            } else if (nowDTO!![i].obsrValue == "3"){
                                nowDTO!![i].obsrValue = "눈"
                            } else if (nowDTO!![i].obsrValue == "4"){
                                nowDTO!![i].obsrValue = "소나기"
                            } else if (nowDTO!![i].obsrValue == "5"){
                                nowDTO!![i].obsrValue = "빗방울"
                            } else if (nowDTO!![i].obsrValue == "6"){
                                nowDTO!![i].obsrValue = "빗방울/눈날림"
                            } else if (nowDTO!![i].obsrValue == "7"){
                                nowDTO!![i].obsrValue = "눈날림"
                            }

                        } else if(nowDTO!![i].category ==  "REH"){
                            nowDTO!![i].category = "습도"
                            nowDTO!![i].obsrValue += "%"
                        } else if(nowDTO!![i].category ==  "RN1"){
                            nowDTO!![i].category = "1시간 강수량"
                            nowDTO!![i].obsrValue += "mm"
                        } else if(nowDTO!![i].category ==  "T1H"){
                            nowDTO!![i].category = "기온"
                            nowDTO!![i].obsrValue += "℃"
                        } else if(nowDTO!![i].category ==  "VEC"){

                            // 풍향 값 바꾸기
                            nowDTO!![i].category = "풍향"
                            val value = Integer.parseInt(nowDTO!![i].obsrValue)
                            if (value<45){
                                nowDTO!![i].obsrValue = "북동북"
                            }else if (value>=45||value<90){
                                nowDTO!![i].obsrValue = "동북동"
                            }else if (value>=90||value<135){
                                nowDTO!![i].obsrValue = "동남동"
                            }else if (value>=135||value<180){
                                nowDTO!![i].obsrValue = "남동남"
                            }else if (value>=180||value<225){
                                nowDTO!![i].obsrValue = "남서남"
                            }else if (value>=225||value<270){
                                nowDTO!![i].obsrValue = "서남서"
                            }else if (value>=270||value<315){
                                nowDTO!![i].obsrValue = "서북서"
                            }else if (value>=315||value<360){
                                nowDTO!![i].obsrValue = "북서북"
                            }

                        } else if(nowDTO!![i].category ==  "WSD"){
                            nowDTO!![i].category = "풍속"
                            nowDTO!![i].obsrValue += "m/s"
                        } else if(nowDTO!![i].category ==  "VVV"){
//                        남북바람성분(VVV) : 북(+표기), 남(-표기)
                            nowDTO!![i].category = "남북바람성분"
                            nowDTO!![i].obsrValue += "m/s(북(+표기), 남(-표기))"

                        } else if(nowDTO!![i].category ==  "UUU"){
//                        동서바람성분(UUU) : 동(+표기), 서(-표기)
                            nowDTO!![i].category = "동서바람성분"
                            nowDTO!![i].obsrValue += "m/s(동(+표기), 서(-표기))"
                        }
//                    str += "${nowDTO!![i].category}= ${nowDTO!![i].obsrValue}\n"

                    }
                    Log.d("[test]", "nowDTO : ${nowDTO.toString()}")

                }

                override fun onFailure(call: Call<ResponseDTONow>, t: Throwable) {
                    Log.d("[test]", "실패 : $t")
                }
            })
        }else if (check==2){
            // 여기에 똑같이 쓰면 됨
        }
    }
}