package com.example.todayweather.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSearchMainBinding

class SearchMainFragment : Fragment() {

    lateinit var binding : FragmentSearchMainBinding
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentSearchMainBinding>(inflater, R.layout.fragment_search_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.before.setOnClickListener {
            navController.navigate(R.id.action_searchMainFragment_to_mainFragment)
        }
    }

}