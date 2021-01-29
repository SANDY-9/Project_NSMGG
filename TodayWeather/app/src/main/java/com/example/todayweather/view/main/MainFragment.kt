package com.example.todayweather.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentMainBinding
import com.example.todayweather.view.BaseFragment
import com.example.todayweather.viewModel.MainFragmentViewModel

class MainFragment : BaseFragment<FragmentMainBinding, MainFragmentViewModel>() {

    override lateinit var binding : FragmentMainBinding

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

    override val viewModel: MainFragmentViewModel by activityViewModels()

    override fun initView() {
        TODO("Not yet implemented")
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