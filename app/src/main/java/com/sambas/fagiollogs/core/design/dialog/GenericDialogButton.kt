package com.sambas.fagiollogs.core.design.dialog

import androidx.compose.runtime.Immutable

@Immutable
data class GenericDialogButton(
    val text: String,
    val onClick: () -> Unit
)