package com.sambas.fagiollogs.core.design.theme

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.loader.Loader

@Immutable
data class DesignAnimations(
    val loader: @Composable () -> Unit,
//    @RawRes val listLoader: Int,
    @RawRes val genericError: Int,
    @RawRes val networkError: Int,
//    @RawRes val success: Int,
//    @RawRes val doneStatus: Int,
)

internal val fagiolsDesignAnimations by lazy {
    DesignAnimations(
        loader = { Loader() },
        genericError = R.raw.generic_error_animation,
        networkError = R.raw.error_network_animation,
//        dialogGenericError = R.raw.cdl_animation_dialog_error_tclub,
//        success = R.raw.cdl_animation_success,
//        doneStatus = R.raw.cdl_done_status_tclub,
    )
}