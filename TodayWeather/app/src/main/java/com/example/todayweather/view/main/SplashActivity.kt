package com.example.todayweather.view.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.location.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.room.*
import com.example.todayweather.R
import com.example.todayweather.databinding.ActivitySplashBinding
import com.example.todayweather.helper.CalculationHelper
import com.example.todayweather.model.CityWeatherTable
import com.example.todayweather.model.NationalWeatherTable
import com.example.todayweather.view.main.SplashActivity.AppDatabase.Companion.getInstance
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import java.util.*

class SplashActivity : AppCompatActivity(), LocationListener {
    /*
    * 위치와 위치로 가져온 데이터들 이곳에서 불러오고 인텐트로 넘기기
    * */
    lateinit var binding : ActivitySplashBinding
    lateinit var locationManager: LocationManager

    lateinit var address : String
//    lateinit var si : String
//    lateinit var gu : String
//    lateinit var dong : String

    lateinit var NationalWeatherDB : AppDatabase // room db

    var realX : Double? = null
    var realY : Double? = null
    var convertX : Int? = null
    var convertY : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissioncheck()
        binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        binding.activity = this
        NationalWeatherDB = getInstance(this)!!
        SharedPref()
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager // GPS정보를 어디서 얻어올 건지 초기화


//        WeeklyRetrofit("경기도","성남시", 1).WeeklyRetrofit()
//        DailyRetrofit(1).weather(convertX!!,convertY!!)
    }

    // 위치파악시 GPS, Network provider사용 구분 함수
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
            location.let { onLocationChanged(it) }

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
        // 버전이 맞는지 확인
        val firebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        firebaseRemoteConfig.fetch(0).addOnCompleteListener{task ->
            if (task.isSuccessful){
                firebaseRemoteConfig.fetchAndActivate()
                fun_DialogDisplay(firebaseRemoteConfig)
            }else{
                AlertDialog.Builder(this)
                        .setTitle("ERROR")
                        .setMessage("앱을 실행하는데 오류가 발생하였습니다. 다시 시도해주시기 바랍니다.")
                        .setCancelable(false)
                        .setPositiveButton("OK", DialogInterface.OnClickListener {
                            dialogInterface, i -> this.finish()}).show()
            }
        }
    }

    fun fun_GetAppVersion() : String { // gradle(app)에서 설정한 versionName 읽어오는 것
        val packageManager = this.packageManager
        return packageManager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES).versionName
    }
    fun fun_DialogDisplay(firebaseRemoteConfig: FirebaseRemoteConfig){
        val strVersionName = fun_GetAppVersion() // Version from gradle(app)
        val strLatestVersion = firebaseRemoteConfig.getString("message_version") // Version from firebase (firebase에서 설정한 key을 가져옴)

        if (strVersionName!=strLatestVersion){ // 앱 버전이 서로 다른 경우 끄기.
            AlertDialog.Builder(this)
                    .setTitle("Update")
                    .setMessage("최신 버전의 앱을 설치 후 재실행 해주시기 바랍니다.")
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener {
                        dialogInterface, i -> this.finish()
                    }).show()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("address", address)
            intent.putExtra("x", realX)
            intent.putExtra("y", realY)// 앱 버전이 같으면 MainActivity로 이동
            startActivity(intent)
            this.finish()
        }
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
        // 위치 값 가져오는 것들
        realX = location.latitude
        realY = location.longitude
        convertX = CalculationHelper.convertGRID_X(realX!!, realY!!)
        convertY = CalculationHelper.convertGRID_Y(realX!!, realY!!)
        Log.d("[test_gps]", "x = $convertX, y = $convertY , location = $location")

        address = getAddress(realX!!,realY!!)
    }

    //  현재 주소 구하기
    private fun getAddress(lat: Double, lng: Double): String {
        val geocoder = Geocoder(this, Locale.getDefault()) //주소 구하기 객체
        val address : String = geocoder.getFromLocation(lat, lng, 1)[0].getAddressLine(0) // 현재 주소
        Log.e("[address]", address)
        // 시 구 동으로 나눔
//        si = address.split(" ")[1]
//        gu = address.split(" ")[2]
//        dong = address.split(" ")[3]
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

        companion object {
            private var INSTANCE: AppDatabase? = null

            fun getInstance(context: Context): AppDatabase? {
                if (INSTANCE == null) {
                    synchronized(AppDatabase::class) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "weatherDB").build()
                    }
                } else {
                    Log.d("[test_instance]", "인스턴스 null 아님")
                }
                return INSTANCE
            }
        }
    }

    // 최초 실행시 한 번만 실행하게 함
    private fun SharedPref() {
        val pref : SharedPreferences = getSharedPreferences("isDB", Activity.MODE_PRIVATE)
        val download : Boolean = pref.getBoolean("isDB",false)
        if (!download){
            Log.d("Is first Time?", "firstD")

            val editer : SharedPreferences.Editor = pref.edit()
            editer.putBoolean("isDB",true)
            editer.apply()
            editer.commit()
            DongnaeReadTxt()
        }else{
            Log.d("Is first Time?", "not firstD");
        }
    }

    private fun DongnaeReadTxt(){

        //val input = NationalWeatherTable(1114062500,"seoul", "jongrogu", "dasandong", 60, 126) // 임의로 써서 넣은 함수

        val assetManager: AssetManager = resources.assets
        val inputStream: InputStream = assetManager.open("dongnae.txt")

        inputStream.bufferedReader().readLines().forEach {
            var token = it.split("\t")
            var input = NationalWeatherTable(token[0].toLong(), token[1], token[2], token[3], token[4].toInt(), token[5].toInt())
            CoroutineScope(Dispatchers.Main).launch {
                NationalWeatherDB!!.nationalWeatherInterface().insert(input)
            }
//             Log.d("file_test", token.toString())
        }
// 여기까지 db생성 코드

        // db 확인하는 코드
        CoroutineScope(Dispatchers.Main).launch {
            //NationalWeatherDB.nationalWeatherInterface().deleteAll()
            //NationalWeatherDB.nationalWeatherInterface().insert(input)
            var output = NationalWeatherDB!!.nationalWeatherInterface().getAll()
            Log.d("db_test", "$output")
        }
        WeeklyReadTxt()
    }
    private fun WeeklyReadTxt(){

        val assetManager: AssetManager = resources.assets
        val inputStream: InputStream = assetManager.open("weekly.txt")

        inputStream.bufferedReader().readLines().forEach {
            var token = it.split("\t")
            var input = CityWeatherTable(token[0], token[1], token[2])
            CoroutineScope(Dispatchers.Main).launch {
                NationalWeatherDB!!.cityWeatherInterface().insert(input)
            }
//            Log.d("file_test", token.toString())
        }
// 여기까지 db생성 코드

        CoroutineScope(Dispatchers.Main).launch {
            //cityWeatherDB.cityWeatherInterface().deleteAll()
            //cityWeatherDB.cityWeatherInterface().insert(input)
            var output = NationalWeatherDB!!.cityWeatherInterface().getAll()
            Log.d("db_test", "$output")
        }
    }
}
