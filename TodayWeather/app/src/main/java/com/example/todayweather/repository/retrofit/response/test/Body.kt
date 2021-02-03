package com.example.todayweather.repository.retrofit.response.test


import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("dataType")
    val dataType: String, // JSON
    @SerializedName("items")
    val items: Items,
    @SerializedName("pageNo")
    val pageNo: Int, // 1
    @SerializedName("numOfRows")
    val numOfRows: Int, // 10
    @SerializedName("totalCount")
    val totalCount: Int // 8
)