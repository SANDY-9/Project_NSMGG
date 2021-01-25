package com.example.todayweather.view.basic

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentDailyDetailBinding

class DailyDetailFragment : Fragment() {

    lateinit var binding: FragmentDailyDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentDailyDetailBinding>(inflater, R.layout.fragment_daily_detail, container, false)
        return binding.root
    }
}