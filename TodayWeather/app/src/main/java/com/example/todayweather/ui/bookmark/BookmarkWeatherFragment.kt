package com.example.todayweather.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.viewModel.DataBaseViewModel
import com.example.todayweather.viewModel.WeatherViewModel

class BookmarkWeatherFragment : Fragment() {

    val weatherViewmodel : WeatherViewModel by activityViewModels()
    val dataBaseViewModel : DataBaseViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark_weather, container, false)
    }
}