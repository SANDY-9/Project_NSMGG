package com.example.todayweather.view.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSettingMainBinding
import com.example.todayweather.view.main.MainActivity

class SettingMainFragment : Fragment() {

    lateinit var binding : FragmentSettingMainBinding
    lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_setting_main,
            container,
            false
        )
        activity = getActivity() as MainActivity
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.setSupportActionBar(binding.toolbar)
        activity.getSupportActionBar()?.title = "설정"
        activity.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

    }

}