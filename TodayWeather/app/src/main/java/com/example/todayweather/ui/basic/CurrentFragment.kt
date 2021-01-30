package com.example.todayweather.ui.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentDailyDetailBinding
import com.example.todayweather.ui.BaseFragment
import com.example.todayweather.viewModel.DailyWeatherViewModel

class CurrentFragment : BaseFragment<FragmentDailyDetailBinding, DailyWeatherViewModel>() {

    override lateinit var binding: FragmentDailyDetailBinding
    override val layoutResourceId: Int
        get() = R.layout.fragment_daily_detail

    override val viewModel: DailyWeatherViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentDailyDetailBinding>(inflater, layoutResourceId, container, false)
        initView()
        initDataBinding()
        initAfterBinding()
        observerViewModel()
        return binding.root
    }

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun observerViewModel() {
        TODO("Not yet implemented")
    }
}