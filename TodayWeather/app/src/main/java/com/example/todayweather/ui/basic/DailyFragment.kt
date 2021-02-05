package com.example.todayweather.ui.basic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.todayweather.R
import com.example.todayweather.data.network.AirAPIService
import com.example.todayweather.databinding.FragmentDailyBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DailyFragment : Fragment() {

    lateinit var binding: FragmentDailyBinding
    val currentWeatherViewModel: CurrentWeatherViewModel by activityViewModels()
    val dailyWeatherViewModel : DailyWeatherViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //데이터 바인딩 초기화
        binding = DataBindingUtil.inflate<FragmentDailyBinding>(inflater, R.layout.fragment_daily, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val airAPIService = AirAPIService
        GlobalScope.launch(Dispatchers.Main) {
            val dustStationSearch = airAPIService.invoke().getDustFindStation("성동구").await()
            val dustCurrent = airAPIService.invoke().getDustStation(dustStationSearch.response!!.body.items[0].stationName).await()
            Log.e("[TEST]", dustStationSearch.response!!.body.items[0].stationName+"테스트")
          //  val item : List<CurrentWeatherResponse.Response.Body.Items.Item> = fetchCurrentWeather.body()!!.response.body.items.item
            val dust = dustCurrent.response!!.body.items[0].pm10Grade

            Log.e("[TEST]", dust)

        }

    }

    //뷰가 뷰모델의 라이브데이터를 옵저빙한다.
    private fun observerViewModel() {
    }


}