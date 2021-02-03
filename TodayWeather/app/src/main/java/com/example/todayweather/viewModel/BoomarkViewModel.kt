package com.example.todayweather.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todayweather.data.model.Bookmark

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-30
 * @desc 북마크관리
 */
class BoomarkViewModel : ViewModel() {

    val bookmark : MutableLiveData<Bookmark> = MutableLiveData<Bookmark>()

}