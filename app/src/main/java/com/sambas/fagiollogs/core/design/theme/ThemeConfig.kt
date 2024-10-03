package com.sambas.fagiollogs.core.design.theme

import androidx.compose.runtime.Immutable

@Immutable
data class ThemeConfig(
    val colorSystem: DesignColorSystem,
    val animations: DesignAnimations,
)

val fagiolsThemeConfig by lazy {
    ThemeConfig(
        colorSystem = fagiolsColorSystem,
        animations = fagiolsDesignAnimations,
    )
}