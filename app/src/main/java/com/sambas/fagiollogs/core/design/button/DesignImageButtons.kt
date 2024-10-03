package com.sambas.fagiollogs.core.design.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object DesignImageButtons {
    val primary = DesignImageButtonTypes { primaryButtonColors() }

    val secondary = DesignImageButtonTypes { secondaryButtonColors() }

    val ghost: DesignImageButtonTypes = DesignImageButtonTypes { ghostButtonColors() }

    val inversePrimary = DesignImageButtonTypes { inversePrimaryButtonColors() }

    val inverseSecondary = DesignImageButtonTypes { inverseSecondaryButtonColors() }

    val inverseGhost = DesignImageButtonTypes { inverseGhostButtonColors() }

    val inverseText: DesignImageButtonTypes = DesignImageButtonTypes { inverseTextButtonColors() }

    val glass: DesignImageButtonTypes = DesignImageButtonTypes { glassButtonColors() }

    val text: DesignImageButtonTypes = DesignImageButtonTypes { textButtonColors() }
}

class DesignImageButtonTypes(
    private val designButtonColorsProvider: @Composable () -> DesignButtonColors
) {
    @Composable
    fun Small(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        painter: Painter,
        onClick: () -> Unit
    ) = ImageButtonSmall(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        text = text,
        onClick = onClick
    )

    @Composable
    fun Medium(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        painter: Painter,
        onClick: () -> Unit
    ) = ImageButtonMedium(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        text = text,
        onClick = onClick
    )

    @Composable
    fun Full(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        painter: Painter,
        onClick: () -> Unit
    ) = ImageButtonFull(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        text = text,
        onClick = onClick
    )

    @Composable
    fun Button(
        modifier: Modifier = Modifier,
        width: Dp,
        height: Dp,
        text: String,
        enabled: Boolean = true,
        painter: Painter,
        iconDimen: Dp = 24.dp,
        onClick: () -> Unit
    ) = ImageButton(
        modifier = modifier
            .width(width)
            .height(height),
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        text = text,
        iconDimen = iconDimen,
        onClick = onClick,
    )
}
