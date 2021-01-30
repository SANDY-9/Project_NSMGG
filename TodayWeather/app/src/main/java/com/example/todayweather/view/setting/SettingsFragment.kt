package com.example.todayweather.view.setting

import android.content.Intent
import android.content.Intent.ACTION_SENDTO
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.todayweather.R
import com.example.todayweather.view.main.SplashActivity

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val peedback : Preference? = findPreference("peedback")
        peedback?.setOnPreferenceClickListener {
            val intent=Intent(ACTION_SENDTO, Uri.parse("mailto:aoqnwnd@naver.com"))
            activity?.startActivity(intent)
            true
        }
        val version : Preference? = findPreference("version")
        // 버전 이름 함수로 가져오기
        val packageManager = activity?.packageManager
        version?.summary = packageManager?.getPackageInfo(context?.packageName.toString(), PackageManager.GET_ACTIVITIES)?.versionName
    }


}
