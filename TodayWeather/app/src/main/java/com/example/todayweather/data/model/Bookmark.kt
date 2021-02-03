package com.example.todayweather.data.model

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc 북마크 정보 저장(필요한 정보 : 주소, nx, ny, 미세먼지 측정소, 육상예보구역 코드(주간날씨 최저최고기온찾기), 예보지역행정코드(주간날씨 강수확률찾기))
 */

open class Bookmark {

    var region : String
        get() {
            return region
        }
        set(value) {
            region = value
        }

    var nx : Int
        get() {
            return nx
        }
        set(value) {
            nx = value
        }

    var ny : Int
        get() {
            return ny
        }
        set(value) {
            ny = value
        }

    var dustAddr : String
        get() {
            return dustAddr
        }
        set(value) {
            dustAddr = value
        }

    var codeTemp : String
        get() {
            return codeTemp
        }
        set(value) {
            codeTemp = value
        }

    var codeRain : String
        get() {
            return codeRain
        }
        set(value) {
            codeRain = value
        }
}