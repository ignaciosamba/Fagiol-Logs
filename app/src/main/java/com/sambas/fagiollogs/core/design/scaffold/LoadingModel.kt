package com.sambas.fagiollogs.core.design.scaffold

import androidx.annotation.FloatRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

@Immutable
data class LoadingModel(
    val state: LoadingState,
    @FloatRange(from = 0.0, to = 1.0) val alpha: Float = 1f,
    val type: LoadingType = LoadingType.Default,
) {
    companion object {
        val disable = LoadingModel(state = LoadingState.None)
    }
}

@Immutable
enum class LoadingState {
    Loading,
    LoadingCancellable,
    None,
}

@Immutable
sealed interface LoadingType {
    @Immutable
    data object Default : LoadingType

    @Immutable
    data class Custom(val content: @Composable () -> Unit) : LoadingType
}