package com.sambas.fagiollogs.core.design.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

fun Modifier.defaultMinSize(dimension: Dimension): Modifier =
    defaultMinSize(minWidth = dimension.width, minHeight = dimension.height)

/**
 * This extension is handy for setting the size of a composable by directly
 * passing an instance of Dimension. In case the width or height value is equal to Dp.Infinity,
 * the corresponding size will adapt to the maximum size of the parent.
 * */
@SuppressLint("SuspiciousModifierThen")
fun Modifier.size(
    dimension: Dimension
): Modifier = then(
    if (dimension.width == Dp.Infinity) {
        fillMaxWidth()
    } else {
        width(dimension.width)
    }
).then(
    if (dimension.height == Dp.Infinity) {
        fillMaxHeight()
    } else {
        height(dimension.height)
    }
)