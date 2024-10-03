package com.sambas.fagiollogs.core.design.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.core.design.theme.DesignTheme


object DesignSquaredImageButtons {
    val primary = DesignSquaredImageButtonTypes { primaryButtonColors() }

    val secondary = DesignSquaredImageButtonTypes { secondaryButtonColors() }

    val ghost = DesignSquaredImageButtonTypes { ghostButtonColors() }

    val inversePrimary = DesignSquaredImageButtonTypes { inversePrimaryButtonColors() }

    val inverseSecondary = DesignSquaredImageButtonTypes { inverseSecondaryButtonColors() }

    val inverseGhost = DesignSquaredImageButtonTypes { inverseGhostButtonColors() }

    val glass = DesignSquaredImageButtonTypes { glassButtonColors() }
}

class DesignSquaredImageButtonTypes(
    private val designButtonColorsProvider: @Composable () -> DesignButtonColors
) {
    @Composable
    fun Medium(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        painter: Painter,
        contentPadding: PaddingValues = PaddingValues(0.dp),
        iconDimen: Dp = DesignTheme.assetDimen.dimen_xs,
        onClick: () -> Unit
    ) = SquaredImageButtonMedium(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        text = text,
        contentPadding = contentPadding,
        iconDimen = iconDimen,
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
        contentPadding: PaddingValues = PaddingValues(0.dp),
        onClick: () -> Unit
    ) = SquaredImageButton(
        modifier = modifier
            .width(width)
            .height(height),
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        painter = painter,
        text = text,
        contentPadding = contentPadding,
        onClick = onClick
    )
}
