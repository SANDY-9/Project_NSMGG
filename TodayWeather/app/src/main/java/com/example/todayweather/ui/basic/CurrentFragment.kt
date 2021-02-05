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

class CurrentFragment : Fragment() {

    lateinit var binding : FragmentDailyDetailBinding
    val currentWeatherViewModel: DailyWeatherViewModel by activityViewModels()
    val dailyWeatherViewModel: DailyWeatherViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentDailyDetailBinding>(inflater, R.layout.fragment_daily_detail, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}