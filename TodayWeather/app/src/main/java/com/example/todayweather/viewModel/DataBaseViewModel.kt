package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
<<<<<<< HEAD
=======
import com.example.todayweather.data.db.database.*
>>>>>>> develop
import com.example.todayweather.data.model.Bookmark

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-07
 * @desc 데이터베이스 생성과 즐겨찾기, 자동완성 등 db관련 요청을 담당하는 뷰모델
 */
<<<<<<< HEAD
class DataBaseViewModel : ViewModel() {
=======
class DataBaseViewModel : ViewModel(){
>>>>>>> develop

    val bookmark : MutableLiveData<Bookmark> = MutableLiveData<Bookmark>()

    fun createDB () {

    }

    //자동완성 목록 가져오기
    fun getAutoCompleteDataList() : List<String> {
        val autoCompleteList = arrayListOf<String>()
        return autoCompleteList
    }



}