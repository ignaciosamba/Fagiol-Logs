package com.sambas.fagiollogs.core.design.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object DesignSquaredButtons {
    val primary = DesignSquaredButtonTypes { primaryButtonColors() }

    val secondary = DesignSquaredButtonTypes { secondaryButtonColors() }

    val ghost = DesignSquaredButtonTypes { ghostButtonColors() }

    val inversePrimary = DesignSquaredButtonTypes { inversePrimaryButtonColors() }

    val inverseSecondary = DesignSquaredButtonTypes { inverseSecondaryButtonColors() }

    val inverseGhost = DesignSquaredButtonTypes { inverseGhostButtonColors() }

    val glass = DesignSquaredButtonTypes { glassButtonColors() }
}

class DesignSquaredButtonTypes(
    private val designButtonColorsProvider: @Composable () -> DesignButtonColors
) {
    @Composable
    fun Medium(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        contentPadding: PaddingValues = PaddingValues(0.dp),
        onClick: () -> Unit
    ) = SquaredButtonMedium(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        text = text,
        contentPadding = contentPadding,
        onClick = onClick
    )

    @Composable
    fun Button(
        modifier: Modifier = Modifier,
        width: Dp,
        height: Dp,
        text: String,
        enabled: Boolean = true,
        contentPadding: PaddingValues = PaddingValues(0.dp),
        onClick: () -> Unit
    ) = SquaredButton(
        modifier = modifier
            .width(width)
            .height(height),
        colors = designButtonColorsProvider(),
        enabled = enabled,
        contentPadding = contentPadding,
        interactionSource = remember { MutableInteractionSource() },
        text = text,
        onClick = onClick
    )
}
