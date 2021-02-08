package com.example.todayweather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import com.example.todayweather.R
import com.example.todayweather.databinding.ActivityMainBinding
import com.example.todayweather.helper.ConvertDateHelper
import com.example.todayweather.viewModel.LocationViewModel
import com.example.todayweather.viewModel.WeatherViewModel
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }
}