package com.sambas.fagiollogs.domain.ui.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sambas.fagiollogs.R

internal enum class SettingsOptions(
    @StringRes val text: Int,
    @DrawableRes val icon: Int,
    val toggleType: Boolean
){
    LANGUAGE(text = R.string.language_option, icon = R.drawable.ic_language, toggleType = false),
    THEME(text = R.string.theme_option, icon = R.drawable.ic_moon, toggleType = false),
    NOTIFICATION(text = R.string.notification_option, icon = R.drawable.ic_notification, toggleType = true),
    METRIC_SYSTEM(text = R.string.metric_option, icon = R.drawable.ic_units_settings, toggleType = true),
}