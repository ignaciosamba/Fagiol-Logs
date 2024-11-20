package com.sambas.fagiollogs.core.design.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val RoundedCornerShape = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

internal val BottomSheetShape = RoundedCornerShape(
    topStart = 16.dp,
    topEnd = 16.dp
)