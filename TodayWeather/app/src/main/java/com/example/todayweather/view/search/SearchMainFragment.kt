package com.example.todayweather.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSearchMainBinding

class SearchMainFragment : Fragment() {

    lateinit var binding : FragmentSearchMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchMainBinding>(inflater, R.layout.fragment_search_main, container, false)
        return binding.root
    }

}