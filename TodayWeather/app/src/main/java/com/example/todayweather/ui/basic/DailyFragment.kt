package com.example.todayweather.ui.basic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.todayweather.R
import com.example.todayweather.data.model.CurrentWeather
import com.example.todayweather.data.network.*
import com.example.todayweather.databinding.FragmentDailyBinding
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DailyFragment : Fragment() {

    lateinit var binding: FragmentDailyBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //데이터 바인딩 초기화
        binding = DataBindingUtil.inflate<FragmentDailyBinding>(inflater, R.layout.fragment_daily, container, false)

        observerViewModel()

        return binding.root
    }

    //뷰가 뷰모델의 라이브데이터를 옵저빙한다.
    private fun observerViewModel() {
        val weatherAPIService = WeatherAPIService(ConnectivityInterceptorImpl(requireActivity()))
        val airAPIService = AirAPIService(ConnectivityInterceptorImpl(requireActivity()))
        val retrofit = RetrofitNetWorkImpl(weatherAPIService, airAPIService)
        retrofit.shortTimeWeather.observe(viewLifecycleOwner, Observer {
            binding.date.text = it.response.toString()
        })
        binding.buttonGps.setOnClickListener { GlobalScope.launch (Dispatchers.Main) {
            val response = retrofit.fetchShortermTimeWeather(61, 126)
        } }
    }

}
