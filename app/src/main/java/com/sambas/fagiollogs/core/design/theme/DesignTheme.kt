package com.sambas.fagiollogs.core.design.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.core.view.WindowCompat


val LocalDesignSpacing = staticCompositionLocalOf<DesignSpacing> {
    throw IllegalStateException("No LocalDesignSpacing has been set")
}

val LocalDesignColors = compositionLocalOf<DesignColors> {
    throw IllegalStateException("No LocalDesignColors has been set")
}

val LocalDesignElevation = staticCompositionLocalOf<DesignElevation> {
    throw IllegalStateException("No LocalDesignElevation has been set")
}

val LocalDesignTypography = staticCompositionLocalOf<DesignTypography> {
    throw IllegalStateException("No LocalDesignTypography has been set")
}

val LocalAnimation = staticCompositionLocalOf<DesignAnimations> {
    throw IllegalStateException("No LocalAnimation has been set")
}

val LocalDesignAssetDimen = staticCompositionLocalOf<DesignAssetDimen> {
    throw IllegalStateException("No LocalAnimation has been set")
}

val LocalFullScreenMode = staticCompositionLocalOf<Boolean> {
    throw IllegalStateException("No LocalFullScreenMode has been set")
}

val LocalDesignButtonDimension = staticCompositionLocalOf<DesignButtonDimension> {
    throw IllegalStateException("No LocalFullScreenMode has been set")
}

val buttonDimension: DesignButtonDimension
    @ReadOnlyComposable
    @Composable
    get() = LocalDesignButtonDimension.current

public object DesignTheme {
    val spacing: DesignSpacing
        @ReadOnlyComposable
        @Composable
        get() = LocalDesignSpacing.current

    val elevations: DesignElevation
        @ReadOnlyComposable
        @Composable
        get() = LocalDesignElevation.current

    val colors: DesignColors
        @ReadOnlyComposable
        @Composable
        get() = LocalDesignColors.current

    val typography: DesignTypography
        @ReadOnlyComposable
        @Composable
        get() = LocalDesignTypography.current

    val animation: DesignAnimations
        @ReadOnlyComposable
        @Composable
        get() = LocalAnimation.current

    val assetDimen: DesignAssetDimen
        @ReadOnlyComposable
        @Composable
        get() = LocalDesignAssetDimen.current

    val buttonDimension: DesignButtonDimension
        @ReadOnlyComposable
        @Composable
        get() = LocalDesignButtonDimension.current
}

/**
 * @param fullScreen true if the screen is edge-to-edge and using [WindowCompat.setDecorFitsSystemWindows].
 */
@Composable
fun ComposeDesignTheme(
    fullScreen: Boolean,
    darkTheme: Boolean = false,
    mediumScreenSize: Int = 200,
    content: @Composable () -> Unit
) {
    val themeConfig = LocalThemeConfig.current
    val designColors =
        if (darkTheme) themeConfig.colorSystem.dark else themeConfig.colorSystem.light

    val screenWidth = LocalConfiguration.current.screenWidthDp
    CompositionLocalProvider(
        LocalDesignColors provides designColors,
        LocalAnimation provides themeConfig.animations,
        LocalDesignTypography provides galanoDesignTypography,
        LocalDesignAssetDimen provides if (screenWidth > mediumScreenSize) {
            bigScreenAssetDimen
        } else {
            smallScreenAssetDimen
        },
        LocalDesignSpacing provides if (screenWidth > mediumScreenSize) {
            bigScreenSpacing
        } else {
            smallScreenSpacing
        },
        LocalDesignElevation provides defaultDesignElevation,
        LocalFullScreenMode provides fullScreen,
        LocalDesignButtonDimension provides if (screenWidth > mediumScreenSize) {
            mediumButtonDimension
        } else {
            smallButtonDimension
        },
    ) {
        MaterialTheme(
            content = content,
            colorScheme = MaterialTheme.colorScheme.copy(
                primaryContainer = DesignTheme.colors.contentPrimary,
                secondary = DesignTheme.colors.contentInverseSecondary,
            )
        )
    }
}