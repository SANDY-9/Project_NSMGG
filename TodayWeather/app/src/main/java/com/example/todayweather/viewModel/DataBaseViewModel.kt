package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.todayweather.data.model.Bookmark

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-07
 * @desc 데이터베이스 생성과 즐겨찾기, 자동완성 등 db관련 요청을 담당하는 뷰모델
 */
class DataBaseViewModel {

    val bookmark : MutableLiveData<Bookmark> = MutableLiveData<Bookmark>()

    fun createDB () {

    }


}