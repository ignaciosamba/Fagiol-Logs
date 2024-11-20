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
    val contentDark: Color,
    val contentDisabled: Color,
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
    val darkNeutralSecondary: Color,
    val neutralTertiary: Color,
    val darkNeutralTertiary: Color,
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
        darkPrimary = Color(0xFFFFFFFF),
        secondary = Color(0xFFF7EEEC),
        darkSecondary = Color(0xFF211F1F),
        action = Color(0xFFEB6600),
        contentWhite = Color(0xFFFFFFFF),
        contentDark = Color(0xFF151515),
        actionSecondary = Color(0xFFE4B999),
        actionTertiary = Color(0xFF416B96),
        accent = Color(0xFFFF5B26),
        accentSecondary = Color(0xFFFDB581),
        accentTertiary = Color(0xFF5382BE),
        actionPrimaryPressed = Color(0xFFD15B00),
        actionSecondaryPressed = Color(0xFFE55323),
        actionSecondaryPressedDark = Color(0xFFE55323),
        actionTertiaryPressed = Color(0xFF1C4264),
        neutralPrimary = Color(0xFF000000),
        neutralSecondary = Color(0xFF525254),
        darkNeutralSecondary = Color(0xFFEAEAEA),
        neutralTertiary = Color(0xFFA4A4A8),
        darkNeutralTertiary = Color(0xFFA4A4A8),
        neutralQuaternary = Color(0xFFFFC7B4),
        neutralInversePrimary = Color(0xFF71777B),
        neutralInverseSecondary = Color(0xFF707072),
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
        contentDisabled = Color(0xFFBEBEBE)
    )
}

object AppColors {
    val Primary = Color(0xFFEB6600)  // Orange
    val White = Color(0xFFFFFFFF)

    // Derived colors from Primary
    val PrimaryLight = Color(0xFFFF8F33)  // Lighter orange
    val PrimaryDark = Color(0xFFB14600)   // Darker orange
    val PrimaryContainer = Color(0xFFFFDBCC) // Very light orange for containers
    val OnPrimary = White

    // Surface colors
    val Surface = White
    val OnSurface = Color(0xFF1C1B1F)  // Dark gray for text on white
    val SurfaceVariant = Color(0xFFF3F3F3)  // Light gray for subtle backgrounds
}