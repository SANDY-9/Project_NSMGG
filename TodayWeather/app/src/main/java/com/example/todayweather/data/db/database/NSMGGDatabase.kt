package com.example.todayweather.data.db.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todayweather.data.network.CityWeatherTable
import com.example.todayweather.data.network.NationalWeatherTable
import com.example.todayweather.ui.main.SplashActivity

/**
 * @author SANDY
 * @email nnal0256@naver.com
 * @created 2021-02-04
 * @desc 우리 앱에서 사용할 데이터베이스 만들기
 */

@Database(entities = [NationalWeatherTable::class, CityWeatherTable::class], version = 1, exportSchema = false)
abstract class NSMGGDatabase : RoomDatabase() {
    abstract fun nationalWeatherInterface(): SplashActivity.NationalWeatherInterface
    abstract fun cityWeatherInterface(): SplashActivity.CityWeatherInterface

    companion object {
        private var INSTANCE: SplashActivity.AppDatabase? = null

        fun getInstance(context: Context): SplashActivity.AppDatabase? {
            if (INSTANCE == null) {
                synchronized(SplashActivity.AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, SplashActivity.AppDatabase::class.java, "weatherDB").build()
                }
            } else {
                Log.d("[test_instance]", "인스턴스 null 아님")
            }
            return INSTANCE
        }
    }
}