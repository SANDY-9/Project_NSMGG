package com.example.todayweather.repository.retrofit.response.test


import com.google.gson.annotations.SerializedName

data class Header(
    @SerializedName("resultCode")
    val resultCode: String, // 00
    @SerializedName("resultMsg")
    val resultMsg: String // NORMAL_SERVICE
)