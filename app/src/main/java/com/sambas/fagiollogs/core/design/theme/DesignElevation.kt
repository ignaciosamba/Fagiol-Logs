package com.sambas.fagiollogs.core.design.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class DesignElevation(
    val elevation: Dp,
    val smallElevation: Dp
)

val defaultDesignElevation = DesignElevation(
    elevation = 8.dp,
    smallElevation = 4.dp
)