package com.example.todayweather.model.response


import com.google.gson.annotations.SerializedName

data class TodayWeatherTimeResponse(
    @SerializedName("response")
    val response: Response
) {
    data class Response(
        @SerializedName("header")
        val header: Header,
        @SerializedName("body")
        val body: Body
    ) {
        data class Header(
            @SerializedName("resultCode")
            val resultCode: String,
            @SerializedName("resultMsg")
            val resultMsg: String
        )

        data class Body(
            @SerializedName("dataType")
            val dataType: String,
            @SerializedName("items")
            val items: Items,
            @SerializedName("pageNo")
            val pageNo: Int,
            @SerializedName("numOfRows")
            val numOfRows: Int,
            @SerializedName("totalCount")
            val totalCount: Int
        ) {
            data class Items(
                @SerializedName("item")
                val item: List<Item>
            ) {
                data class Item(
                    @SerializedName("baseDate")
                    val baseDate: String,
                    @SerializedName("baseTime")
                    val baseTime: String,
                    @SerializedName("category")
                    val category: String,
                    @SerializedName("nx")
                    val nx: Int,
                    @SerializedName("ny")
                    val ny: Int,
                    @SerializedName("obsrValue")
                    val obsrValue: String
                )
            }
        }
    }
}