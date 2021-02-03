package com.example.todayweather.ui.basic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentDailyBinding
import com.example.todayweather.repository.retrofit.AirAPIService
import com.example.todayweather.repository.retrofit.DailyRetrofit
import com.example.todayweather.repository.retrofit.WeatherAPIService
import com.example.todayweather.repository.retrofit.response.CurrentWeatherResponse
import com.example.todayweather.viewModel.CurrentWeatherViewModel
import com.example.todayweather.viewModel.DailyWeatherViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

class DailyFragment : Fragment() {

    lateinit var binding: FragmentDailyBinding
    val currentWeatherViewModel: CurrentWeatherViewModel by activityViewModels()
    val dailyWeatherViewModel : DailyWeatherViewModel by activityViewModels()

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
        val weatherAPIService = WeatherAPIService()
        Log.e("[TEST]", "2")
        GlobalScope.launch (Dispatchers.Main){
            val currentWeatherResponse = weatherAPIService.getCurrentWeather(
                "20210203", "1930", 61, 126
            )
            Log.e("[TEST]", "3")
            binding.region.text = currentWeatherResponse.body().toString()
            Log.e("[TEST]", currentWeatherResponse.toString())
        }
    }

    //뷰가 뷰모델의 라이브데이터를 옵저빙한다.
    private fun observerViewModel() {
    }


}