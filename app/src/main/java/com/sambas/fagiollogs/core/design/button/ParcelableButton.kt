package com.sambas.fagiollogs.core.design.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalUseFallbackRippleImplementation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PressableButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = null,
    shape: Shape = MaterialTheme.shapes.small,
    colors: DesignButtonColors,
    contentPadding: PaddingValues = DesignButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    CompositionLocalProvider(
        LocalUseFallbackRippleImplementation provides true
    ) {
        androidx.compose.material3.Button(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            interactionSource = interactionSource,
            elevation = elevation,
            shape = shape,
            border = if (enabled) {
                colors.borderStroke
            } else {
                colors.disableBorderStroke
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isPressed) {
                    colors.pressedBackgroundColor
                } else {
                    colors.backgroundColor
                },
                contentColor = colors.contentColor,
                disabledContentColor = colors.disabledContentColor,
                disabledContainerColor = colors.disabledBackgroundColor
            ),
            contentPadding = contentPadding,
            content = content
        )
    }
}