package com.example.todayweather.data.db.database.repository

import android.util.Log
import com.example.todayweather.data.db.database.NSMGGDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-09
 * @desc 날씨를 요청할 때 필요한 DAO
 */
class WeatherDAO {

    //자동완성을 위한 주소 목록 가져오기
    fun AddressList( WeatherDB: NSMGGDatabase) : List<String> {
        val address : MutableList<String> = arrayListOf<String>()
        CoroutineScope(Dispatchers.Main).launch {
            val output = WeatherDB.nationalWeatherInterface().getAll()
            Log.d("db_test", "${output.size}")
            for (i in output.indices){
                address.add("${output[i].name1} ${output[i].name2} ${output[i].name3}")
            }
            Log.d("db_test", address.toString())
        }
        return address
    }

    //주소 3단계 이름으로 x 가져오기 : return Int
    fun XWeather(WeatherDB: NSMGGDatabase, dong: String) : Int {
        var find : Int = 0
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            find = WeatherDB.nationalWeatherInterface().getX(dong)
        }
        return find
    }
    //주소 3단계 이름으로 y 가져오기 : return Int
    fun YWeather(WeatherDB: NSMGGDatabase, dong: String) : Int {
        var find : Int = 0
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            find = WeatherDB.nationalWeatherInterface().getY(dong)
        }
        return find
    }
    //주소 3단계 이름으로 기온코드 가져오기 : return String(주간예보 최저/최고기온 조회)

    //주소 3단계 이름으로 행정구역코드 가져오기 : return String(주간예보조 강수/날씨 조회)

    //지점코드 가져오기 : : return String

}