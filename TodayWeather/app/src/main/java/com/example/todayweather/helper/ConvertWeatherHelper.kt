package com.example.todayweather.helper

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-25
 * @desc 기상청으로 받아온 데이터 해석
 */

object ConvertWeatherHelper {

    // SKY : 하늘
    fun convertSKY (SKY: Int) : String {
        var result = ""
        when (SKY) {
            1 -> result = "맑음"
            2 -> result = "구름조금"
            3 -> result = "구름많음"
            4 -> result = "흐림"
        }
        return result
    }

    // PTY : 강수형태
    fun convertPTY (PTY: Int) : String {
        var result = ""
        when (PTY) {
            0 -> result = "없음"
            1 -> result = "비"
            2 -> result = "진눈깨비"
            3 -> result = "눈"
            4 -> result = "소나기"
            5 -> result = "비"
            6 -> result = "진눈깨비"
            7 -> result = "눈"
        }
        return result
    }

    // SKY값이랑 PTY 둘다 있을 때(초단기실황(현재날씨) 제외한 거의 모든 경우 ) -> 날씨로 변환
    fun convertWeather (SKY: Int, PTY: Int) : String {
        var result = ""
        when (PTY) {
            0 ->
                when (SKY) {
                    1 -> result = "맑음"
                    2 -> result = "구름조금"
                    3 -> result = "구름많음"
                    4 -> result = "흐림"
                }
            1 -> result = "비"
            2 -> result = "진눈깨비"
            3 -> result = "눈"
            4 -> result = "소나기"
            5 -> result = "비"
            6 -> result = "진눈깨비"
            7 -> result = "눈"
        }
        return result
    }

    // 강수량 변환
    fun convertRainfall (RN: String) : String {
        var result = ""
        val RN_d = RN.toDouble()
        if (0.1 <= RN_d && RN_d < 1.0) {
            result = "1mm 미만"
        } else {
            val RN_i = RN_d.toInt()
            when (RN_i) {
                0 -> result = "0mm"
                // in 0.1..1.0 -> result = "1mm 미만"
                in 1..5 -> result = "1~4mm"
                in 5..10 -> result = "5~9mm"
                in 10..20 -> result = "10~19mm"
                in 20..40 -> result = "20~39mm"
                in 40..70 -> result = "40~69mm"
                else -> result = "70mm 이상"
            }
        }
        return result
    }

}
