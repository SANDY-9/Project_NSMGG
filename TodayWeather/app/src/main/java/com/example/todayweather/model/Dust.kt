package com.example.todayweather.model

import com.google.gson.annotations.SerializedName

/**
 * @author byel
 * @email ehdzldxn3@naver.com
 * @created 2021-01-28
 * @desc
 */
class Dust {
}

data class ResponseDust (
    @SerializedName("response")var response : BodyDust
)

data class BodyDust (
        @SerializedName("body")var body : ItemsDust
)

data class ItemsDust (
        @SerializedName("items")var items : List<DustDTO>
)

data class DustDTO (
        @SerializedName("pm10Value") var pm10Value: String, // 미세먼지 농도
        @SerializedName("pm10Grade") var pm10Grade: String, // 미세먼지 점수
        @SerializedName("pm10Value") var pm25Value: String, // 초미세먼지 농도
        @SerializedName("pm10Grade") var pm25Grade: String, // 초미세먼지 점수
)
