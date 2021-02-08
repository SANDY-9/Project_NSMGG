package com.example.todayweather.ui.main

import android.os.Bundle
import android.view.*
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    lateinit var binding : FragmentMainBinding
    lateinit var navController: NavController

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.search -> {
                    navController.navigate(R.id.action_mainFragment_to_searchMainFragment)
                    true
                }
                R.id.menu -> {
                    binding.menuLayout.visibility = VISIBLE
                    true
                }
                else -> false
            }
        }
        binding.closeButton.setOnClickListener { binding.menuLayout.visibility = GONE }
        binding.goSetting.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_settingMainFragment)
        }
    }

}