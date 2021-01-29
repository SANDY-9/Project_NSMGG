package com.example.todayweather.view.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.todayweather.R
import com.example.todayweather.databinding.FragmentSettingMainBinding
import com.example.todayweather.view.BaseFragment
import com.example.todayweather.view.main.MainActivity

class SettingMainFragment : Fragment() {

    lateinit var binding : FragmentSettingMainBinding
    lateinit var activity: MainActivity
    lateinit var navController: NavController

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
        initView()
        initDataBinding()
        initAfterBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity.setSupportActionBar(binding.toolbar)
        activity.getSupportActionBar()?.title = "설정"
        activity.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        if(item.itemId == android.R.id.home) {
        }
    }
    fun initView() {

    }

    fun initDataBinding() {
        activity = getActivity() as MainActivity
    }

    fun initAfterBinding() {
        TODO("Not yet implemented")
    }

}