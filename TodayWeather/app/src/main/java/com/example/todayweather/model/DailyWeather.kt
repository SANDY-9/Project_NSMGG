package com.example.todayweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-23
 * @desc
 */

data class DailyWeather(var temperature : String)

// 내장 db data class
@Entity(tableName = "dongnae")
data class NationalWeatherTable(
    @PrimaryKey val code: Long,
    val name1: String,
    val name2: String,
    val name3: String,
    val x: Int,
    val y: Int
)

// retrofit을 사용한 api 통신 data class(동네예보)
data class ResponseDTONow (
    @SerializedName("response")var response : BodyDTONow
)
data class BodyDTONow (
    @SerializedName("body")var body : ItemsDTONow
)
data class ItemsDTONow (
    @SerializedName("items")var items : ItemDTDNow
)
data class ItemDTDNow (
    @SerializedName("item")var item : List<NowDTO>
)
data class NowDTO (
//  @SerializedName 어노테이션을 사용하여 괄호 안에 Json 응답과 동일하게 써주고, 변수명을 다르게 지정해주시면 됩니다.
    @SerializedName("baseDate") var baseDate: String,
    @SerializedName("baseTime") var baseTime: String,
    @SerializedName("category") var category: String,
    @SerializedName("nx") var nx: Int,
    @SerializedName("ny") var ny: Int,
    @SerializedName("obsrValue") var obsrValue: String
)