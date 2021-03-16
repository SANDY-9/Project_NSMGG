package com.example.todayweather.ui.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentWeeklyBinding
import com.example.todayweather.ui.main.StartActivity
import com.example.todayweather.viewModel.LocationLiveData
import com.example.todayweather.viewModel.LocationViewModel
import com.example.todayweather.viewModel.WeatherViewModel

class WeeklyFragment : Fragment() {

    lateinit var binding : FragmentWeeklyBinding
    val weatherViewmodel : WeatherViewModel by lazy {
        ViewModelProvider(this, WeatherViewModel.Factory())
                .get(WeatherViewModel::class.java)
    }
    val locationViewModel : LocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentWeeklyBinding>(inflater, R.layout.fragment_weekly, container, false)
        binding.weatherWeekly = weatherViewmodel
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGps2.setOnClickListener {
            Toast.makeText(context, "위치 탐색중", Toast.LENGTH_SHORT).show()
            binding.region2.text = StartActivity().getAddress(StartActivity.realX!!, StartActivity.realY!!)
            LocationLiveData.get(context)!!.observe(viewLifecycleOwner, Observer {
//                binding.region.text = "위치 탐색중"
            })
        }
    }
}