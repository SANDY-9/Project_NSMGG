package com.example.todayweather.data.network.response


import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
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
                    var baseDate: String,
                    @SerializedName("baseTime")
                    var baseTime: String,
                    @SerializedName("category")
                    var category: String,
                    @SerializedName("nx")
                    var nx: Int,
                    @SerializedName("ny")
                    var ny: Int,
                    @SerializedName("obsrValue")
                    var obsrValue: String
                )
            }
        }
    }
}
