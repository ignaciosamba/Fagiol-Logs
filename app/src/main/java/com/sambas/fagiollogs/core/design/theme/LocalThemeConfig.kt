package com.sambas.fagiollogs.core.design.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf

object LocalThemeConfig {

    /**
     * The [CompositionLocal] to update the current [ThemeConfig] locally.
     * This takes precedence over [globalThemeConfig].
     */
    private val LocalThemeConfig = compositionLocalOf<ThemeConfig?> { null }

    /**
     * The global [ThemeConfig] set with [setGlobalThemeConfig].
     */
    private val globalThemeConfig = mutableStateOf<ThemeConfig?>(null)

    /**
     * Set the global [ThemeConfig].
     *
     * Calling this will change the current [ThemeConfig], unless a value has been set with
     * [CompositionLocalProvider].
     *
     * The global [ThemeConfig] allows to update the UI of modules that are not part of the app,
     * which sets the theme depending on the user currently logged in.
     */
    fun setGlobalThemeConfig(themeConfig: ThemeConfig) {
        globalThemeConfig.value = themeConfig
    }

    /**
     * Get the global [ThemeConfig].
     *
     * Prefer using [current] and use this as last resort.
     */
    fun getGlobalThemeConfig() = globalThemeConfig.value

    /**
     * Return the current [ThemeConfig] if one has been set with [CompositionLocalProvider],
     * or the last value set with [setGlobalThemeConfig].
     *
     * This throws [IllegalStateException] if no value is available.
     */
    val current: ThemeConfig
        @Composable
        get() = LocalThemeConfig.current
            ?: globalThemeConfig.value
            ?: throw IllegalStateException("No LocalThemeConfig available")

    /**
     * Associates a [LocalThemeConfig] key to a value in a call to [CompositionLocalProvider].
     */
    infix fun provides(themeConfig: ThemeConfig): ProvidedValue<ThemeConfig?> {
        return LocalThemeConfig.provides(themeConfig)
    }
}