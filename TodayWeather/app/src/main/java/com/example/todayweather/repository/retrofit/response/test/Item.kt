package com.example.todayweather.repository.retrofit.response.test


import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("baseDate")
    val baseDate: String, // 20210203
    @SerializedName("baseTime")
    val baseTime: String, // 1900
    @SerializedName("category")
    val category: String, // PTY
    @SerializedName("nx")
    val nx: Int, // 61
    @SerializedName("ny")
    val ny: Int, // 127
    @SerializedName("obsrValue")
    val obsrValue: String // 3
)