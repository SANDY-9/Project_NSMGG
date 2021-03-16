package com.example.todayweather.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayweather.helper.CalculationHelper
import java.util.*


/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc 위치정보 관련 모든 처리 뷰모델(지역 이름, 위도, 경도, x,y 등)
 */
class LocationViewModel private constructor(context: Context) : ViewModel() {

    var location : MutableLiveData<Location> = MutableLiveData<Location>()
    var X : MutableLiveData<Int> = MutableLiveData<Int>()
    var Y : MutableLiveData<Int> = MutableLiveData<Int>()

    init {
        X.value = 0
        Y.value = 0
//        location.value = LocationLiveData()
    }
//    fun getLoction() : MutableLiveData<Location>{
//        return LocationLiveData.get()
//    }





}
class LocationLiveData constructor(var context: Context?) : MutableLiveData<Location>() {
    private val locationManager: LocationManager = context?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var realX : Double? = null
    var realY : Double? = null
    var convertX : Int? = null
    var convertY : Int? = null

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
    fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(context, Locale.getDefault()) //주소 구하기 객체
        val address : String = geocoder.getFromLocation(lat, lng, 1)[0].getAddressLine(0) // 현재 주소
        Log.e("[address]", address)
        // 시 구 동으로 나눔
//        si = address.split(" ")[1]
//        gu = address.split(" ")[2]
//        dong = address.split(" ")[3]
        return address
    }
    private val listener: LocationListener = object : LocationListener {

        override fun onLocationChanged(location: Location) {
            realX = location.latitude
            realY = location.longitude
            convertX = CalculationHelper.convertGRID_X(realX!!, realY!!)
            convertY = CalculationHelper.convertGRID_Y(realX!!, realY!!)
            Log.d("[test_gps_viewmodel]", "x = $convertX, y = $convertY , location = $location")

            val address = getAddress(realX!!,realY!!)
            Log.d("[test_gps_viewmodel]", "$address")
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

