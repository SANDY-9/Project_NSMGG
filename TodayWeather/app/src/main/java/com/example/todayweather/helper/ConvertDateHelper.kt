package com.example.todayweather.helper

import android.content.Context
import android.location.LocationManager
import com.example.todayweather.ui.main.StartActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-28
 * @desc 현재 시간, 날짜 등 변환
 */

object ConvertDateHelper {

    //날짜형식 1 : OOOO년 OO월 OO일 O요일 오전/오후 OO시 OO분
    fun dateFormToday() : String {

        val simpleDateFormat1 : SimpleDateFormat = SimpleDateFormat("yyyy년 M월 d일")
        val simpleDateFormat2 : SimpleDateFormat = SimpleDateFormat("aa HH시 mm분")
        val date : Date = Date()
        val weekDay = arrayOf("일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일")
        val cal : Calendar = Calendar.getInstance()

        val num : Int = cal.get(Calendar.DAY_OF_WEEK)-1
        val today = weekDay[num]
        val day = simpleDateFormat1.format(date)
        val time = simpleDateFormat2.format(date)

        return day+" "+today+" "+time
    }

    //날짜형식 2 : OO월 OO일 O요일-> for문 출력시 다음날짜 계속 출력, number=0 ->오늘
    fun dateFormWeek(number : Int) : String {
        val simpleDateFormat : SimpleDateFormat = SimpleDateFormat("M월 d일")
        val weekDay = arrayOf("일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일")
        var cal : Calendar = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_WEEK, number)

        var num : Int = cal.get(Calendar.DAY_OF_WEEK)+(number-1)
        if(num >= 7) {
            num = (num-7)%7
        }
        val week = weekDay[num]
        val day = simpleDateFormat.format(cal.time)

        var result = ""
        if(number == 0) {
            result = day+" "+week+"(오늘)"
        } else {
            result = day+" "+week
        }
        return result
    }

    //시간 형식 "2000" -> "오후 8시"로 변환
    fun dateFormTime(time : String) : String {
        var result = time.removeRange(2, 4)  // "XXXX"형식에서 앞의 XX 두자리만 남기기
        val time_into = result.toInt()
        if(time_into > 12) {
            result = "오후 ${time_into-12}시"
        } else {
            result = "오전 ${time_into}시"
        }
        return result
    }

//    fun dateFormLocation() : String{
//        val result = StartActivity().registerLocationUpdates()
//        return if (result!="NONE GPS" && result!="NONE PERMISSION"){
//            result
//        }else{
//            "위치 서비스나 데이터를 켜주세요."
//        }
//    }
}
