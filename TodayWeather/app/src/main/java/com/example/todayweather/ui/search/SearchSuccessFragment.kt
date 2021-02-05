package com.example.todayweather.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSearchSuccessBinding

class SearchSuccessFragment : Fragment() {

    lateinit var binding : FragmentSearchSuccessBinding
    val currentWeatherViewModel : CurrentWeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchSuccessBinding>(inflater, R.layout.fragment_search_success, container, false)
        return binding.root
    }

}