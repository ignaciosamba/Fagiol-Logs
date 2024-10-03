package com.sambas.fagiollogs.core.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class DesignColors(
    // Background
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val backgroundNavigation: Color,
    val backgroundActionPrimary: Color,
    val backgroundActionInversePrimary: Color,
    val backgroundActionSecondary: Color,
    val backgroundActionInverseSecondary: Color,
    val backgroundActionInverseSecondaryPressed: Color,
    val backgroundDisabled: Color,
    val backgroundDisabledInverse: Color,
    val backgroundInversePrimary: Color,
    val backgroundInverseSecondary: Color,
    val backgroundInverseTertiary: Color,
    val backgroundActionPrimaryPressed: Color,
    val backgroundGlass: Color,
    val backgroundGlassPressed: Color,
    val backgroundDialog: Color,
    val backgroundSuccess: Color,
    val backgroundError: Color,
    val backgroundWarning: Color,
    val backgroundAccentSecondary: Color,
    val backgroundGradientHorizontal: Brush,
    val backgroundGradientTopLeft: Brush,
    val backgroundAccent: Color,
    val backgroundGradientSecondary: Brush,
    val backgroundGradientTertiary: Brush,
    val backgroundGradientQuaternary: Brush,
    val backgroundActionSecondaryPressed: Color,

    // Content
    val contentPrimary: Color,
    val contentSecondary: Color,
    val contentTertiary: Color,
    val contentQuaternary: Color,
    val contentAction: Color,
    val contentError: Color,
    val contentSuccess: Color,
    val contentWarning: Color,
    val contentInversePrimary: Color,
    val contentInverseSecondary: Color,
    val contentAccent: Color,
    val contentProductNext: Color,
    val contentWhite: Color,

    // Border
    val borderDividerPrimary: Color,
    val borderDividerInversePrimary: Color,
    val borderDividerSecondary: Color,
    val borderDividerTertiary: Color,
    val borderDividerQuaternary: Color,
    val borderSelectedPrimary: Color,
    val borderSelectedSecondary: Color,
    val borderGradient: Color,
    val borderError: Color,

    // Gradient
    val gradient1: Color,
    val gradient2: Color,
    val gradient3: Color,
    val gradient4: Color,

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

    val backgroundGradientPromo: Brush,
    val backgroundGradientUpgrade: Brush,

    // Icon
    val iconBold: Color,
    val iconSubtle: Color,
    val iconSubtler: Color,
    val iconInteractive: Color,
    val iconInteractivePressed: Color,
    val iconDisabled: Color,
    val iconSelected: Color,
)

