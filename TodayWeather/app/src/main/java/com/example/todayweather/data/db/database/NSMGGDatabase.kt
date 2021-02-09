package com.example.todayweather.data.db.database

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.util.Log
import androidx.room.*
import com.example.todayweather.data.db.entity.BookmarkTable
import com.example.todayweather.data.network.temporary.CityWeatherTable
import com.example.todayweather.data.network.temporary.NationalWeatherTable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-04
 * @desc 우리 앱에서 사용할 데이터베이스 만들기
 */

// splash 있던 room 관련 코드를 여기에 옮기기
@Database(entities = [NationalWeatherTable::class, CityWeatherTable::class, BookmarkTable::class], version = 3, exportSchema = false)
abstract class NSMGGDatabase : RoomDatabase() {
    abstract fun nationalWeatherInterface(): NationalWeatherInterface
    abstract fun cityWeatherInterface(): CityWeatherInterface
    abstract fun bookMarkerInterface(): BookMarkerInterface
    companion object {
        private var INSTANCE: NSMGGDatabase? = null

        fun getInstance(context: Context): NSMGGDatabase? {
            if (INSTANCE == null) {
                synchronized(NSMGGDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NSMGGDatabase::class.java, "weatherDB")
                            .build()
                }
            } else {
                Log.d("[test_instance]", "인스턴스 null 아님")
            }
            return INSTANCE
        }
    }
}


// 기상청 제공 데이터들은 최초 실행시 한 번만 저장하게 함
// reason : (primary key이기에 접속시 중복 저장시 runtime error)
fun SharedPref(context: Context, WeatherDB : NSMGGDatabase) {
    val pref : SharedPreferences = context.getSharedPreferences("isDB", Activity.MODE_PRIVATE)
    val download : Boolean = pref.getBoolean("isDB",false)
    if (!download){
        Log.d("Is first Time?", "firstD")

        val editer : SharedPreferences.Editor = pref.edit()
        editer.putBoolean("isDB",true)
        editer.apply()
        editer.commit()
        DongnaeReadTxt(context, WeatherDB)
    }else{
        Log.d("Is first Time?", "not firstD");
    }
}

private fun DongnaeReadTxt(context: Context, WeatherDB: NSMGGDatabase) {

    //val input = NationalWeatherTable(1114062500,"seoul", "jongrogu", "dasandong", 60, 126) // 임의로 써서 넣은 함수

    val assetManager: AssetManager = context.resources.assets
    val inputStream: InputStream = assetManager.open("dongnae.txt")

    inputStream.bufferedReader().readLines().forEach {
        var token = it.split("\t")
        var input = NationalWeatherTable(token[0].toLong(), token[1], token[2], token[3], token[4].toInt(), token[5].toInt())
        CoroutineScope(Dispatchers.Main).launch {
            WeatherDB.nationalWeatherInterface().insert(input)
        }
//             Log.d("file_test", token.toString())
    }
// 여기까지 db생성 코드

    // db 확인하는 코드
    CoroutineScope(Dispatchers.Main).launch {
        //NationalWeatherDB.nationalWeatherInterface().deleteAll()
        //NationalWeatherDB.nationalWeatherInterface().insert(input)
        var output = WeatherDB.nationalWeatherInterface().getAll()
        Log.d("db_test", "$output")
    }
    WeeklyReadTxt(context, WeatherDB)
}
private fun WeeklyReadTxt(context: Context, WeatherDB: NSMGGDatabase) {

    val assetManager: AssetManager = context.resources.assets
    val inputStream: InputStream = assetManager.open("weekly.txt")

    inputStream.bufferedReader().readLines().forEach {
        val token = it.split("\t")
        val input = CityWeatherTable(token[0], token[1], token[2])
        CoroutineScope(Dispatchers.Main).launch {
            WeatherDB.cityWeatherInterface().insert(input)
        }
//            Log.d("file_test", token.toString())
    }
// 여기까지 db생성 코드

    CoroutineScope(Dispatchers.Main).launch {
        //cityWeatherDB.cityWeatherInterface().deleteAll()
        //cityWeatherDB.cityWeatherInterface().insert(input)
        var output = WeatherDB.cityWeatherInterface().getAll()

        Log.d("db_test", "$output")
    }
}