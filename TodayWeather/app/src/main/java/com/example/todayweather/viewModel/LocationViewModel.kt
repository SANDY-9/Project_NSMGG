package com.example.todayweather.viewModel

import android.content.Context
import android.location.Address
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayweather.data.model.Location

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc GPS 라이브데이터 관리
 */
class LocationViewModel() : ViewModel() {

    val location : MutableLiveData<Location> = MutableLiveData<Location>()

}