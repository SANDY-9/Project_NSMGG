package com.example.todayweather.ui.basic

import android.os.Bundle
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
import com.example.todayweather.databinding.FragmentDailyDetailBinding
import com.example.todayweather.ui.main.StartActivity
import com.example.todayweather.ui.main.StartActivity.Companion.realX
import com.example.todayweather.ui.main.StartActivity.Companion.realY
import com.example.todayweather.viewModel.LocationLiveData
import com.example.todayweather.viewModel.LocationViewModel
import com.example.todayweather.viewModel.WeatherViewModel

class CurrentFragment : Fragment() {

    lateinit var binding : FragmentDailyDetailBinding
    val weatherViewmodel : WeatherViewModel by lazy {
        ViewModelProvider(this, WeatherViewModel.Factory())
                .get(WeatherViewModel::class.java)
    }
    val locationViewModel : LocationViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragmentDailyDetailBinding>(inflater, R.layout.fragment_daily_detail, container, false)
        binding.weatherDetail  = weatherViewmodel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGps.setOnClickListener {
            Toast.makeText(context, "위치 탐색중", Toast.LENGTH_SHORT).show()
            binding.region.text = StartActivity().getAddress(realX!!,realY!!)
            LocationLiveData.get(context)!!.observe(viewLifecycleOwner, Observer {
//                binding.region.text = "위치 탐색중"
            })
        }
    }
}