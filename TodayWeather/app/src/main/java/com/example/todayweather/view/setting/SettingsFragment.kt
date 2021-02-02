package com.example.todayweather.view.setting

import android.app.Activity
import android.app.AlarmManager
import android.app.AlertDialog
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.todayweather.R
import com.example.todayweather.push.AlarmReciver
import com.example.todayweather.push.MyFirebaseMessagingService
import java.util.*


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
        val attachment : Preference? = findPreference("attachment")
        val pref : SharedPreferences? = activity?.getSharedPreferences(
                "timeSetting",
                Activity.MODE_PRIVATE
        )
        val timeSetting  = pref?.getBoolean("timeSetting", false)
        val hourSetting  = pref?.getString("hourSetting", null)
        val minSetting  = pref?.getString("minSetting", null)

        val editer : SharedPreferences.Editor = pref!!.edit()

        if (!timeSetting!!){
            editer.putBoolean("timeSetting", true)
            editer.apply()
            editer.commit()

            attachment?.summary = "알림 받을 시간을 설정해주세요."
        }else{
            attachment?.summary = "매일 ${hourSetting.toString()}:${minSetting.toString()}에 오늘 날씨에 대한 정보 알림을 받습니다."
        }
        // 클릭시
        attachment?.setOnPreferenceClickListener {
            numberPickerCustom(attachment, editer)
            true
        }

    }


    fun numberPickerCustom(attachment: Preference, editer: SharedPreferences.Editor) {
        val d = AlertDialog.Builder(context)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.number_picker_dialog, null)
        d.setTitle("알림 시간 설정")
        d.setMessage("30분 단위로 설정 가능합니다.")
        d.setView(dialogView)

        val numberPickerH = dialogView.findViewById<NumberPicker>(R.id.numperPickerH)
        numberPickerH.minValue = 0
        numberPickerH.maxValue = 23
        numberPickerH.value = 7
        val numberPickerM = dialogView.findViewById<NumberPicker>(R.id.numperPickerM)
        numberPickerM.minValue = 0
        numberPickerM.maxValue = 5
        numberPickerM.displayedValues = arrayOf("0", "10", "20", "30", "40", "50")

        d.setPositiveButton("설정") { dialogInterface, i ->
            val result_hour = numberPickerH.value
            val result_min = numberPickerM.value * 10
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
            attachment.summary = "매일 $str_hour:${str_min}에 오늘 날씨에 대한 정보 알림을 받습니다."

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

//            // 푸쉬 알림 채널 만들기
//            Firebase.messaging.subscribeToTopic("weather${str_hour.split(" ")[1]}${result_min}")
//                .addOnCompleteListener { task ->
//                    var msg = getString(R.string.msg_subscribed)
//                    if (!task.isSuccessful) {
//                        msg = getString(R.string.msg_subscribe_failed)
//                    }
//                    Log.d("[test]", msg)
//                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
//                }
            Alarm(context,result_hour,result_min)
        }
        d.setNegativeButton("취소") { dialogInterface, i -> }
        val alertDialog = d.create()
        alertDialog.show()
    }

    private fun Alarm(context: Context?, result_hour: Int, result_min: Int) {
        val am = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReciver::class.java)
        val sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val calendar: Calendar = Calendar.getInstance()

        //알람시간 calendar에 set해주기
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE), result_hour, result_min, 0)
        Log.d("[test]","${calendar.get(Calendar.YEAR)}, ${calendar.get(Calendar.MONTH)}, ${calendar.get(Calendar.DATE)}, $result_hour, $result_min")

        //알람 예약
        am.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, sender)
    }

}
