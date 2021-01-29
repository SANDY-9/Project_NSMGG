package com.example.todayweather.repository.retrofit

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.todayweather.model.ResponseDTO
import com.example.todayweather.model.ResponseDTO2
import com.example.todayweather.model.SkyDTO
import com.example.todayweather.model.TempDTO
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
class WeeklyRetrofit(var region : String, var city: String, var check:Int, val context: Context) {
    var regId = ""
    var tempDTO : TempDTO? = null
    var skyDTO : SkyDTO? = null

    // 하늘상태 regId 변환 함수
    private fun CityClassification() {

        Log.d("[test]", "$region, $city")


        if (region.contains("서울")||region.contains("인천")||region.contains("경기")) {
            regId = "11B00000"
        }else if (region.contains("대전")||region.contains("세종")||region.contains("충청남도")) {
            regId = "11C20000"
        }else if (region.contains("부산")||region.contains("울산")||region.contains("경상남도")) {
            regId = "11H20000"
        }else if (region.contains("광주")||region.contains("전라남도")) {
            regId = "11F20000"
        }else if (region.contains("대구")||region.contains("경상북도")) {
            regId = "11H10000"
        }else if (region.contains("강원도")) {
            if (    city.contains("고성")||city.contains("속초")||city.contains("양양")||
                city.contains("강릉")||city.contains("동해")||city.contains("삼척")||
                city.contains("태백")) {
                regId = "11D20000"
            }else{
                regId = "11D10000"
            }
        }else if (region.contains("충청북도")) {
            regId = "11C10000"
        }else if (region.contains("전라북도")) {
            regId = "11F10000"
        }else if (region.contains("제주도")) {
            regId = "11G00000"
        }
    }

    // 주간 온도
    interface RetrofitTempService {
        @GET("getMidTa?ServiceKey=dzreunK7XtadmHxjf8A9XrzxaHM%2FcnS7Iw2DuFjQRc4Q1BFGOkCmIhIRzbUOK7V0Ey8CNQJKci4xoJ6Bslps%2BQ%3D%3D&pageNo=1&numOfRows=10&dataType=JSON")
        fun getRegId(
            @retrofit2.http.Query("regId") regId: String,
            @retrofit2.http.Query("tmFc") tmFc: String
        ): Call<ResponseDTO>
    }
    // 주간 하늘상태 및 강수 확률
    interface RetrofitSkyService {
        @GET("getMidLandFcst?ServiceKey=dzreunK7XtadmHxjf8A9XrzxaHM%2FcnS7Iw2DuFjQRc4Q1BFGOkCmIhIRzbUOK7V0Ey8CNQJKci4xoJ6Bslps%2BQ%3D%3D&pageNo=1&numOfRows=10&dataType=JSON")
        fun getRegId(
            @retrofit2.http.Query("regId") regId: String,
            @retrofit2.http.Query("tmFc") tmFc: String
        ): Call<ResponseDTO2>
    }

