package com.sambas.fagiollogs.core.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class DesignColorPalette(
    // General:
    val primary: Color,
    val secondary: Color,
    val action: Color,
    val contentWhite: Color,
    val actionSecondary: Color,
    val actionTertiary: Color,
    val accent: Color,
    val accentSecondary: Color,
    val accentTertiary: Color,
    val actionPrimaryPressed: Color,
    val actionSecondaryPressed: Color,
    val actionSecondaryPressedDark: Color,
    val actionTertiaryPressed: Color,

    // Neutrals:
    val neutralPrimary: Color,
    val neutralSecondary: Color,
    val neutralTertiary: Color,
    val neutralQuaternary: Color,
    val neutralInversePrimary: Color,
    val neutralInverseSecondary: Color,
    val darkPrimary: Color,
    val darkSecondary: Color,
    val darkTertiary: Color,
    val darkQuaternary: Color,

    // Status:
    val success: Color,
    val error: Color,
    val warning: Color,
    val successSecondary: Color,
    val errorSecondary: Color,
    val warningSecondary: Color,
    val successTertiary: Color,
    val errorTertiary: Color,
    val warningTertiary: Color,

    // Brand:
    val brandPrimary: Color,
    val brandSecondary: Color,
    val brandProduct1: Color,

    // Gradient
    val gradientPrimary1: Color,
    val gradientPrimary2: Color,
    val gradientPrimary3: Color,
    val gradientPrimary4: Color,

    val gradientSecondary1: Color,
    val gradientSecondary2: Color,

    val gradientTertiary1: Color,
    val gradientTertiary2: Color,

    val gradientQuaternary1: Color,
    val gradientQuaternary2: Color,

    // New design library
    val accentPromoBold: Color,
    val secondario: Color,
    val giallo80: Color,
    val sbarra: Color,
    val rosa50: Color,

    )

internal val fagiolsColorPalette by lazy {
    DesignColorPalette(
        primary = Color(0xFF090909),
        secondary = Color(0xFFF7EEEC),
        action = Color(0xFFEB6600),
        contentWhite = Color(0xFFFFFFFF),
        actionSecondary = Color(0xFFE4B999),
        actionTertiary = Color(0xFF345676),
        accent = Color(0xFF00B828),
        accentSecondary = Color(0xFFCCF1D4),
        accentTertiary = Color(0xFF002E0A),
        actionPrimaryPressed = Color(0xFFD15B00),
        actionSecondaryPressed = Color(0xFF9DC9F8),
        actionSecondaryPressedDark = Color(0xFF16293C),
        actionTertiaryPressed = Color(0xFF1C4264),
        neutralPrimary = Color(0xFF000000),
        neutralSecondary = Color(0xFF000000),
        neutralTertiary = Color(0xFFE8ECEF),
        neutralQuaternary = Color(0xFFFFC7B4),
        neutralInversePrimary = Color(0xFF71777B),
        neutralInverseSecondary = Color(0xFF9FA4A8),
        darkPrimary = Color(0xFF18242E),
        darkSecondary = Color(0xFF000000),
        darkTertiary = Color(0xFF133354),
        darkQuaternary = Color(0xFF3D4448),
        success = Color(0xFF00CD51),
        error = Color(0xFFEE0125),
        warning = Color(0xFFFEA31A),
        successSecondary = Color(0xFFD6FFE7),
        errorSecondary = Color(0xFFFDD8DE),
        warningSecondary = Color(0xFFFFEFD7),
        successTertiary = Color(0xFF003D18),
        errorTertiary = Color(0xFF3A030C),
        warningTertiary = Color(0xFF3D2501),
        brandPrimary = Color(0xFF0057B7),
        brandSecondary = Color(0xFFFEE01A),
        brandProduct1 = Color(0xFFFF6900),
        gradientPrimary1 = Color(0xFF003B7A),
        gradientPrimary2 = Color(0xFF0057B7),
        gradientPrimary3 = Color(0xFF0071EB),
        gradientPrimary4 = Color(0xFF10A9FF),
        gradientSecondary1 = Color(0xFF760124),
        gradientSecondary2 = Color(0xFFFE641D),
        gradientTertiary1 = Color(0xFF1AA6BD),
        gradientTertiary2 = Color(0xFF2E58AA),
        gradientQuaternary1 = Color(0xFF4096F4),
        gradientQuaternary2 = Color(0xFF1A0E5A),
        accentPromoBold = Color(0xFFFEE01A),
        secondario = Color(0xFFFEE01A),
        giallo80 = Color(0xFFFEEC78),
        sbarra = Color(0xFF1D9EFB),
        rosa50 = Color(0xFFF431ED),
    )
}