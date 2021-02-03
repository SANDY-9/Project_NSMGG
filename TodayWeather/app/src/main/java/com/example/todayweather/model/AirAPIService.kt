package com.example.todayweather.model

import android.location.Address
import com.example.todayweather.model.response.DustLocalResponse
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
    @GET("MsrstnInfoInqireSvc/getMsrstnList?numOfRows=1&pageNo=1&returnType=JSON")
    fun getDustFindStation (
            @Query("addr") addr : String,                     // 주소에서 locality부분
            @Query("serviceKey") serviceKey : String
    )

    //측정소로 미세먼지 검색
    @GET("ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?numOfRows=1&pageNo=1&dataTerm=DAILY&ver=1.3&returnType=JSON")
    fun getDustStation (
            @Query("stationNam") stationName : String,       // 측정소이름
            @Query("serviceKey") serviceKey : String
    )


    //case 2 시도별 미세먼지 조회
    @GET("ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?numOfRows=100&pageNo=1&ver=1.3&returnType=JSON")
    fun getDustLocal (
            @Query("sidoName") sidoName : String,           // 시 도 이름
            @Query("serviceKey") serviceKey : String
    ) : DustLocalResponse
}