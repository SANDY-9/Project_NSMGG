package com.example.todayweather.view.setting

import android.app.TimePickerDialog
import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.TimePicker
import androidx.preference.DialogPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.todayweather.R
import com.example.todayweather.view.main.SplashActivity
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
        version?.summary = packageManager?.getPackageInfo(context?.packageName.toString(), PackageManager.GET_ACTIVITIES)?.versionName

        // 시간 수정 버튼 누를 때 dialog띄우기
        val attachment : Preference? = findPreference("attachment")
        attachment?.setOnPreferenceClickListener {
            val time = Calendar.getInstance()
            val hour = time.get(Calendar.HOUR)
            val minute = time.get(Calendar.MINUTE)

            val timeListener =
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    var str_hour = "오후"
                    var str_min = ""

                    if (hourOfDay<13){
                        str_hour="오전"
                    }
                    if (hourOfDay<10){
                        str_hour += " 0$hourOfDay"
                    }
                    if (minute < 10){
                        str_min = "0$minute"
                    }
                    attachment.summary = "매일 $str_hour:${str_min}에 오늘 날씨에 대한 정보 알림을 받습니다."
                }

            val builder = TimePickerDialog(context, timeListener, hour, minute, false)
            builder.show()
            true
        }
    }


}
