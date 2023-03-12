package com.rmaprojects.core.domain.model

import com.chibatching.kotpref.KotprefModel
import com.chibatching.kotpref.enumpref.enumOrdinalPref

object SettingsPreference: KotprefModel() {
    var isDarkMode by enumOrdinalPref(ApplicationThemeMode.IS_LIGHT_MODE, "isDarkMode")
}

enum class ApplicationThemeMode {
    IS_DARK_MODE,
    IS_LIGHT_MODE
}