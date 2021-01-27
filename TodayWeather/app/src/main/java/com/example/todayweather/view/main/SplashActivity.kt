package com.example.todayweather.view.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.room.*
import com.example.todayweather.R
import com.example.todayweather.databinding.ActivitySplashBinding
import com.example.todayweather.helper.CalculationHelper
import com.example.todayweather.model.CityWeatherTable
import com.example.todayweather.model.NationalWeatherTable
import com.example.todayweather.view.main.SplashActivity.AppDatabase.Companion.getInstance
import java.util.*

class SplashActivity : AppCompatActivity(), LocationListener {
    /*
    * 위치와 위치로 가져온 데이터들 이곳에서 불러오고 인텐트로 넘기기
    * */
    lateinit var binding : ActivitySplashBinding
    lateinit var locationManager: LocationManager

    lateinit var address : String
    lateinit var si : String
    lateinit var gu : String
    lateinit var dong : String

    lateinit var NationalWeatherDB : AppDatabase // room db

    var realX : Double? = null
    var realY : Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissioncheck()
        binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        binding.activity = this
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager // GPS정보를 어디서 얻어올 건지 초기화
        NationalWeatherDB = getInstance(this)!!
    }

    private fun registerLocationUpdates() {
        // LocationManager.GPS_PROVIDER 또는 LocationManager.NETWORK_PROVIDER 를 얻어온다.
        val provider = locationManager.getBestProvider(Criteria(), true)
        Toast.makeText(this, "GPS를 사용할 수 있습니다.", Toast.LENGTH_SHORT).show()
        if (provider == null) {
            Toast.makeText(this, "위치 정보를 사용할 수 있는 상태가 아닙니다, GPS를 켜주세요.", Toast.LENGTH_SHORT).show()
            return
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            return
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1f, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1f, this);

        //1000은 1초마다, 1은 1미터마다 해당 값을 갱신한다는 뜻으로, 딜레이마다 호출하기도 하지만
        //위치값을 판별하여 일정 미터단위 움직임이 발생 했을 때에도 리스너를 호출 할 수 있다.
        val location = locationManager.getLastKnownLocation(provider)
        Log.d("[test]",location.toString())

        if (location != null){
            location.let { onLocationChanged(it!!) }

            // 위치 정보 취득 시작
            locationManager.requestLocationUpdates(provider, 400, 1f, this)
            Toast.makeText(this, "위치 정보를 얻었습니다.", Toast.LENGTH_SHORT).show()
            Log.d("[test]",realX.toString())
            if ( realX != null ){
                address = getAddress(realX!!,realY!!)
            }
        }
    }

    //  버튼 클릭 이벤트
    fun nextActivity(view : View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        registerLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        //프로그램 멈추거나 인텐트 이동하면 위치 그만 탐색
        locationManager.removeUpdates(this)
    }

    // 퍼미션 체크
    private fun permissioncheck() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
            }
        }
    }

    // 위치가 바뀌면 호출되는 함수
    override fun onLocationChanged(location: Location) {
        if (location.provider == LocationManager.GPS_PROVIDER) {
            // 위치 값 가져오는 것들
            realX = location.latitude
            realY = location.longitude
            val convertX = CalculationHelper.convertGRID_X(realX!!, realY!!)
            val convertY = CalculationHelper.convertGRID_Y(realX!!, realY!!)
            Log.d("[test_gps]", "x = $convertX, y = $convertY")
        } else {
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
            // 위치 값 가져오는 것들
            realX = location.latitude
            realY = location.longitude
            val convertX = CalculationHelper.convertGRID_X(realX!!, realY!!)
            val convertY = CalculationHelper.convertGRID_Y(realX!!, realY!!)
            Log.d("[test_network]", "x = $convertX, y = $convertY")
        }
        address = getAddress(realX!!,realY!!)
    }

    //  현재 주소 구하기
    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault()) //주소 구하기 객체
        val address : String = geocoder.getFromLocation(lat, lng, 1)[0].getAddressLine(0)
        Log.e("[address]", address)
        si = address.split(" ")[1]
        gu = address.split(" ")[2]
        dong = address.split(" ")[3]
        return address
    }

    @Dao
    interface NationalWeatherInterface { // 동네예보
        // db 전부 호출
        @Query("SELECT * FROM dongnae")
        suspend fun getAll(): List<NationalWeatherTable>

        // 동 이름으로 데이터 찾기
        @Query("SELECT * FROM dongnae WHERE name3 LIKE :dong")
        suspend fun getChoice(dong : String): List<NationalWeatherTable>

        // nx, ny 값으로 데이터 가져오기
        @Query("SELECT * FROM dongnae WHERE x = :x AND y = :y")
        suspend fun getXY(x : Int, y : Int): List<NationalWeatherTable>

        @Insert
        suspend fun insert(nationalWeatherTable: NationalWeatherTable)

        @Query("DELETE FROM dongnae")
        suspend fun deleteAll()
    }

    @Dao
    interface CityWeatherInterface { // 중기예보
        @Query("SELECT * FROM weekly")
        suspend fun getAll(): List<CityWeatherTable>

        // 도시, 지역 이름으로 코드 찾기
        @Query("SELECT weeklyCode FROM weekly WHERE region LIKE :region1 AND city LIKE :city1")
        suspend fun getRegId( region1: String, city1: String): String

        // 지역 이름으로 코드 찾기
        @Query("SELECT weeklyCode FROM weekly WHERE region LIKE :region1")
        suspend fun getRegIdRegion( region1: String): String

        // db insert
        @Insert
        suspend fun insert(cityWeatherTable: CityWeatherTable)

        // delete db
        @Query("DELETE FROM weekly")
        suspend fun deleteAll()
    }

    @Database(entities = [NationalWeatherTable::class, CityWeatherTable::class], version = 1, exportSchema = false)
    abstract class AppDatabase: RoomDatabase() {
        abstract fun nationalWeatherInterface(): NationalWeatherInterface
        abstract fun cityWeatherInterface(): CityWeatherInterface

        companion object{
            private var INSTANCE: AppDatabase? = null

            fun getInstance(context: Context): AppDatabase? {
                if (INSTANCE == null) {
                    synchronized(AppDatabase::class) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "db").build()
                    }
                }
                return INSTANCE
            }
        }
    }
}
