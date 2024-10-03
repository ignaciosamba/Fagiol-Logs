package com.sambas.fagiollogs.core.design.theme

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun RowScope.SpacerMini() = Gap(DesignTheme.spacing.space_mini)

@Composable
fun ColumnScope.SpacerMini() = Gap(DesignTheme.spacing.space_mini)

@Composable
fun LazyItemScope.SpacerMini() = Gap(DesignTheme.spacing.space_mini)

@Composable
fun RowScope.SpacerXXXS() = Gap(DesignTheme.spacing.space_xxxs)

@Composable
fun ColumnScope.SpacerXXXS() = Gap(DesignTheme.spacing.space_xxxs)

@Composable
fun LazyItemScope.SpacerXXXS() = Gap(DesignTheme.spacing.space_xxxs)

@Composable
fun RowScope.SpacerXXS() = Gap(DesignTheme.spacing.space_xxs)

@Composable
fun ColumnScope.SpacerXXS() = Gap(DesignTheme.spacing.space_xxs)

@Composable
fun LazyItemScope.SpacerXXS() = Gap(DesignTheme.spacing.space_xxs)

@Composable
fun RowScope.SpacerXS() = Gap(DesignTheme.spacing.space_xs)

@Composable
fun ColumnScope.SpacerXS() = Gap(DesignTheme.spacing.space_xs)

@Composable
fun LazyItemScope.SpacerXS() = Gap(DesignTheme.spacing.space_xs)

@Composable
fun RowScope.SpacerS() = Gap(DesignTheme.spacing.space_s)

@Composable
fun ColumnScope.SpacerS() = Gap(DesignTheme.spacing.space_s)

@Composable
fun LazyItemScope.SpacerS() = Gap(DesignTheme.spacing.space_s)

@Composable
fun RowScope.SpacerM() = Gap(DesignTheme.spacing.space_m)

@Composable
fun ColumnScope.SpacerM() = Gap(DesignTheme.spacing.space_m)

@Composable
fun LazyItemScope.SpacerM() = Gap(DesignTheme.spacing.space_m)

@Composable
fun RowScope.SpacerL() = Gap(DesignTheme.spacing.space_l)

@Composable
fun ColumnScope.SpacerL() = Gap(DesignTheme.spacing.space_l)

@Composable
fun LazyItemScope.SpacerL() = Gap(DesignTheme.spacing.space_l)

@Composable
fun RowScope.SpacerXL() = Gap(DesignTheme.spacing.space_xl)

@Composable
fun ColumnScope.SpacerXL() = Gap(DesignTheme.spacing.space_xl)

@Composable
fun LazyItemScope.SpacerXL() = Gap(DesignTheme.spacing.space_xl)

@Composable
fun RowScope.SpacerXXL() = Gap(DesignTheme.spacing.space_xxl)

@Composable
fun ColumnScope.SpacerXXL() = Gap(DesignTheme.spacing.space_xxl)

@Composable
fun LazyItemScope.SpacerXXL() = Gap(DesignTheme.spacing.space_xxl)

@Composable
fun RowScope.SpacerSuper() = Gap(DesignTheme.spacing.space_super)

@Composable
fun ColumnScope.SpacerSuper() = Gap(DesignTheme.spacing.space_super)

@Composable
fun LazyItemScope.SpacerSuper() = Gap(DesignTheme.spacing.space_super)


@Composable
fun RowScope.Gap(width: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.width(width))
}

@Composable
fun ColumnScope.Gap(height: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.height(height))
}

@Composable
fun LazyItemScope.Gap(padding: Dp, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.size(padding))
}