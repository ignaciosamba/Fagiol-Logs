package com.sambas.fagiollogs.core.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class DesignAssetDimen(
    val dimen_xxs: Dp,
    val dimen_xs: Dp,
    val dimen_s: Dp,
    val dimen_m: Dp,
    val dimen_l: Dp,
    val dimen_xl: Dp,
    val dimen_xxl: Dp,
    val dimen_super: Dp,
    val dimen_mega: Dp
)

val bigScreenAssetDimen = DesignAssetDimen(
    dimen_xxs = 12.dp,
    dimen_xs = 16.dp,
    dimen_s = 20.dp,
    dimen_m = 24.dp,
    dimen_l = 32.dp,
    dimen_xl = 64.dp,
    dimen_xxl = 80.dp,
    dimen_super = 150.dp,
    dimen_mega = 240.dp
)

val smallScreenAssetDimen = DesignAssetDimen(
    dimen_xxs = 12.dp,
    dimen_xs = 16.dp,
    dimen_s = 20.dp,
    dimen_m = 24.dp,
    dimen_l = 32.dp,
    dimen_xl = 48.dp,
    dimen_xxl = 64.dp,
    dimen_super = 96.dp,
    dimen_mega = 120.dp
)