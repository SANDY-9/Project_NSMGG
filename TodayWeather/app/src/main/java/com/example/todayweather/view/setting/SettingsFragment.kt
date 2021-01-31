package com.example.todayweather.view.setting

import android.app.Activity
import android.app.AlertDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.todayweather.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
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

        attachment?.setOnPreferenceClickListener {
            numberPickerCustom(attachment, editer)

//            val time = Calendar.getInstance()
//            // 현재시간
//            val hour = time.get(Calendar.HOUR)
//            val minute = time.get(Calendar.MINUTE)
//
//            val timeListener =
//                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
//                    var str_hour = "오후 ${hourOfDay-12}"
//                    var str_min = "$minute"
//
//                    if (hourOfDay<13){
//                        str_hour="오전 $hourOfDay"
//                        if (hourOfDay<10){
//                            str_hour = "오전 0$hourOfDay"
//                        }
//                    }
//
//                    if (minute < 10){
//                        str_min = "0$minute"
//                    }
//                    attachment.summary = "매일 $str_hour:${str_min}에 오늘 날씨에 대한 정보 알림을 받습니다."
//                    Toast.makeText(
//                        context,
//                        "매일 $hourOfDay:${minute}에 오늘 날씨에 대한 정보 알림을 받습니다.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    // 저장하는 코드
//                    editer.putString("hourSetting", str_hour)
//                    editer.putString("minSetting", str_min)
//                    editer.apply()
//                    editer.commit()
//
//                    // 푸쉬 알림 채널 만들기
//                    Firebase.messaging.subscribeToTopic("weather${str_hour.split(" ")[1]}${minute}")
//                        .addOnCompleteListener { task ->
//                            var msg = getString(R.string.msg_subscribed)
//                            if (!task.isSuccessful) {
//                                msg = getString(R.string.msg_subscribe_failed)
//                            }
//                            Log.d("[test]", msg)
//                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
//                    }
//            }
//
//            val builder = TimePickerDialog(context, timeListener, hour, minute, false)
//            builder.show()
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

        val timePicker = dialogView.findViewById<TimePicker>(R.id.timepicker)
        timePicker.setIs24HourView(true) // 24시간 단위
        d.setPositiveButton("설정") { dialogInterface, i ->
            val result_hour = timePicker.hour
            val result_min = timePicker.minute
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

            // 푸쉬 알림 채널 만들기
            Firebase.messaging.subscribeToTopic("weather${str_hour.split(" ")[1]}${result_min}")
                .addOnCompleteListener { task ->
                    var msg = getString(R.string.msg_subscribed)
                    if (!task.isSuccessful) {
                        msg = getString(R.string.msg_subscribe_failed)
                    }
                    Log.d("[test]", msg)
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }


        }
        d.setNegativeButton("취소") { dialogInterface, i -> }
        val alertDialog = d.create()
        alertDialog.show()
    }

}
