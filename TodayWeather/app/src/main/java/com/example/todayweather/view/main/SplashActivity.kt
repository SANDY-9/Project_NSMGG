package com.example.todayweather.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.todayweather.R
import com.example.todayweather.databinding.ActivitySplashBinding
import com.example.todayweather.helper.CalculationHelper
import java.time.temporal.TemporalAdjusters.next

class SplashActivity : AppCompatActivity() {

    lateinit var binding : ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
//        binding.button.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
        binding.activity = this
    }

    fun nextActivity(view : View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}