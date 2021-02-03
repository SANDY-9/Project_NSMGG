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
            val totalCount: Int,
            @SerializedName("items")
            val items: List<Item>,
            @SerializedName("pageNo")
            val pageNo: Int,
            @SerializedName("numOfRows")
            val numOfRows: Int
        ) {
            data class Item(
                @SerializedName("dmX")
                val dmX: String,
                @SerializedName("item")
                val item: String,
                @SerializedName("mangName")
                val mangName: String,
                @SerializedName("year")
                val year: String,
                @SerializedName("addr")
                val addr: String,
                @SerializedName("stationName")
                val stationName: String,
                @SerializedName("dmY")
                val dmY: String
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