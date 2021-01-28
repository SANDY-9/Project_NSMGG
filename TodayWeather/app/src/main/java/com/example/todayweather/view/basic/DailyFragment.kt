package com.example.todayweather.view.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentDailyBinding
import com.example.todayweather.viewModel.CurrentWeatherViewModel

class DailyFragment : Fragment() {

    lateinit var binding: FragmentDailyBinding
    //뷰모델 초기화-프래그먼트 : by activityViewModels() 이용, 변수는 무조건 val변수=>lateinit var 불가능
    val currentWeatherViewModel: CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //데이터 바인딩 초기화
        binding = DataBindingUtil.inflate<FragmentDailyBinding>(inflater, R.layout.fragment_daily, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observerViewModel()
    }

    //뷰가 뷰모델의 라이브데이터를 옵저빙한다.
    private fun observerViewModel() {
       // currentWeatherViewModel.date.observe()
    }


}