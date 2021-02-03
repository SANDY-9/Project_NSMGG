package com.example.todayweather.repository.retrofit.response


import com.google.gson.annotations.SerializedName

data class DustStationResponse(
    @SerializedName("response")
    val response: Response
) {
    data class Response(
        @SerializedName("body")
        val body: Body,
        @SerializedName("header")
        val header: Header
    ) {
        data class Body(
            @SerializedName("totalCount")
            val totalCount: Int,
            @SerializedName("items")
            val items: List<Item>,
            @SerializedName("pageNo")
            val pageNo: Int,
            @SerializedName("numOfRows")
            val numOfRows: Int
        ) {
            data class Item(
                @SerializedName("pm25Grade1h")
                val pm25Grade1h: String,
                @SerializedName("pm10Value24")
                val pm10Value24: String,
                @SerializedName("so2Value")
                val so2Value: String,
                @SerializedName("pm10Grade1h")
                val pm10Grade1h: String,
                @SerializedName("o3Grade")
                val o3Grade: String,
                @SerializedName("pm10Value")
                val pm10Value: String,
                @SerializedName("pm25Flag")
                val pm25Flag: Any,
                @SerializedName("khaiGrade")
                val khaiGrade: String,
                @SerializedName("pm25Value")
                val pm25Value: String,
                @SerializedName("no2Flag")
                val no2Flag: Any,
                @SerializedName("mangName")
                val mangName: String,
                @SerializedName("no2Value")
                val no2Value: String,
                @SerializedName("so2Grade")
                val so2Grade: String,
                @SerializedName("coFlag")
                val coFlag: Any,
                @SerializedName("khaiValue")
                val khaiValue: String,
                @SerializedName("coValue")
                val coValue: String,
                @SerializedName("pm10Flag")
                val pm10Flag: Any,
                @SerializedName("no2Grade")
                val no2Grade: String,
                @SerializedName("pm25Value24")
                val pm25Value24: String,
                @SerializedName("o3Flag")
                val o3Flag: Any,
                @SerializedName("pm25Grade")
                val pm25Grade: String,
                @SerializedName("so2Flag")
                val so2Flag: Any,
                @SerializedName("coGrade")
                val coGrade: String,
                @SerializedName("dataTime")
                val dataTime: String,
                @SerializedName("pm10Grade")
                val pm10Grade: String,
                @SerializedName("o3Value")
                val o3Value: String
            )
        }

        data class Header(
            @SerializedName("resultMsg")
            val resultMsg: String,
            @SerializedName("resultCode")
            val resultCode: String
        )
    }
}