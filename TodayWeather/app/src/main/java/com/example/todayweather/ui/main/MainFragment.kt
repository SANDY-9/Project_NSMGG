package com.example.todayweather.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentMainBinding
<<<<<<< HEAD:TodayWeather/app/src/main/java/com/example/todayweather/ui/main/MainFragment.kt
import com.example.todayweather.ui.BaseFragment
import com.example.todayweather.viewModel.BaseViewModel
=======
import com.example.todayweather.repository.retrofit.DustRetrofit
import com.example.todayweather.view.BaseFragment
import com.example.todayweather.viewModel.MainFragmentViewModel
>>>>>>> feature-Kang:TodayWeather/app/src/main/java/com/example/todayweather/view/main/MainFragment.kt

class MainFragment : BaseFragment<FragmentMainBinding, BaseViewModel>() {

    override lateinit var binding : FragmentMainBinding
    lateinit var navController: NavController

    override val layoutResourceId: Int
        get() = R.layout.fragment_main

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return binding.root

    }

    override val viewModel: BaseViewModel by activityViewModels()

    override fun initView() {
        navController = Navigation.findNavController(binding.root)
    }

    override fun initDataBinding() {
        TODO("Not yet implemented")
    }

    override fun initAfterBinding() {
        TODO("Not yet implemented")
    }

    override fun observerViewModel() {
        TODO("Not yet implemented")
    }

}