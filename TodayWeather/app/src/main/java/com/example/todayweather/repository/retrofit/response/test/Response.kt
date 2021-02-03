package com.example.todayweather.repository.retrofit.response.test


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("header")
    val header: Header,
    @SerializedName("body")
    val body: Body
)