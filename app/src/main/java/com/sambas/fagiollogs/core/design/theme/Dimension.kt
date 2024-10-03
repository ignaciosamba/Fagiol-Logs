package com.sambas.fagiollogs.core.design.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimension(
    val width: Dp = 0.dp,
    val height: Dp = 0.dp
) {
    constructor(size: Dp) : this(width = size, height = size)
}