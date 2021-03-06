package com.example.todayweather.data.network.temporary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.model.DailyWeather
import com.example.todayweather.data.model.WeeklyWeather
import com.example.todayweather.data.network.AirAPIService
import com.example.todayweather.data.network.WeatherAPIService
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class RetrofitNetWorkImpl2(
    private val weatherAPIService : WeatherAPIService,
    private val airAPIService: AirAPIService
)  {

    val base_time = SimpleDateFormat("hhmm").format(Date())
    val base_date = SimpleDateFormat("yyyyMMdd").format(Date())

    val TAG = "RetrofitNetWorkImpl"

    val downloadCurrentWeather: LiveData<CurrentWeather>
        get() = MutableLiveData<CurrentWeather>()

    val downloadDailyWeather: LiveData<DailyWeather>
        get() = MutableLiveData<DailyWeather>()

    val downloadWeeklyWeather: LiveData<WeeklyWeather>
        get() = MutableLiveData<WeeklyWeather>()

    suspend fun fetchCurrentWeather(nx: Int, ny: Int, addr: String) {
            //현재 날씨 요청
            val fetchCurrentWeather = weatherAPIService
                    .getCurrentWeather(base_date, base_time, nx, ny)

    }


    /*
    요청할수 있는 시간
    0200 0500 0800 1100 1400 1700 2000 2300

    전날을 요청할수 있는 조건
    1. 현재시각으로부터 24시간 전은 요청할수있다
    예: 현재 = 날짜 210206 / 시간 1600
        요청 가능시간 = 날짜 210205 / 시간 1700 / 2000 / 2300 요청가능

    0000~0159 : 전날 2300 요청하기
     */

    //오늘 날씨 요청
    suspend fun fetchDailyWeather(nx: Int, ny: Int, stnIds: Int) {
        //현재시간
        var now_timeHH = SimpleDateFormat("HH").format(Date())
        var now_timeMM = SimpleDateFormat("mm").format(Date())

        //동네예보 변수
        var day_date = base_date
        var day_time = base_time

        //초단기예보 변수
        var time_date = base_date
        var time_time = base_time

        //지상 일자료조회 (어제 날씨 조회) 변수
        var yesterday = date_Change()

        //동네예보
        // 00시 ~ 01시면 전날로 변경
        if(now_timeHH == "00" || now_timeHH == "01") {day_date = date_Change()}
        //0200 0500 0800 1100 1400 1700 2000 2300 요청시간에 맞게 시간 변경
        day_time = day_time_Change(now_timeHH)

        //초단기예보
        //00시면 전날로 변경 및 요청시간 1130으로 변경
        if(now_timeHH == "00") {
            time_date = date_Change()
            time_time = "1130"
        }
        //00분 ~ 29분 1시간전으로 되돌리기
        //if(Integer.parseInt(now_timeMM)>30) {time_time = time_time_Change(now_timeHH)}

            //동네예보조회
            val todayWeather_day = weatherAPIService.getShortermWeather_day(day_date, day_time, nx, ny)

            //초단기예보조회
            val todayWeather_time = weatherAPIService.getShortermWeather_time(time_date, time_time, nx, ny)

            //지상 일자료조회 (어제 날씨 조회)
            val yesterdayWeather_day = weatherAPIService.getYesterdayWeather(yesterday,yesterday,stnIds)

            //Log.e(TAG, ""+yesterdayWeather_day.body()!!.response.body.items.item[0])


    }


    suspend fun fetchWeeklyWeather(nx: Int, ny: Int, addr: String) {

    }



    //전날로 바꿔주는 함수 (동네예보&초단기예보)
    fun date_Change():String{
        val cal = Calendar.getInstance()
        cal.time = Date()
        val day: DateFormat = SimpleDateFormat("yyyyMMdd")
        cal.add(Calendar.DATE, -1)
        return day.format(cal.time)
    }

    //시간 변경하는 함수 (동네예보)
    fun day_time_Change(now_timeHH:String):String{

        var result_time:String = ""
        //0200 0500 0800 1100 1400 1700 2000 2300
        if(now_timeHH == "02" || now_timeHH == "03" || now_timeHH == "04"){
            result_time = "0200"
        } else if(now_timeHH == "05" || now_timeHH == "06" || now_timeHH == "07"){
            result_time = "0500"
        } else if (now_timeHH == "08" || now_timeHH == "09" || now_timeHH == "10") {
            result_time = "0800"
        } else if (now_timeHH == "11" || now_timeHH == "12" || now_timeHH == "13") {
            result_time = "1100"
        } else if (now_timeHH == "14" || now_timeHH == "15" || now_timeHH == "16") {
            result_time = "1400"
        } else if (now_timeHH == "17" || now_timeHH == "18" || now_timeHH == "19") {
            result_time = "1700"
        } else if (now_timeHH == "20" || now_timeHH == "21" || now_timeHH == "22") {
            result_time = "2000"
        } else if (now_timeHH == "23" || now_timeHH == "00" || now_timeHH == "01") {
            result_time = "2300"
        }
        return result_time
    }

    //1시간전으로 되돌리는 함수 (초단기예보)
//    fun time_time_Change(now_timeHH:String):String{
//        var time_date = ""
//        if(now_timeHH == "00") {time_date = "2330"}
//        else if (now_timeHH == "01") {time_date = "0030"}
//        else if (now_timeHH == "02") {time_date = "0130"}
//        else if (now_timeHH == "03") {time_date = "0230"}
//        else if (now_timeHH == "04") {time_date = "0330"}
//        else if (now_timeHH == "05") {time_date = "0430"}
//        else if (now_timeHH == "06") {time_date = "0530"}
//        else if (now_timeHH == "07") {time_date = "0630"}
//        else if (now_timeHH == "08") {time_date = "0730"}
//        else if (now_timeHH == "09") {time_date = "0830"}
//        else if (now_timeHH == "10") {time_date = "0930"}
//        else if (now_timeHH == "11") {time_date = "1030"}
//        else if (now_timeHH == "12") {time_date = "1130"}
//        else if (now_timeHH == "13") {time_date = "1230"}
//        else if (now_timeHH == "14") {time_date = "1330"}
//        else if (now_timeHH == "15") {time_date = "1430"}
//        else if (now_timeHH == "16") {time_date = "1530"}
//        else if (now_timeHH == "17") {time_date = "1630"}
//        else if (now_timeHH == "18") {time_date = "1730"}
//        else if (now_timeHH == "19") {time_date = "1830"}
//        else if (now_timeHH == "20") {time_date = "1930"}
//        else if (now_timeHH == "21") {time_date = "2030"}
//        else if (now_timeHH == "22") {time_date = "2130"}
//        else if (now_timeHH == "23") {time_date = "2230"}
//        return time_date
//    }


}