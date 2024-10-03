package com.sambas.fagiollogs.core.design.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

object DesignCircularButtons {
    val primary = DesignCircularButtonTypes { primaryButtonColors() }

    val secondary = DesignCircularButtonTypes { secondaryButtonColors() }

    val inversePrimary = DesignCircularButtonTypes { inversePrimaryButtonColors() }

    val inverseSecondary = DesignCircularButtonTypes { inverseSecondaryButtonColors() }
}

class DesignCircularButtonTypes(
    private val designButtonColorsProvider: @Composable () -> DesignButtonColors
) {
    @Composable
    fun Small(
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        painter: Painter,
        onClick: () -> Unit
    ) = CircularButtonSmall(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        onClick = onClick
    )

    @Composable
    fun Medium(
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        painter: Painter,
        onClick: () -> Unit
    ) = CircularButtonMedium(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        onClick = onClick
    )

    @Composable
    fun Button(
        modifier: Modifier = Modifier,
        width: Dp,
        height: Dp,
        enabled: Boolean = true,
        painter: Painter,
        onClick: () -> Unit
    ) = CircularButton(
        modifier = modifier
            .width(width)
            .height(height),
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        onClick = onClick
    )
}