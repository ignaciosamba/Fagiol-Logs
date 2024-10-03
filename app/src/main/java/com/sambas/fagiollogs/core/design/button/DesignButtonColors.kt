package com.sambas.fagiollogs.core.design.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import com.sambas.fagiollogs.core.design.theme.DesignTheme

@Immutable
data class DesignButtonColors(
    val backgroundColor: Color,
    val pressedBackgroundColor: Color,
    val contentColor: Color,
    val disabledBackgroundColor: Color,
    val disabledContentColor: Color,
    val borderStroke: BorderStroke?,
    val disableBorderStroke: BorderStroke?
) {
    companion object {
        @Composable
        fun colors(
            backgroundColor: Color = MaterialTheme.colorScheme.primary,
            contentColor: Color = contentColorFor(backgroundColor),
            disabledBackgroundColor: Color = MaterialTheme.colorScheme.onSurface
                .copy(alpha = 0.12f)
                .compositeOver(MaterialTheme.colorScheme.surface),
            disabledContentColor: Color = MaterialTheme.colorScheme.onSurface
                .copy(alpha = ContentAlpha.disabled),
            pressedBackground: Color = backgroundColor,
            borderStroke: BorderStroke? = null,
            disableBorderStroke: BorderStroke? = null
        ): DesignButtonColors = DesignButtonColors(
            backgroundColor = backgroundColor,
            pressedBackgroundColor = pressedBackground,
            contentColor = contentColor,
            disabledBackgroundColor = disabledBackgroundColor,
            disabledContentColor = disabledContentColor,
            borderStroke = borderStroke,
            disableBorderStroke = disableBorderStroke
        )
    }
}

@Composable
fun primaryButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = DesignTheme.colors.backgroundActionPrimary,
    pressedBackground = DesignTheme.colors.backgroundActionPrimaryPressed,
    disabledBackgroundColor = DesignTheme.colors.backgroundDisabled,
    contentColor = DesignTheme.colors.contentInverseSecondary,
    disabledContentColor = DesignTheme.colors.contentSecondary
)

@Composable
fun inversePrimaryButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = DesignTheme.colors.backgroundActionInversePrimary,
    pressedBackground = DesignTheme.colors.backgroundActionSecondaryPressed,
    disabledBackgroundColor = DesignTheme.colors.backgroundDisabledInverse,
    contentColor = DesignTheme.colors.contentAction,
    disabledContentColor = DesignTheme.colors.contentQuaternary
)

@Composable
fun secondaryButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = DesignTheme.colors.backgroundActionSecondary,
    pressedBackground = DesignTheme.colors.backgroundActionSecondaryPressed,
    disabledBackgroundColor = DesignTheme.colors.backgroundDisabled,
    contentColor = DesignTheme.colors.contentAction,
    disabledContentColor = DesignTheme.colors.contentSecondary
)

@Composable
fun inverseSecondaryButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = DesignTheme.colors.backgroundActionInverseSecondary,
    pressedBackground = DesignTheme.colors.backgroundActionInverseSecondaryPressed,
    disabledBackgroundColor = DesignTheme.colors.backgroundDisabledInverse,
    contentColor = DesignTheme.colors.contentInversePrimary,
    disabledContentColor = DesignTheme.colors.contentQuaternary
)

@Composable
fun ghostButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = Color.Transparent,
    pressedBackground = DesignTheme.colors.backgroundActionSecondary,
    disabledBackgroundColor = Color.Transparent,
    contentColor = DesignTheme.colors.contentAction,
    disabledContentColor = DesignTheme.colors.contentSecondary,
    borderStroke = BorderStroke(
        width = 1.dp,
        color = DesignTheme.colors.borderSelectedPrimary
    ),
    disableBorderStroke = BorderStroke(
        width = 1.dp,
        color = DesignTheme.colors.borderDividerTertiary
    )
)

@Composable
fun inverseGhostButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = Color.Transparent,
    pressedBackground = DesignTheme.colors.backgroundGlassPressed,
    disabledBackgroundColor = Color.Transparent,
    contentColor = DesignTheme.colors.contentInversePrimary,
    disabledContentColor = DesignTheme.colors.contentSecondary,
    borderStroke = BorderStroke(
        width = 2.dp,
        color = DesignTheme.colors.borderDividerInversePrimary
    ),
    disableBorderStroke = BorderStroke(
        width = 2.dp,
        color = DesignTheme.colors.borderDividerTertiary
    )
)

@Composable
fun glassButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = DesignTheme.colors.backgroundGlass,
    pressedBackground = DesignTheme.colors.backgroundGlassPressed,
    disabledBackgroundColor = DesignTheme.colors.backgroundDisabledInverse,
    contentColor = DesignTheme.colors.contentInversePrimary,
    disabledContentColor = DesignTheme.colors.contentQuaternary
)

@Composable
fun textButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = Color.Transparent,
    pressedBackground = Color.Transparent,
    disabledBackgroundColor = Color.Transparent,
    contentColor = DesignTheme.colors.contentAction,
    disabledContentColor = DesignTheme.colors.contentQuaternary
)

@Composable
fun inverseTextButtonColors(): DesignButtonColors = DesignButtonColors.colors(
    backgroundColor = Color.Transparent,
    pressedBackground = Color.Transparent,
    disabledBackgroundColor = Color.Transparent,
    contentColor = DesignTheme.colors.contentInversePrimary,
    disabledContentColor = Color(0xFF5D6468) // TODO REPLACE WITH DesignTheme.colors.textInverseDisabled
)
