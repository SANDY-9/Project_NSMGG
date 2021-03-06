package com.example.todayweather.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentBookmarkMainBinding

class BookmarkMainFragment : Fragment() {

    lateinit var binding : FragmentBookmarkMainBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentBookmarkMainBinding>(inflater, R.layout.fragment_bookmark_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.closeButton.setOnClickListener {
            navController.navigate(R.id.action_bookmarkMainFragment_to_mainFragment)
        }

    }

}