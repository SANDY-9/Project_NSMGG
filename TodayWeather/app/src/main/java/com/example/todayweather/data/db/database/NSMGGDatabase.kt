package com.example.todayweather.data.db.database

import android.content.Context
import android.util.Log
import androidx.room.*
import com.example.todayweather.data.db.entity.BookmarkTable
import com.example.todayweather.data.network.CityWeatherTable
import com.example.todayweather.data.network.NationalWeatherTable
import com.example.todayweather.ui.main.SplashActivity

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-04
 * @desc 우리 앱에서 사용할 데이터베이스 만들기
 */

// splash 있던 room 관련 코드를 여기에 옮기기
@Database(entities = [NationalWeatherTable::class, CityWeatherTable::class, BookmarkTable::class], version = 1, exportSchema = false)
abstract class NSMGGDatabase : RoomDatabase() {
    abstract fun nationalWeatherInterface(): NationalWeatherInterface
    abstract fun cityWeatherInterface(): CityWeatherInterface
    //  인터페이스 만들기 abstract fun bookMarkerInterface(): BookMarkerInterface

    companion object {
        private var INSTANCE: NSMGGDatabase? = null

        fun getInstance(context: Context): NSMGGDatabase? {
            if (INSTANCE == null) {
                synchronized(NSMGGDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NSMGGDatabase::class.java, "weatherDB").build()
                }
            } else {
                Log.d("[test_instance]", "인스턴스 null 아님")
            }
            return INSTANCE
        }
    }
}

@Dao
interface NationalWeatherInterface { // 동네예보
    // db 전부 호출
    @Query("SELECT * FROM dongnae")
    suspend fun getAll(): List<NationalWeatherTable>

    // 동 이름으로 데이터 찾기
    @Query("SELECT * FROM dongnae WHERE name3 LIKE :dong")
    suspend fun getChoice(dong : String): List<NationalWeatherTable>

    // nx, ny 값으로 데이터 가져오기
    @Query("SELECT * FROM dongnae WHERE x = :x AND y = :y")
    suspend fun getXY(x : Int, y : Int): List<NationalWeatherTable>

    @Insert
    suspend fun insert(nationalWeatherTable: NationalWeatherTable)

    @Query("DELETE FROM dongnae")
    suspend fun deleteAll()
}

@Dao
interface CityWeatherInterface { // 중기예보
    @Query("SELECT * FROM weekly")
    suspend fun getAll(): List<CityWeatherTable>

    // 도시, 지역 이름으로 코드 찾기
    @Query("SELECT weeklyCode FROM weekly WHERE region LIKE :region1 AND city LIKE :city1")
    suspend fun getRegId( region1: String, city1: String): String

    // 지역 이름으로 코드 찾기
    @Query("SELECT weeklyCode FROM weekly WHERE region LIKE :region1")
    suspend fun getRegIdRegion( region1: String): String

    // db insert
    @Insert
    suspend fun insert(cityWeatherTable: CityWeatherTable)

    // delete db
    @Query("DELETE FROM weekly")
    suspend fun deleteAll()
}

@Dao
interface BookMarkerInterface { // 즐겨찾기
    @Query("SELECT * FROM my_Bookmark")
    suspend fun getAll(): List<BookmarkTable>

//    불러오고 싶은 함수 추후 작성
//    // 도시, 지역 이름으로 코드 찾기
//    @Query("SELECT * FROM my_Bookmark WHERE region LIKE :region1 AND city LIKE :city1")
//    suspend fun getRegId( region1: String, city1: String): String
//
//    // 지역 이름으로 코드 찾기
//    @Query("SELECT * FROM my_Bookmark WHERE region LIKE :region1")
//    suspend fun getRegIdRegion( region1: String): String

    // db insert
    @Insert
    suspend fun insert(bookmarkTable: BookmarkTable)

    // delete db
    @Query("DELETE FROM my_Bookmark")
    suspend fun deleteAll()
}