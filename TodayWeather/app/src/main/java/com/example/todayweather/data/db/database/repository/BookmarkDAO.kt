package com.example.todayweather.data.db.database.repository

import android.util.Log
import com.example.todayweather.data.db.database.NSMGGDatabase
import com.example.todayweather.data.db.entity.BOOKMARK_ID
import com.example.todayweather.data.db.entity.BookmarkTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-09
 * @desc 북마크 관리 DAO
 */
class BookmarkDAO {

    // 즐겨찾기 추가하기
    fun AddBookMarker(WeatherDB: NSMGGDatabase, region : String,
                      nx : Int, ny:Int, dustation:String,
                      codetemp:String, codeRain:String) : Int {
        var result = 0
        // db 확인하는 코드
        CoroutineScope(Dispatchers.Main).launch {
            var output = WeatherDB.bookMarkerInterface().getAll()
            Log.d("db_test", "$output")

            result = WeatherDB.bookMarkerInterface().getReg(region)
            if (result>0){
                val input = BookmarkTable(BOOKMARK_ID, region, nx, ny, dustation, codetemp, codeRain)
                WeatherDB.bookMarkerInterface().insert(input)
            }else{
                Log.d("db_test", "이미 같은 지역이 즐겨찾기가 되어있음")
            }
        }
        return result
    }

    // 즐겨찾기 항목 삭제하기.
    fun DeleteBookMarker(WeatherDB: NSMGGDatabase, region : String) : Int {
        var result = 0
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            val result = WeatherDB.bookMarkerInterface().getReg(region)
            if (result>0){
                WeatherDB.bookMarkerInterface().deleteOne(region)
                Log.d("db_test", "항목이 삭제.")
            }else{
                Log.d("db_test", "항목이 없습니다.")
            }
        }
        return result
    }

    //즐겨찾기 지역 목록 불러오기 : return List<String>

    //즐겨찾기 지역 이름으로 x 가져오기 : return Int

    //즐겨찾기 지역 이름으로 y 가져오기 : return Int

    //즐겨찾기 지역 이름으로 기온코드 가져오기 : return String(주간예보 최저/최고기온 조회)

    //즐겨찾기 지역 이름으로 행정구역코드 가져오기 : return String(주간예보조 강수/날씨 조회)

    //즐겨찾기 지역 이름으로 지점코드 가져오기 : return String

}