package com.sambas.fagiollogs.core.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class DesignSpacing(
    val space_mini: Dp,
    val space_xxxs: Dp,
    val space_xxs: Dp,
    val space_xs: Dp,
    val space_s: Dp,
    val space_m: Dp,
    val space_l: Dp,
    val space_xl: Dp,
    val space_xxl: Dp,
    val space_super: Dp
)

val bigScreenSpacing = DesignSpacing(
    space_mini = 2.dp,
    space_xxxs = 4.dp,
    space_xxs = 8.dp,
    space_xs = 16.dp,
    space_s = 24.dp,
    space_m = 32.dp,
    space_l = 48.dp,
    space_xl = 64.dp,
    space_xxl = 96.dp,
    space_super = 128.dp
)

val smallScreenSpacing = DesignSpacing(
    space_mini = 2.dp,
    space_xxxs = 4.dp,
    space_xxs = 8.dp,
    space_xs = 16.dp,
    space_s = 20.dp,
    space_m = 24.dp,
    space_l = 32.dp,
    space_xl = 48.dp,
    space_xxl = 64.dp,
    space_super = 96.dp
)
