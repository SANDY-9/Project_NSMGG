package com.example.todayweather.data.model

import android.location.Address

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-04
 * @desc 위치 관련 처리.
 */

data class Location(
        var lat : Double = 0.0,
        var lng : Double = 0.0,
        var address : Address? = null
)