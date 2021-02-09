package com.example.todayweather.data.db.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todayweather.data.db.entity.BookmarkTable
import com.example.todayweather.data.network.temporary.CityWeatherTable
import com.example.todayweather.data.network.temporary.NationalWeatherTable

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-09
 * @desc SQL문 매핑
 */
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
    @Query("SELECT weeklyCode FROM weekly WHERE region LIKE :region AND city LIKE :city")
    suspend fun getRegId( region: String, city: String): String

    // 지역 이름으로 코드 찾기
    @Query("SELECT weeklyCode FROM weekly WHERE region LIKE :region")
    suspend fun getRegIdRegion( region: String): String

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

    //    불러오고 싶은 함수 추후
    // 지역 이름으로 db유무 확인
    @Query("SELECT COUNT(*) FROM my_Bookmark WHERE region = :region")
    suspend fun getReg( region: String): Int

    // db insert
    @Insert
    suspend fun insert(bookmarkTable: BookmarkTable)

    // delete db
    @Query("DELETE FROM my_Bookmark")
    suspend fun deleteAll()

    // delete db select one
    @Query("DELETE FROM my_Bookmark Where region like :region")
    suspend fun deleteOne(region: String)
}
