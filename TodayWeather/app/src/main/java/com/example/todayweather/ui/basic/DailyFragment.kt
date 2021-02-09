package com.example.todayweather.ui.basic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.todayweather.R
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.network.*
import com.example.todayweather.databinding.FragmentDailyBinding
import com.example.todayweather.viewModel.LocationViewModel
import com.example.todayweather.viewModel.WeatherViewModel
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DailyFragment : Fragment() {

    lateinit var binding: FragmentDailyBinding
    val weatherViewmodel : WeatherViewModel by activityViewModels()
    val locationViewModel : LocationViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        //데이터 바인딩 초기화
        binding = DataBindingUtil.inflate<FragmentDailyBinding>(inflater, R.layout.fragment_daily, container, false)
        binding.weather = weatherViewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
