package com.example.todayweather.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSearchFailBinding

class SearchFailFragment : Fragment() {

    lateinit var binding : FragmentSearchFailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchFailBinding>(inflater, R.layout.fragment_search_fail, container, false)
        return binding.root
    }

}