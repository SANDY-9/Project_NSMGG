package com.example.todayweather.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.todayweather.ui.basic.CurrentFragment
import com.example.todayweather.ui.basic.DailyFragment
import com.example.todayweather.ui.basic.WeeklyFragment

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-06
 * @desc
 */
class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
        when(position) {
            0 -> fragment = DailyFragment()
            1 -> fragment = CurrentFragment()
            2 -> fragment =WeeklyFragment()
        }
        return fragment
    }

}