package com.sambas.fagiollogs.persistant

import android.content.Context
import com.sambas.fagiollogs.domain.model.ThemeTypesEnum
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences @Inject constructor(@ApplicationContext private val context: Context) {
    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    private val themeKey = "theme_mode"

    private val _themeMode = MutableStateFlow(ThemeTypesEnum.valueOf(
        prefs.getString(themeKey, ThemeTypesEnum.SYSTEM.name) ?: ThemeTypesEnum.SYSTEM.name
    ))
    val themeMode: StateFlow<ThemeTypesEnum> = _themeMode.asStateFlow()

    var themeModeValue: ThemeTypesEnum
        get() = _themeMode.value
        set(value) {
            prefs.edit().putString(themeKey, value.name).apply()
            _themeMode.value = value
        }

    private fun getString(key: String, default: String): String =
        prefs.getString(key, default) ?: default

    private fun putString(key: String, value: String) =
        prefs.edit().putString(key, value).apply()

}