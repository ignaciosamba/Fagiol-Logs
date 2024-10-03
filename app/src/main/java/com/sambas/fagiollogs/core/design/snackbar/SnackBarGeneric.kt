package com.sambas.fagiollogs.core.design.snackbar

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Immutable

/**
 * A generic Snackbar.
 *
 * This uses a builder to make it possible to the UI and cleanup code separately, allowing for
 * example to define the UI from a Composable and the state management from a ViewModel.
 *
 * ```
 * // Create a builder to define the UI.
 * val snackbarBuilder = SnackBarGeneric.Builder("Some message")
 *
 * // Create an instance with related cleanup code.
 * val snackbar = snackbarBuilder.build { snackbarResult ->
 *     // `this` is the `SnackBarGeneric` itself.
 *     state = state.copy(snackbars = state.snackbars - this)
 * }
 *
 * // Add the new instance to the state.
 * state = state.copy(snackbars = state.snackbars + snackbar)
 * ```
 */
@Immutable
class SnackBarGeneric private constructor(
    val message: Context.() -> String,
    val actionLabel: Context.() -> String?,
    val onResult: SnackBarGeneric.(SnackbarResult) -> Unit,
    val duration: SnackbarDuration?,
    val swipeToDismiss: Boolean,
) {
    class Builder(
        private val message: Context.() -> String,
        private val actionLabel: Context.() -> String? = { null },
        private val duration: SnackbarDuration? = null,
        private val swipeToDismiss: Boolean = true,
    ) {
        constructor(
            message: String,
            actionLabel: String? = null,
            duration: SnackbarDuration? = null,
            swipeToDismiss: Boolean = true,
        ) : this(
            message = { message },
            actionLabel = { actionLabel },
            duration = duration,
            swipeToDismiss = swipeToDismiss,
        )

        constructor(
            @StringRes message: Int,
            @StringRes actionLabel: Int? = null,
            duration: SnackbarDuration? = null,
            swipeToDismiss: Boolean = true,
        ) : this(
            message = { getString(message) },
            actionLabel = { actionLabel?.let { getString(actionLabel) } },
            duration = duration,
            swipeToDismiss = swipeToDismiss,
        )

        fun build(onResult: SnackBarGeneric.(SnackbarResult) -> Unit) =
            SnackBarGeneric(message, actionLabel, onResult, duration, swipeToDismiss)
    }
}
