package com.sambas.fagiollogs.core.design.loader

import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import com.sambas.fagiollogs.core.design.scaffold.LoadingState

public enum class ScreenLoadingType {

    /**
     * The loading should be visible and should be blocking.
     */
    Visible,

    /**
     * The loading should be visible, but it can be dismissed.
     */
    VisibleAndCancellable,

    /**
     * There are no ongoing operations that requested the loading animation to be visible.
     */
    None,
}


private const val ANIMATION_BACKGROUND_ALPHA = 0.5f

fun ScreenLoadingType.toLoadingModel(alpha: Float = ANIMATION_BACKGROUND_ALPHA) = when (this) {
    ScreenLoadingType.Visible -> LoadingModel(LoadingState.Loading, alpha)
    ScreenLoadingType.VisibleAndCancellable -> LoadingModel(LoadingState.LoadingCancellable, alpha)
    ScreenLoadingType.None -> LoadingModel.disable
}