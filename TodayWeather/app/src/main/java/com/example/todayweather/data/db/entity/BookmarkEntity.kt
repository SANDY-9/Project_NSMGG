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

// https://youngest-programming.tistory.com/179 room에 대한 자세한 링크

const val BOOKMARK_ID = 0
// 이거는 즐겨찾기를 누르면 room db에 저장하게 하는 클래스 만들기

// Entity: Room으로 작업할 때 데이터베이스 테이블을 묘사하는 annotated 클래스이다.
@Entity(tableName = "my_Bookmark", primaryKeys = arrayOf("region"))
data class BookmarkTable (
        // SQLite의 테이블 이름은 대소문자를 구분하지 않습니다.
        val region : String,

        //column 이름을 다르게 하고 싶다면 @ColumnInfo 어노테이션을 추가하면 된다.
        @ColumnInfo(name = "x")
        val nx : Int,

        @ColumnInfo(name = "y")
        val ny : Int,

        @ColumnInfo(name = "code_temp")
        var codeTemp : String,

        @ColumnInfo(name = "code_local")
        var codeLocal : String,

        @ColumnInfo(name = "num_local")
        var num_local : String,
)