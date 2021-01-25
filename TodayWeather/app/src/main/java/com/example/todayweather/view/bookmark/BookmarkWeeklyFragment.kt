package com.example.todayweather.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentBookmarkWeeklyBinding

class BookmarkWeeklyFragment : Fragment() {

    lateinit var binding : FragmentBookmarkWeeklyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentBookmarkWeeklyBinding>(inflater, R.layout.fragment_bookmark_weekly, container, false)
        return binding.root
    }

}