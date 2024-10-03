package com.sambas.fagiollogs.di

import android.app.Application
import com.sambas.fagiollogs.core.design.theme.LocalThemeConfig
import com.sambas.fagiollogs.core.design.theme.fagiolsThemeConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FagiolsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LocalThemeConfig.setGlobalThemeConfig(fagiolsThemeConfig)
    }
}