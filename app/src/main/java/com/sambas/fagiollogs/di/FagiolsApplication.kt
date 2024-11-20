package com.sambas.fagiollogs.di

import android.app.Application
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.design.theme.LocalThemeConfig
import com.sambas.fagiollogs.core.design.theme.fagiolsThemeConfig
import com.sambas.fagiollogs.persistant.AppPreferences
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FagiolsApplication : Application() {
    @Inject
    lateinit var authManager: AuthManager
    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate() {
        super.onCreate()
        LocalThemeConfig.setGlobalThemeConfig(fagiolsThemeConfig)
    }

    override fun onTerminate() {
        super.onTerminate()
        authManager.cleanup()
    }
}