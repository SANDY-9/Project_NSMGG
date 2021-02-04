package com.example.todayweather.viewModel

import android.location.Address
import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc 위치정보 관련 라이브데이터 관리
 */
class LocationViewModel() : ViewModel() {

    val lat : MutableLiveData<Double> = MutableLiveData<Double>()
    val lng : MutableLiveData<Double> = MutableLiveData<Double>()
    val address : MutableLiveData<Address> = MutableLiveData<Address>()
    val X : MutableLiveData<Int> = MutableLiveData<Int>()
    val Y : MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        lat.value = 0.0
        lng.value = 0.0
        address.value = null
        X.value = 0
        Y.value = 0
    }



}