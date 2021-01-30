package com.example.todayweather.ui.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentWeeklyBinding

class WeeklyFragment : Fragment() {

    lateinit var binding : FragmentWeeklyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentWeeklyBinding>(inflater, R.layout.fragment_weekly, container, false)
        return binding.root

    }

}