package com.example.todayweather.ui.basic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentDailyDetailBinding
import com.example.todayweather.viewModel.LocationViewModel
import com.example.todayweather.viewModel.WeatherViewModel

class CurrentFragment : Fragment() {

    lateinit var binding : FragmentDailyDetailBinding
    val weatherViewmodel : WeatherViewModel by activityViewModels()
    val locationViewModel : LocationViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentDailyDetailBinding>(inflater, R.layout.fragment_daily_detail, container, false)
        return binding.root
    }

}