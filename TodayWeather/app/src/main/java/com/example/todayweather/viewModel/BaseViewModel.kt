package com.example.todayweather.viewModel

import androidx.lifecycle.ViewModel
import com.example.todayweather.model.ResponseDTO
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-01-28
 * @desc
 */

open class BaseViewModel : ViewModel() {

    /**
     * RxJava 의 observing을 위한 부분. Model에서 들어오는 Single<>과 같은 RxJava 객체들의 Observing을 위한 부분이다
     * Observable들을 옵저빙할때, addDisposable을 이용하여 추가하기만 하면 된다
     * RxJava의 Observable들은 compositeDisposable에 추가를 해주고, 뷰모델이 없어질 때 추가했던 것들을 지워줘야한다.
     */
    private val compositeDisposable = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    // ViewModel은 View와의 생명주기를 공유하기 때문에 View가 부서질 때 ViewModel의 onCleared()가 호출되게 되며, 그에 따라 옵저버블들이 전부 클리어 된다.
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}