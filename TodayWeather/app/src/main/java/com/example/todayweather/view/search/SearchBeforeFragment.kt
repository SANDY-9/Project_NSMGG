package com.example.todayweather.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSearchBeforeBinding

class SearchBeforeFragment : Fragment() {

    lateinit var binding : FragmentSearchBeforeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchBeforeBinding>(inflater, R.layout.fragment_search_before, container, false)
        return binding.root
    }

}