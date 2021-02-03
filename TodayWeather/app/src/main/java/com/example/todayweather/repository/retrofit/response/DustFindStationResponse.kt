package com.example.todayweather.repository.retrofit.response


import com.google.gson.annotations.SerializedName

data class DustFindStationResponse(
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
            val totalCount: Int, // 2
            @SerializedName("items")
            val items: List<Item>,
            @SerializedName("pageNo")
            val pageNo: Int, // 1
            @SerializedName("numOfRows")
            val numOfRows: Int // 1
        ) {
            data class Item(
                @SerializedName("dmX")
                val dmX: String, // 37.542036
                @SerializedName("item")
                val item: String, // SO2, CO, O3, NO2, PM10, PM2.5
                @SerializedName("mangName")
                val mangName: String, // 도시대기
                @SerializedName("year")
                val year: String, // 1982
                @SerializedName("addr")
                val addr: String, // 서울 성동구 뚝섬로3길 18성수1가1동주민센터
                @SerializedName("stationName")
                val stationName: String, // 성동구
                @SerializedName("dmY")
                val dmY: String // 127.049685
            )
        }

        data class Header(
            @SerializedName("resultMsg")
            val resultMsg: String, // NORMAL_CODE
            @SerializedName("resultCode")
            val resultCode: String // 00
        )
    }
}