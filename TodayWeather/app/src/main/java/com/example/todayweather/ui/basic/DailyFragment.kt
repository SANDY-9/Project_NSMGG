package com.example.todayweather.ui.basic

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentDailyBinding
import com.example.todayweather.ui.main.StartActivity
import com.example.todayweather.ui.main.StartActivity.Companion.realX
import com.example.todayweather.ui.main.StartActivity.Companion.realY
import com.example.todayweather.viewModel.LocationLiveData
import com.example.todayweather.viewModel.LocationViewModel
import com.example.todayweather.viewModel.WeatherViewModel

class DailyFragment : Fragment() {

    lateinit var binding: FragmentDailyBinding
    val weatherViewmodel : WeatherViewModel by activityViewModels()
    val locationViewModel : LocationViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //데이터 바인딩 초기화
        binding = DataBindingUtil.inflate<FragmentDailyBinding>(inflater, R.layout.fragment_daily, container, false)
        binding.weather = weatherViewmodel
        binding.location = locationViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGps.setOnClickListener {
            Toast.makeText(context, "위치 탐색중",Toast.LENGTH_SHORT).show()
            LocationLiveData.get(context)?.observe(viewLifecycleOwner, Observer {
                LocationViewModel().setLocation(it)
            })
            binding.location!!.address.value = StartActivity().getAddress(realX!!, realY!!)

        }
    }

}