//TODO: fix all the colors.
fun designColorsDark(
    palette: DesignColorPalette
): DesignColors = DesignColors(
    // Background
    backgroundPrimary = palette.darkPrimary,
    backgroundSecondary = palette.darkSecondary,
    backgroundNavigation = palette.darkPrimary,
    backgroundActionPrimary = palette.action,
    backgroundActionInversePrimary = palette.secondary,
    backgroundActionSecondary = palette.darkTertiary,
    backgroundActionInverseSecondary = palette.actionTertiary,
    backgroundActionInverseSecondaryPressed = palette.actionTertiaryPressed,
    backgroundDisabled = palette.darkQuaternary,
    backgroundDisabledInverse = palette.neutralInverseSecondary,
    backgroundInversePrimary = palette.secondary,
    backgroundInverseSecondary = palette.darkSecondary,
    backgroundInverseTertiary = palette.darkPrimary,
    backgroundActionPrimaryPressed = palette.actionPrimaryPressed,
    backgroundGlass = palette.secondary.copy(alpha = 0.2f),
    backgroundGlassPressed = palette.secondary.copy(alpha = 0.75f),
    backgroundDialog = palette.primary.copy(alpha = 0.8f),
    backgroundSuccess = palette.successTertiary,
    backgroundError = palette.errorTertiary,
    backgroundWarning = palette.warningTertiary,
    backgroundAccentSecondary = palette.accentTertiary,
    backgroundGradientHorizontal = Brush.horizontalGradient(
        colors = listOf(
            palette.gradientPrimary1,
            palette.gradientPrimary2,
            palette.gradientPrimary3,
            palette.gradientPrimary4
        )
    ),
    backgroundGradientTopLeft = Brush.linearGradient(
        colors = listOf(
            palette.gradientPrimary1,
            palette.gradientPrimary2,
            palette.gradientPrimary3,
            palette.gradientPrimary4
        ),
        start = Offset(0f, 0f),
        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
    ),
    backgroundAccent = palette.accent,
    backgroundGradientSecondary = Brush.verticalGradient(
        colors = listOf(
            palette.gradientSecondary1,
            palette.gradientSecondary2
        )
    ),
    backgroundGradientTertiary = Brush.linearGradient(
        colors = listOf(
            palette.gradientTertiary1,
            palette.gradientTertiary2
        ),
        start = Offset(Float.POSITIVE_INFINITY, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    ),
    backgroundGradientQuaternary = Brush.verticalGradient(
        colors = listOf(
            palette.gradientQuaternary2,
            palette.gradientQuaternary1
        )
    ),
    backgroundActionSecondaryPressed = palette.actionSecondaryPressedDark,

    // Content
    contentPrimary = palette.secondary,
    contentSecondary = palette.neutralSecondary,
    contentTertiary = palette.neutralPrimary,
    contentQuaternary = palette.neutralInversePrimary,
    contentAction = palette.action,
    contentError = palette.error,
    contentSuccess = palette.success,
    contentWarning = palette.warning,
    contentInversePrimary = palette.primary,
    contentInverseSecondary = palette.secondary,
    contentAccent = palette.accent,
    contentProductNext = palette.brandProduct1,
    contentWhite = palette.contentWhite,

    // Border
    borderDividerPrimary = palette.darkSecondary,
    borderDividerInversePrimary = palette.secondary,
    borderDividerSecondary = palette.darkSecondary,
    borderDividerTertiary = palette.darkSecondary,
    borderDividerQuaternary = palette.neutralInverseSecondary,
    borderSelectedPrimary = palette.action,
    borderSelectedSecondary = palette.secondary,
    borderGradient = Color.Unspecified,
    borderError = palette.error,

    // Gradient
    gradient1 = palette.gradientPrimary1,
    gradient2 = palette.gradientPrimary2,
    gradient3 = palette.gradientPrimary3,
    gradient4 = palette.gradientPrimary4,

    gradientSecondary1 = palette.gradientSecondary1,
    gradientSecondary2 = palette.gradientSecondary2,

    gradientTertiary1 = palette.gradientTertiary1,
    gradientTertiary2 = palette.gradientTertiary2,

    gradientQuaternary1 = palette.gradientQuaternary1,
    gradientQuaternary2 = palette.gradientQuaternary2,

    // New design library
    accentPromoBold = palette.accentPromoBold,
    secondario = palette.secondario,
    giallo80 = palette.giallo80,
    sbarra = palette.sbarra,
    rosa50 = palette.rosa50,
    backgroundGradientUpgrade = Brush.horizontalGradient(
        colors = listOf(
            palette.sbarra,
            palette.rosa50,
        )
    ),
    backgroundGradientPromo = Brush.verticalGradient(
        colors = listOf(
            palette.giallo80,
            palette.secondario,
        )
    ),
    // Icon
    iconBold = palette.accentPromoBold,
    iconSubtle = palette.accentPromoBold,
    iconSubtler = palette.accentPromoBold,
    iconInteractive = palette.accentPromoBold,
    iconInteractivePressed = palette.accentPromoBold,
    iconDisabled = palette.accentPromoBold,
    iconSelected = palette.accentPromoBold,
)

fun designColorsLight(
    palette: DesignColorPalette
): DesignColors = DesignColors(
    // Background
    backgroundPrimary = palette.secondary,
    backgroundSecondary = palette.neutralQuaternary,
    backgroundNavigation = palette.secondary,
    backgroundActionPrimary = palette.action,
    backgroundActionInversePrimary = palette.secondary,
    backgroundActionSecondary = palette.actionSecondary,
    backgroundActionInverseSecondary = palette.actionTertiary,
    backgroundActionInverseSecondaryPressed = palette.actionTertiaryPressed,
    backgroundDisabled = palette.neutralTertiary,
    backgroundDisabledInverse = palette.neutralInverseSecondary,
    backgroundInversePrimary = palette.primary,
    backgroundInverseSecondary = palette.brandPrimary,
    backgroundInverseTertiary = palette.darkPrimary,
    backgroundActionPrimaryPressed = palette.actionPrimaryPressed,
    backgroundGlass = palette.secondary.copy(alpha = 0.2f),
    backgroundGlassPressed = palette.secondary.copy(alpha = 0.75f),
    backgroundDialog = palette.primary.copy(alpha = 0.8f),
    backgroundSuccess = palette.successSecondary,
    backgroundError = palette.errorSecondary,
    backgroundWarning = palette.warningSecondary,
    backgroundAccentSecondary = palette.accentSecondary,
    backgroundGradientHorizontal = Brush.horizontalGradient(
        colors = listOf(
            palette.gradientPrimary1,
            palette.gradientPrimary2,
            palette.gradientPrimary3,
            palette.gradientPrimary4
        )
    ),
    backgroundGradientTopLeft = Brush.linearGradient(
        colors = listOf(
            palette.gradientPrimary1,
            palette.gradientPrimary2,
            palette.gradientPrimary3,
            palette.gradientPrimary4
        ),
        start = Offset(0f, Float.POSITIVE_INFINITY),
        end = Offset(Float.POSITIVE_INFINITY, 0f)
    ),
    backgroundAccent = palette.accent,
    backgroundGradientSecondary = Brush.verticalGradient(
        colors = listOf(
            palette.gradientSecondary1,
            palette.gradientSecondary2
        )
    ),
    backgroundGradientTertiary = Brush.linearGradient(
        colors = listOf(
            palette.gradientTertiary1,
            palette.gradientTertiary2
        ),
        start = Offset(Float.POSITIVE_INFINITY, 0f),
        end = Offset(0f, Float.POSITIVE_INFINITY)
    ),
    backgroundGradientQuaternary = Brush.verticalGradient(
        colors = listOf(
            palette.gradientQuaternary2,
            palette.gradientQuaternary1
        )
    ),
    backgroundActionSecondaryPressed = palette.actionSecondaryPressed,

    // Content
    contentPrimary = palette.primary,
    contentSecondary = palette.neutralPrimary,
    contentTertiary = palette.neutralSecondary,
    contentQuaternary = palette.neutralInversePrimary,
    contentAction = palette.action,
    contentError = palette.error,
    contentSuccess = palette.success,
    contentWarning = palette.warning,
    contentInversePrimary = palette.secondary,
    contentInverseSecondary = palette.secondary,
    contentAccent = palette.accent,
    contentProductNext = palette.brandProduct1,
    contentWhite = palette.contentWhite,

    // Border
    borderDividerPrimary = palette.neutralQuaternary,
    borderDividerInversePrimary = palette.secondary,
    borderDividerSecondary = palette.neutralTertiary,
    borderDividerTertiary = palette.neutralSecondary,
    borderDividerQuaternary = palette.neutralInverseSecondary,
    borderSelectedPrimary = palette.action,
    borderSelectedSecondary = palette.primary,
    borderGradient = Color.Unspecified,
    borderError = palette.error,

    // Gradient
    gradient1 = palette.gradientPrimary1,
    gradient2 = palette.gradientPrimary2,
    gradient3 = palette.gradientPrimary3,
    gradient4 = palette.gradientPrimary4,

    gradientSecondary1 = palette.gradientSecondary1,
    gradientSecondary2 = palette.gradientSecondary2,

    gradientTertiary1 = palette.gradientTertiary1,
    gradientTertiary2 = palette.gradientTertiary2,

    gradientQuaternary1 = palette.gradientQuaternary1,
    gradientQuaternary2 = palette.gradientQuaternary2,

    // New design library
    accentPromoBold = palette.accentPromoBold,
    secondario = palette.secondario,
    giallo80 = palette.giallo80,
    sbarra = palette.sbarra,
    rosa50 = palette.rosa50,
    backgroundGradientUpgrade = Brush.horizontalGradient(
        colors = listOf(
            palette.sbarra,
            palette.rosa50,
        )
    ),
    backgroundGradientPromo = Brush.verticalGradient(
        colors = listOf(
            palette.giallo80,
            palette.secondario,
        )
    ),
    // Icon
    iconBold = palette.accentPromoBold,
    iconSubtle = palette.accentPromoBold,
    iconSubtler = palette.accentPromoBold,
    iconInteractive = palette.accentPromoBold,
    iconInteractivePressed = palette.accentPromoBold,
    iconDisabled = palette.accentPromoBold,
    iconSelected = palette.accentPromoBold,
)