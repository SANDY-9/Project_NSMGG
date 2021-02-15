package com.example.todayweather.ui.setting

import android.app.*
import android.content.*
import android.content.Intent.ACTION_SENDTO
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.todayweather.R
import com.example.todayweather.data.network.SimpleWorker
import java.util.*
import java.util.concurrent.TimeUnit


class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        // 버튼 누르면 이메일 보내기로 가기
        val peedback : Preference? = findPreference("peedback")
        peedback?.setOnPreferenceClickListener {
            val intent=Intent(ACTION_SENDTO, Uri.parse("mailto:aoqnwnd@naver.com"))
            activity?.startActivity(intent)
            true
        }

// 버전 이름 함수로 가져오기
        val version : Preference? = findPreference("version")
        val packageManager = activity?.packageManager
        version?.summary = packageManager?.getPackageInfo(
                context?.packageName.toString(),
                PackageManager.GET_ACTIVITIES
        )?.versionName



// 시간 수정 버튼 누를 때 dialog띄우기
        val attachment : Preference = findPreference("attachment")!!
        val pref : SharedPreferences? = activity?.getSharedPreferences(
                "timeSetting",
                Activity.MODE_PRIVATE
        )
        val timeSetting  = pref?.getBoolean("timeSetting", false)
        val hourSetting  = pref?.getString("hourSetting", "오전 07")
        val minSetting  = pref?.getString("minSetting", "00")

        val editer : SharedPreferences.Editor = pref!!.edit()

        if (!timeSetting!!){
            editer.putBoolean("timeSetting", true)
            editer.apply()
            editer.commit()

            attachment.summary = "알림 받을 시간을 설정해주세요."
        }else{
            attachment.summary = "매일 ${hourSetting.toString()}:${minSetting.toString()}에 오늘 날씨에 대한 정보 알림을 받습니다."
        }
// 알림 시간 설정 클릭시
        attachment.setOnPreferenceClickListener {
            if (hourSetting!!.split(" ")[0]=="오후"){
                numberPickerCustom(attachment, editer, hourSetting.split(" ")[1].toInt()+12, minSetting!!.toInt())
            }else{
                numberPickerCustom(attachment, editer, hourSetting.split(" ")[1].toInt(), minSetting!!.toInt())
            }
            Log.d("[test]","${hourSetting.split(" ")[1].toInt()}, ${minSetting.toInt()}")
            true
        }

// 알림 받기 유무
        val sync : SwitchPreferenceCompat? = findPreference("sync")
        sync?.setOnPreferenceChangeListener { preference, newValue ->
            if (sync.isChecked){
                // 없애기
                Log.d("[test]","체크 되어있을 때")
            }else{
                numberPickerCustom(attachment, editer, hourSetting!!.split(" ")[1].toInt(), minSetting!!.toInt())
            }
            true
        }
    }

    fun numberPickerCustom(attachment: Preference, editer: SharedPreferences.Editor, base_hour: Int, base_min: Int) {
        val d = AlertDialog.Builder(context)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.number_picker_dialog, null)
        d.setTitle("알림 시간 설정")
        d.setMessage("알림 받을 시간을 설정해주세요.")
        d.setView(dialogView)

        val numberPickerH = dialogView.findViewById<NumberPicker>(R.id.numperPickerH)
        numberPickerH.minValue = 0
        numberPickerH.maxValue = 23
        numberPickerH.value = base_hour
        val numberPickerM = dialogView.findViewById<NumberPicker>(R.id.numperPickerM)
        numberPickerM.minValue = 0
        numberPickerM.maxValue = 59
        numberPickerM.value = base_min
//        numberPickerM.displayedValues = arrayOf("0", "10", "20", "30", "40", "50")

        d.setPositiveButton("설정") { dialogInterface, i ->
            val result_hour = numberPickerH.value
            val result_min = numberPickerM.value
            //24시로 표시
            Log.d("[test]", "onClick: ${result_hour}, ${result_min}")

            var str_hour = "오후 ${result_hour-12}"
            var str_min = "$result_min"

            if (result_hour<13){
                str_hour="오전 $result_hour"
                if (result_hour<10){
                    str_hour = "오전 0$result_hour"
                }
            }

            if (result_min < 10){
                str_min = "0$result_min"
            }

            Toast.makeText(
                    context,
                    "매일 $str_hour:${str_min}에 오늘 날씨에 대한 정보 알림을 받습니다.",
                    Toast.LENGTH_SHORT
            ).show()

            // 저장하는 코드
            editer.putString("hourSetting", str_hour)
            editer.putString("minSetting", str_min)
            editer.apply()
            editer.commit()

            attachment.summary = "매일 $str_hour:${str_min}에 오늘 날씨에 대한 정보 알림을 받습니다."
            setHour = result_hour
            setMin = result_min
            createNotificationChannel()
            DailyWorkRequeset()
            val dailyWorkRequeset = OneTimeWorkRequestBuilder<SimpleWorker>()
                .setInitialDelay(getTimeUsingInWorkRequest(result_hour, result_min), TimeUnit.MILLISECONDS)
                .addTag("notify_day_by_day")
                .build()
            WorkManager.getInstance(requireContext()).enqueue(dailyWorkRequeset)

//            alarmBroadcastReceiver(result_hour,result_min)
            Log.d("[test]","re h : ${ result_hour}, re m : ${result_min}")
        }
        d.setNegativeButton("취소") { dialogInterface, i -> }
        val alertDialog = d.create()
        alertDialog.show()
    }

    fun DailyWorkRequeset(): Boolean {
        val dailyWorkRequeset = OneTimeWorkRequestBuilder<SimpleWorker>()
            .setInitialDelay(getTimeUsingInWorkRequest(setHour, setMin), TimeUnit.MILLISECONDS)
            .addTag("notify_day_by_day")
            .build()
        WorkManager.getInstance(requireContext()).enqueue(dailyWorkRequeset)
        return true
    }

    fun getTimeUsingInWorkRequest(hour:Int, min:Int): Long {
        val currentDate = Calendar.getInstance()
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
        }
        if (calendar.before(currentDate)) {
            Log.d("[test]","내일 알람")
            calendar.add(Calendar.DATE, 1)
        }
        return calendar.timeInMillis- currentDate.timeInMillis
    }


    //    API26(Oreo)+ notification 작동을 위해서는 channel을 생성해야 함
    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name: CharSequence = "알림설정에서의 제목"
            val description = "Oreo Version 이상을 위한 알림(알림설정에서의 설명)"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val notificationChannel = NotificationChannel("channel_id", name, importance)
            notificationChannel.description = description
            val notificationManager = context?.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(notificationChannel)
            Log.d("[test]","createNotificationChannel")
        }
    }

    companion object {
        var setHour : Int = 0
        var setMin : Int = 0
    }

}