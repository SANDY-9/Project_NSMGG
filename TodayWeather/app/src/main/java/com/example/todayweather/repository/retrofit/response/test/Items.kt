package com.example.todayweather.repository.retrofit.response.test


import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("item")
    val item: List<Item>
)