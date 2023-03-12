package com.rmaprojects.phonepedia.presentation.settings

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.core.domain.model.ApplicationThemeMode
import com.rmaprojects.core.domain.model.SettingsPreference
import com.rmaprojects.phonepedia.R
import com.rmaprojects.phonepedia.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment: Fragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.switchDarkMode.setOnCheckedChangeListener { buttonView, _ ->
            if (buttonView.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SettingsPreference.isDarkMode = ApplicationThemeMode.IS_DARK_MODE
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SettingsPreference.isDarkMode = ApplicationThemeMode.IS_LIGHT_MODE
            }
        }

        binding.switchDarkMode.isChecked = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES
    }
}