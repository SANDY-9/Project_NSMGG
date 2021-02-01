package com.example.todayweather.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.example.todayweather.R
import com.example.todayweather.model.DustDTO

import com.example.todayweather.model.DustModel
import com.example.todayweather.repository.retrofit.DustRetrofit



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //미세먼지 조회
        // 1. 프래그먼트에서 컨텍스트 어케 넣는지 모르겠당
        // 2. 라이브러리 데이터 어캐 불러오는거임? 모르겠음
        // 3. 측정소 조회 구,시,군(addr) 넣어 보내서  측정소(stationName) 가져오기 했음 검사받기
        // 4. 선누 아프디마
        //낫널로 어캐 바꾸드라

        val asdf = DustRetrofit(this)
        var stationName = asdf.dustAddr("성동구")
        Log.e("asd", "onCreate: "+stationName )
        asdf.dailyDust(stationName)

    }
}