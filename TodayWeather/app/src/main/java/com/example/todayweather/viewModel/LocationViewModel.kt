package com.example.todayweather.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todayweather.helper.CalculationHelper
import com.example.todayweather.ui.main.StartActivity
import com.example.todayweather.ui.main.StartActivity.Companion.convertX
import com.example.todayweather.ui.main.StartActivity.Companion.convertY
import com.example.todayweather.ui.main.StartActivity.Companion.dong_object
import com.example.todayweather.ui.main.StartActivity.Companion.gu_object
import com.example.todayweather.ui.main.StartActivity.Companion.realX
import com.example.todayweather.ui.main.StartActivity.Companion.realY
import com.example.todayweather.ui.main.StartActivity.Companion.si_object
import java.security.acl.Owner
import java.util.*


/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc 위치정보 관련 모든 처리 뷰모델(지역 이름, 위도, 경도, x,y 등)
 */
class LocationViewModel() : ViewModel() {

    val location : MutableLiveData<Location> = MutableLiveData<Location>()
    val address : MutableLiveData<String> = MutableLiveData<String>()
    val X : MutableLiveData<Int> = MutableLiveData<Int>()
    val Y : MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        X.value = 0
        Y.value = 0
        address.value = "${si_object} ${gu_object} ${dong_object}"
    }
    fun setLocation(location: Location) {
        this.location.value = location
        X.value = CalculationHelper.convertGRID_X(location.latitude,location.longitude)
        Y.value = CalculationHelper.convertGRID_Y(location.latitude,location.longitude)
        setAddress(location.latitude,location.longitude)
    }

    fun getLocation() : Location? {
        return location.value
    }

    fun setAddress(latitude: Double, longitude: Double) {
        address.value = StartActivity().getAddress(latitude, longitude)
    }
    fun getAddress() = address.value

}
class LocationLiveData constructor(var context: Context?) : MutableLiveData<Location>() {
    private val locationManager: LocationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    companion object {
        private var sInstance: LocationLiveData? = null

        @MainThread
        operator fun get(context: Context?): LocationLiveData? {
            if (sInstance == null) {
                sInstance = LocationLiveData(context?.applicationContext)
            }
            return sInstance
        }
    }

    private val listener: LocationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            LocationViewModel().setLocation(location)
            realX = location.latitude
            realY = location.longitude
            LocationViewModel().address.value = StartActivity().getAddress(realX!!, realY!!)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,1f,listener)
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1f,listener)
    }

    override fun onInactive() {
        locationManager.removeUpdates(listener)
    }
}

