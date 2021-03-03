package com.example.todayweather.viewModel

import android.content.Context
import android.location.Address
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc 위치정보 관련 모든 처리 뷰모델(지역 이름, 위도, 경도, x,y 등)
 */
class LocationViewModel : ViewModel() {

    var location : MutableLiveData<Location> = MutableLiveData<Location>()
    var X : MutableLiveData<Int> = MutableLiveData<Int>()
    var Y : MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        X.value = 0
        Y.value = 0
    }






}