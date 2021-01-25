package com.example.todayweather.helper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 기상청 X, Y값 변환, 체감온도 등 변환해야 하는 값 계산
 */

object CalculationHelper {

    //위도, 경도를 기상청 격자 x값으로 바꿔주는 함수 (lat_X : 위도, lng_Y : 경도)
    fun convertGRID_X(lat_X: Double, lng_Y: Double) : Int {

        //불변 값
        val RE = 6371.00877 // 지구 반경(km)
        val GRID = 5.0 // 격자 간격(km)
        val SLAT1 = 30.0 // 투영 위도1(degree)
        val SLAT2 = 60.0 // 투영 위도2(degree)
        val OLON = 126.0 // 기준점 경도(degree)
        val OLAT = 38.0 // 기준점 위도(degree)
        val XO = 43.0 // 기준점 X좌표(GRID)
        val YO = 136.0 // 기1준점 Y좌표(GRID)

        val DEGRAD = Math.PI / 180.0      //Math.PI : 원주율(파이)값
        val RADDEG = 180.0 / Math.PI
        val re = RE / GRID
        val slat1 = SLAT1 * DEGRAD
        val slat2 = SLAT2 * DEGRAD
        val olon = OLON * DEGRAD
        val olat = OLAT * DEGRAD

        var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn)
        var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn
        var ro = Math.tan(Math.PI * 0.25 + olat * 0.5)
        ro = re * sf / Math.pow(ro, sn)

        //여기서부터가 변환 코드
        // X값(위도) 변환
        var ra = Math.tan(Math.PI * 0.25 + lat_X * DEGRAD * 0.5)
        ra = re * sf / Math.pow(ra, sn)
        var theta = lng_Y * DEGRAD - olon
        if (theta > Math.PI) theta -= 2.0 * Math.PI
        if (theta < -Math.PI) theta += 2.0 * Math.PI
        theta *= sn

        return Math.floor(ra * Math.sin(theta) + XO + 0.5).toInt()
    }

    //위도, 경도를 기상청 격자 y값으로 바꿔주는 함수 (lat_X : 위도, lng_Y : 경도)
    fun convertGRID_Y(lat_X: Double, lng_Y: Double) : Int {

        //불변값
        val RE = 6371.00877 // 지구 반경(km)
        val GRID = 5.0 // 격자 간격(km)
        val SLAT1 = 30.0 // 투영 위도1(degree)
        val SLAT2 = 60.0 // 투영 위도2(degree)
        val OLON = 126.0 // 기준점 경도(degree)
        val OLAT = 38.0 // 기준점 위도(degree)
        val XO = 43.0 // 기준점 X좌표(GRID)
        val YO = 136.0 // 기1준점 Y좌표(GRID)

        val DEGRAD = Math.PI / 180.0 //degree(도->라디안)   *Math.PI : 원주율(파이)값
        val RADDEG = 180.0 / Math.PI //radian(라디안->도)
        val re = RE / GRID
        val slat1 = SLAT1 * DEGRAD
        val slat2 = SLAT2 * DEGRAD
        val olon = OLON * DEGRAD
        val olat = OLAT * DEGRAD

        var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn)
        var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn
        var ro = Math.tan(Math.PI * 0.25 + olat * 0.5)
        ro = re * sf / Math.pow(ro, sn)

        //여기서부터가 변환 코드

        // Y값(경도) 변환
        var ra = Math.tan(Math.PI * 0.25 + lat_X * DEGRAD * 0.5)
        ra = re * sf / Math.pow(ra, sn)
        var theta = lng_Y * DEGRAD - olon
        if (theta > Math.PI) theta -= 2.0 * Math.PI
        if (theta < -Math.PI) theta += 2.0 * Math.PI
        theta *= sn

        return Math.floor(ro - ra * Math.cos(theta) + YO + 0.5).toInt()
    }

    //체감온도 계산하는 함수
    fun convertFeelTemperature (T:Double, V:Double) : Int {
        var result = 0.0
        if (V > 4.8 ) {
            result = 13.12+0.6215*T-11.37*(Math.pow(V, 0.16)) + 0.3965*(Math.pow(V, 0.16))*T
            if(result > T) {
                result = T
            }
        } else {
            result = T
        }
        return result.toInt()
    }
    
}