package com.example.todayweather.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.todayweather.R
import com.example.todayweather.adapter.ViewPagerAdapter
import com.example.todayweather.databinding.FragmentMainBinding
import java.util.*
import kotlin.concurrent.timer


class MainFragment : Fragment() {

    lateinit var binding : FragmentMainBinding
    lateinit var navController: NavController
    lateinit var menu_Animation : Animation
    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent(view)
        setComponent()
        setEvent()
    }

    private fun initComponent(view: View) {
        navController = Navigation.findNavController(view)
        menu_Animation = AnimationUtils.loadAnimation(context, R.anim.menu_anim)
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    private fun setComponent() {
        //인디케이터 세팅
        binding.indicator.setViewPager(binding.viewPager)
        binding.indicator.createIndicators(3, 0)
        //뷰페이저 세팅
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.currentItem = 0
        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (positionOffsetPixels == 0) {
                    binding.viewPager.setCurrentItem(position)
                }
            }
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.indicator.animatePageSelected(position)
            }
        })
    }

    private fun setEvent() {
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.search -> {
                    navController.navigate(R.id.action_mainFragment_to_searchMainFragment)
                    true
                }
                R.id.menu -> {
                    binding.menuLayout.visibility = VISIBLE
                    //애니메이션
                    binding.menuLayout.startAnimation(menu_Animation)

                    //백그라운드 어둡게
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.backlayout.visibility = VISIBLE
                    },500)

                    true
                }
                else -> false
            }
        }
        binding.closeButton.setOnClickListener {
            binding.menuLayout.visibility = GONE
            binding.backlayout.visibility = GONE
        }
        binding.goSetting.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_settingMainFragment)
        }
        binding.backlayout.setOnClickListener {
            binding.menuLayout.visibility = GONE
            binding.backlayout.visibility = GONE
        }
    }

}