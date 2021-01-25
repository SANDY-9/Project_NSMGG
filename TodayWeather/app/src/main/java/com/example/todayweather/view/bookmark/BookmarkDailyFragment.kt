package com.example.todayweather.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentBookmarkDailyBinding

class BookmarkDailyFragment : Fragment() {

    lateinit var binding: FragmentBookmarkDailyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentBookmarkDailyBinding>(inflater, R.layout.fragment_bookmark_daily, container, false)
        return binding.root
    }

}