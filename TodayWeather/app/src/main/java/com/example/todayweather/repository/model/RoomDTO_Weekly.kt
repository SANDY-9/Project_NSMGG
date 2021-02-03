package com.example.todayweather.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-28
 * @desc
 */

// 내장 db data class
@Entity(tableName = "weekly")
data class CityWeatherTable(
    val region: String,
    val city: String,
    @PrimaryKey val weeklyCode: String
)

// retrofit을 사용한 api 통신 data class(주간 최저.고 온도)
data class ResponseDTO (
    @SerializedName("response")var response : BodyDTO
)
data class BodyDTO (
    @SerializedName("body")var body : ItemsDTO
)
data class ItemsDTO (
    @SerializedName("items")var items : ItemDTO
)
data class ItemDTO (
    @SerializedName("item")var item : List<TempDTO>
)
data class TempDTO (

//  @SerializedName 어노테이션을 사용하여 괄호 안에 Json 응답과 동일하게 써주고, 변수명을 다르게 지정해주시면 됩니다.
//  @SerializedName("taMin3")
//  var morning3:int

    // x일 후 최저,최고 온도
    // ex) -20 ~ 40
//    var response : String,
    @SerializedName("taMin3") var taMin3: Int,
    @SerializedName("taMax3") var taMax3: Int,
    @SerializedName("taMin4") var taMin4: Int,
    @SerializedName("taMax4") var taMax4: Int,
    @SerializedName("taMin5") var taMin5: Int,
    @SerializedName("taMax5") var taMax5: Int,
    @SerializedName("taMin6") var taMin6: Int,
    @SerializedName("taMax6") var taMax6: Int,
    @SerializedName("taMin7") var taMin7: Int,
    @SerializedName("taMax7") var taMax7: Int,
    @SerializedName("taMin8") var taMin8: Int,
    @SerializedName("taMax8") var taMax8: Int,
    @SerializedName("taMin9") var taMin9: Int,
    @SerializedName("taMax9") var taMax9: Int,
    @SerializedName("taMin10") var taMin10: Int,
    @SerializedName("taMax10") var taMax10: Int
)

// retrofit을 사용한 api 통신 data class(하늘상태, 강수확률)
data class ResponseDTO2 (
    @SerializedName("response")var response : BodyDTO2
)
data class BodyDTO2 (
    @SerializedName("body")var body : ItemsDTO2
)
data class ItemsDTO2 (
    @SerializedName("items")var items : ItemDTO2
)
data class ItemDTO2 (
    @SerializedName("item")var item : List<SkyDTO>
)
data class SkyDTO (
    // 강수확률
    // ex) 0~100(%)
    @SerializedName("rnSt3Am") var rnSt3Am: Int,
    @SerializedName("rnSt3Pm") var rnSt3Pm: Int,
    @SerializedName("rnSt4Am") var rnSt4Am: Int,
    @SerializedName("rnSt4Pm") var rnSt4Pm: Int,
    @SerializedName("rnSt5Am") var rnSt5Am: Int,
    @SerializedName("rnSt5Pm") var rnSt5Pm: Int,
    @SerializedName("rnSt6Am") var rnSt6Am: Int,
    @SerializedName("rnSt6Pm") var rnSt6Pm: Int,
    @SerializedName("rnSt7Am") var rnSt7Am: Int,
    @SerializedName("rnSt7Pm") var rnSt7Pm: Int,

    // 하늘 상태
    /*
    ex) 맑음, 구름많음, 흐림
    - 구름많고 비, 구름많고 눈, 구름많고 비/눈, 구름많고 소나기
    - 흐리고 비, 흐리고 눈, 흐리고 비/눈, 흐리고 소나기
    */
    @SerializedName("wf3Am") var wf3Am: String,
    @SerializedName("wf3Pm") var wf3Pm: String,
    @SerializedName("wf4Am") var wf4Am: String,
    @SerializedName("wf4Pm") var wf4Pm: String,
    @SerializedName("wf5Am") var wf5Am: String,
    @SerializedName("wf5Pm") var wf5Pm: String,
    @SerializedName("wf6Am") var wf6Am: String,
    @SerializedName("wf6Pm") var wf6Pm: String,
    @SerializedName("wf7Am") var wf7Am: String,
    @SerializedName("wf7Pm") var wf7Pm: String
)