package com.example.todayweather.ui.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentWeeklyBinding
import com.example.todayweather.viewModel.LocationViewModel
import com.example.todayweather.viewModel.WeatherViewModel

class WeeklyFragment : Fragment() {

    lateinit var binding : FragmentWeeklyBinding
    val weatherViewmodel : WeatherViewModel by activityViewModels()
    val locationViewModel : LocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentWeeklyBinding>(inflater, R.layout.fragment_weekly, container, false)
        return binding.root

    }

}