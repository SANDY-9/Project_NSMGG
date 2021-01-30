package com.example.todayweather.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.todayweather.viewModel.BaseViewModel

abstract class BaseFragment<T:ViewDataBinding, R:BaseViewModel> : Fragment() {

    //데이터바인딩 클래스
    open lateinit var binding: T

    //setContentView로 호출할 Layout의 리소스 Id.
    abstract val layoutResourceId: Int

    //viewModel 변수
    //변수 초기화 방법1 : override val viewModel: BaseViewModel by activityViewModels()
    //변수 초기화 방법2 : override val viewModel: BaseViewModel = BaseViewModel()
    abstract val viewModel: R

    //레이아웃을 띄운 직후 호출. 뷰나 액티비티의 속성 등을 초기화. ex) 리사이클러뷰, 툴바, 드로어뷰..
    abstract fun initView()

    //두번째로 호출.데이터 바인딩 및 rxjava 설정. ex) rxjava observe, databinding observe
    abstract fun initDataBinding()

    //바인딩 이후에 할 일을 여기에 구현. 그 외에 설정할 것이 있으면 이곳에서 설정. 클릭 리스너도 이곳에서 설정.
    abstract fun initAfterBinding()

    //뷰가 뷰모델의 라이브데이터를 옵저빙한다.
    abstract fun observerViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        initView()
        initDataBinding()
        initAfterBinding()
        observerViewModel()
        return binding.root
    }

}