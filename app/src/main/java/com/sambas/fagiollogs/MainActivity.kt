package com.sambas.fagiollogs

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import com.sambas.fagiollogs.core.design.theme.ComposeDesignTheme
import com.sambas.fagiollogs.domain.model.ThemeTypesEnum
import com.sambas.fagiollogs.domain.navigation.AccessNavHost
import com.sambas.fagiollogs.domain.navigation.MainNavHost
import com.sambas.fagiollogs.domain.navigation.RootNavHost
import com.sambas.fagiollogs.persistant.AppPreferences
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeMode = appPreferences.themeMode.collectAsState().value
            val isDarkTheme = when (themeMode) {
                ThemeTypesEnum.LIGHT -> false
                ThemeTypesEnum.DARK -> true
                ThemeTypesEnum.SYSTEM -> isSystemInDarkTheme()
            }
            ComposeDesignTheme(fullScreen = true, darkTheme = isDarkTheme) {
                RootNavHost(
                    onBackPressed = onBackPressedDispatcher::onBackPressed,
                    onClose = ::finish,
                )
            }
        }
    }
}
