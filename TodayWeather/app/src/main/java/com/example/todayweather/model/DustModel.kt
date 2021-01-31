package com.example.todayweather.model

import com.google.gson.annotations.SerializedName

/**
 * @author byel
 * @email ehdzldxn3@naver.com
 * @created 2021-01-28
 * @desc
 */
class DustModel {
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
        @SerializedName("pm10Grade1h") var pm10Grade1h: String, // 미세먼지 점수
        @SerializedName("pm10Flag") var pm10Flag: String, //
        @SerializedName("pm25Value") var pm25Value: String, // 초미세먼지 농도
        @SerializedName("pm25Grade1h") var pm25Grade1h: String, // 초미세먼지 점수
        @SerializedName("pm25Flag") var pm25Flag: String, //
)


