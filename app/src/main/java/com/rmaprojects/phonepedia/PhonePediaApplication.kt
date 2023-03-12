package com.rmaprojects.phonepedia

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.color.DynamicColors
import com.rmaprojects.core.domain.model.ApplicationThemeMode
import com.rmaprojects.core.domain.model.SettingsPreference
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PhonePediaApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        if (SettingsPreference.isDarkMode == ApplicationThemeMode.IS_DARK_MODE) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            return
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}