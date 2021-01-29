package com.example.todayweather.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentBookmarkDailyBinding
import com.example.todayweather.view.BaseFragment
import com.example.todayweather.viewModel.BaseViewModel
import com.example.todayweather.viewModel.CurrentWeatherViewModel

class BookmarkDailyFragment : BaseFragment<FragmentBookmarkDailyBinding, CurrentWeatherViewModel>() {

    override lateinit var binding: FragmentBookmarkDailyBinding
    val currentWeatherViewModel : CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentBookmarkDailyBinding>(inflater, R.layout.fragment_bookmark_daily, container, false)
        return binding.root
    }

    override val layoutResourceId: Int
        get() = TODO("Not yet implemented")

    override val viewModel: CurrentWeatherViewModel
        get() = TODO("Not yet implemented")

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initDataBinding() {
        TODO("Not yet implemented")
    }

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun observerViewModel() {
        TODO("Not yet implemented")
    }

}