    fun WeeklyRetrofit() { // 기온
        val date = Date()
        val sdf = SimpleDateFormat("yyyyMMdd")
        val format2 = SimpleDateFormat("HHmm")
        var tmFc = sdf.format(date)
        if (format2.format(date).toInt() < 1800 && format2.format(date).toInt() > 600) {
            tmFc += "0600"
        } else if (format2.format(date).toInt() > 1800) {
            tmFc += "1800"
        } else {
            val day = Calendar.getInstance()
            day.add(Calendar.DATE, -1)
            val beforeDate = SimpleDateFormat("yyyyMMdd").format(day.time)
            tmFc = beforeDate + "1800"
        }
        // 여기 수정
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/1360000/MidFcstInfoService/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        if (check==1){
            val api = retrofit.create(RetrofitTempService::class.java)
            val callGetTemp = api.getRegId(regId, tmFc)
            // response 구역
            callGetTemp.enqueue(object : Callback<ResponseDTO> {
                override fun onResponse(
                    call: Call<ResponseDTO>,
                    response: Response<ResponseDTO>
                ) {
                    if (response.body()!!.response.body.items.item.get(0) == null){
                        Log.d("[test]", "실패 : ${response.raw()}")
                        return
                    }
                    tempDTO = response.body()!!.response.body.items.item.get(0)
                    Log.d("[test]", "성공 : ${response.raw()}")
                    check = 2
                    CityClassification()
                    WeeklyRetrofit()
                }

                override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                    Toast.makeText(context, "데이터를 연결후 다시 시도해주시길 바랍니다.",Toast.LENGTH_SHORT).show()
                    Log.d("[test]", "실패 : $t")
                }
            })
        } else {
            val api = retrofit.create(RetrofitSkyService::class.java)
            val callGetSky = api.getRegId(regId, tmFc)
            callGetSky.enqueue(object : Callback<ResponseDTO2> {
                override fun onResponse(
                    call: Call<ResponseDTO2>,
                    response: Response<ResponseDTO2>
                ) {
                    if (response.body()!!.response.body.items.item.get(0) == null){
                        Log.d("[test]", "실패 : ${response.raw()}")
                        return
                    }
                    skyDTO = response.body()!!.response.body.items.item.get(0)
                    Log.d("[test]", "성공 : ${response.raw()}")
                    Log.d("[test]", "$skyDTO")

                    var rn3 = skyDTO!!.rnSt3Am
                    var rn4 = skyDTO!!.rnSt4Am
                    var rn5 = skyDTO!!.rnSt5Am
                    var rn6 = skyDTO!!.rnSt6Am
                    var rn7 = skyDTO!!.rnSt7Am

                    if (rn3 <= skyDTO!!.rnSt3Pm) {
                        rn3 = skyDTO!!.rnSt3Pm
                    }
                    if (rn4 <= skyDTO!!.rnSt4Pm) {
                        rn4 = skyDTO!!.rnSt4Pm
                    }
                    if (rn5 <= skyDTO!!.rnSt5Pm) {
                        rn5 = skyDTO!!.rnSt5Pm
                    }
                    if (rn6 <= skyDTO!!.rnSt6Pm) {
                        rn6 = skyDTO!!.rnSt6Pm
                    }
                    if (rn7 <= skyDTO!!.rnSt7Pm) {
                        rn7 = skyDTO!!.rnSt7Pm
                    }
//                    textView5!!.text = """
//                            3일뒤 최저온도 : ${tempDTO!!.taMin3}, 최고온도 : ${tempDTO!!.taMax3},
//                            강수확률 : ${rn3}%, 하늘상태 : ${skyDTO!!.wf3Pm}
//
//                            4일뒤 최저온도 : ${tempDTO!!.taMin4}, 최고온도 : ${tempDTO!!.taMax4},
//                            강수확률 : ${rn4}%, 하늘상태 : ${skyDTO!!.wf4Pm}
//
//                            5일뒤 최저온도 : ${tempDTO!!.taMin5}, 최고온도 : ${tempDTO!!.taMax5},
//                            강수확률 : ${rn5}%, 하늘상태 : ${skyDTO!!.wf5Pm}
//
//                            6일뒤 최저온도 : ${tempDTO!!.taMin6}, 최고온도 : ${tempDTO!!.taMax6},
//                            강수확률 : ${rn6}%, 하늘상태 : ${skyDTO!!.wf6Pm}
//
//                            7일뒤 최저온도 : ${tempDTO!!.taMin7}, 최고온도 : ${tempDTO!!.taMax7},
//                            강수확률 : ${rn7}%, 하늘상태 : ${skyDTO!!.wf7Pm}
//                            """.trimIndent()

                }

                override fun onFailure(call: Call<ResponseDTO2>, t: Throwable) {
                    Toast.makeText(context, "데이터를 연결후 다시 시도해주시길 바랍니다.", Toast.LENGTH_SHORT).show()
                    Log.d("[test]", "실패 : $t")
                }
            })
        }
    }
}