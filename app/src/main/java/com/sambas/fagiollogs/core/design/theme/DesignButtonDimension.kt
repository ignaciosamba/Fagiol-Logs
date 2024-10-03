package com.sambas.fagiollogs.core.design.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DesignButtonDimension(
    val buttonFull: Dimension,
    val buttonMedium: Dimension,
    val imageButtonMedium: Dimension,
    val buttonSmall: Dimension,
    val imageButtonSmall: Dimension,
    val circularButtonMedium: Dimension,
    val circularButtonSmall: Dimension,
    val squaredButton: Dimension,
    val squaredImageButton: Dimension
)

val mediumButtonDimension = DesignButtonDimension(
    buttonFull = Dimension(
        width = Dp.Infinity,
        height = 50.dp
    ),
    buttonMedium = Dimension(
        width = 311.dp,
        height = 48.dp
    ),
    imageButtonMedium = Dimension(
        width = 215.dp,
        height = 48.dp
    ),
    buttonSmall = Dimension(
        width = 208.dp,
        height = 40.dp
    ),
    imageButtonSmall = Dimension(
        width = 200.dp,
        height = 40.dp
    ),
    circularButtonMedium = Dimension(
        width = 60.dp,
        height = 60.dp
    ),
    circularButtonSmall = Dimension(
        width = 32.dp,
        height = 32.dp
    ),
    squaredButton = Dimension(
        width = 101.dp,
        height = 32.dp
    ),
    squaredImageButton = Dimension(
        width = 133.dp,
        height = 32.dp
    )
)

val smallButtonDimension = DesignButtonDimension(
    buttonFull = Dimension(
        width = Dp.Infinity,
        height = 50.dp
    ),
    buttonMedium = Dimension(
        width = 311.dp,
        height = 44.dp
    ),
    imageButtonMedium = Dimension(
        width = 215.dp,
        height = 44.dp
    ),
    buttonSmall = Dimension(
        width = 208.dp,
        height = 32.dp
    ),
    imageButtonSmall = Dimension(
        width = 200.dp,
        height = 32.dp
    ),
    circularButtonMedium = Dimension(
        width = 60.dp,
        height = 60.dp
    ),
    circularButtonSmall = Dimension(
        width = 32.dp,
        height = 32.dp
    ),
    squaredButton = Dimension(
        width = 101.dp,
        height = 32.dp
    ),
    squaredImageButton = Dimension(
        width = 133.dp,
        height = 32.dp
    )
)
