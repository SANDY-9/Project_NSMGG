package com.example.todayweather.data.db.database.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
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
                      nx : Int, ny:Int, codeTemp:String,
                      codeLocal:String, num_local:String) : Int {
        var result = 0
        // db 확인하는 코드
        CoroutineScope(Dispatchers.Main).launch {
            var output = WeatherDB.bookMarkerInterface().getAll()
            Log.d("db_test", "$output")

            result = WeatherDB.bookMarkerInterface().getReg(region)
            if (result==0){
                val input = BookmarkTable(region, nx, ny, codeTemp, codeLocal, num_local)
                WeatherDB.bookMarkerInterface().insert(input)
            } else {
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
    fun FindBookMarker(WeatherDB: NSMGGDatabase, context: Context) : List<String> {
        var result :MutableList<String> = arrayListOf<String>()
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            val find = WeatherDB.bookMarkerInterface().getAll()
            if (find.isEmpty()){
                Toast.makeText(context,"즐겨찾기 해놓은 목록이 없습니다.", Toast.LENGTH_SHORT).show()
                return@launch
            }
            for (i in find.indices){
                result[i] = find[i].region
            }
        }
        return result
    }

    //즐겨찾기 지역 이름으로 x 가져오기 : return Int
    fun XBookMarker(WeatherDB: NSMGGDatabase, region: String) : Int {
        var find : Int = 0
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            find = WeatherDB.bookMarkerInterface().getX(region)
        }
        return find
    }

    //즐겨찾기 지역 이름으로 y 가져오기 : return Int
    fun YBookMarker(WeatherDB: NSMGGDatabase, region: String) : Int {
        var find : Int = 0
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            find = WeatherDB.bookMarkerInterface().getY(region)
        }
        return find
    }

    //즐겨찾기 지역 이름으로 기온코드 가져오기 : return String(주간예보 최저/최고기온 조회)
    fun TempBookMarker(WeatherDB: NSMGGDatabase, region: String) : String {
        var find : String = ""
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            find = WeatherDB.bookMarkerInterface().getTemp(region)
        }
        return find
    }

    //즐겨찾기 지역 이름으로 행정구역코드 가져오기 : return String(주간예보조 강수/날씨 조회)
    fun CodeLcoalBookMarker(WeatherDB: NSMGGDatabase, region: String) : String {
        var find : String = ""
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            find = WeatherDB.bookMarkerInterface().getCodeLocal(region)
        }
        return find
    }
    //즐겨찾기 지역 이름으로 지점코드 가져오기 : return String
    fun NumLocalBookMarker(WeatherDB: NSMGGDatabase, region: String) : String {
        var find : String = ""
        CoroutineScope(Dispatchers.Main).launch {
            //항목 찾기
            find = WeatherDB.bookMarkerInterface().getNumLocal(region)
        }
        return find
    }
}