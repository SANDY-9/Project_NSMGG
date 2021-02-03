package com.example.todayweather.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc 북마크 정보 저장(필요한 정보 : 주소, nx, ny, 미세먼지 측정소, 육상예보구역 코드(주간날씨 최저최고기온찾기), 예보지역행정코드(주간날씨 강수확률, 날씨찾기))
 */

const val BOOKMARK_ID = 0

@Entity(tableName = "my_Bookmark")
data class BookmarkTable (
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "BOOKMARK_ID")
        var BOOKMARK_ID : Int,
        @PrimaryKey
        @ColumnInfo(name = "region")    //북마크 name
        val region : String,
        @ColumnInfo(name = "x")
        val nx : Int,
        @ColumnInfo(name = "y")
        val ny : Int,
        @ColumnInfo(name = "dust")
        var dustAddr : String,
        @ColumnInfo(name = "code_WeekTemp")
        var codeTemp : String,
        @ColumnInfo(name = "code_WeekWeather")
        var codeRain : String,
)