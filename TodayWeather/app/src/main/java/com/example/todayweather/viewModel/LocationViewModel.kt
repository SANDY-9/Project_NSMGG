package com.example.todayweather.viewModel

import android.content.Context
import android.location.Address
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc GPS 라이브데이터 관리
 */
class LocationViewModel() : ViewModel() {

    var lat : Double = 0.0
    var lng : Double = 0.0
    var address : Address? = null

    constructor(lat:Double, lng:Double, address : Address) : this() {
        this.lat = lat
        this.lng = lng
        this.address = address
    }

    fun onGPS() {

    }



}