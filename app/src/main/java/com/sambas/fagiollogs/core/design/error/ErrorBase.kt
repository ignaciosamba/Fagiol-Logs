package com.sambas.fagiollogs.core.design.error

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.theme.DesignTheme

@Immutable
sealed interface ErrorBase

@JvmInline
value class DismissError(val value: Boolean)

/**
 * A generic fullscreen error.
 *
 * This uses a builder to make it possible to the UI and cleanup code separately, allowing for
 * example to define the UI from a Composable and the state management from a ViewModel.
 *
 * ```
 * // Create a builder to define the UI.
 * val errorBuilder = FullscreenError.Builder(
 *     title = "The title",
 *     message = "The message",
 *     primaryButton = FullscreenError.Button("The button") {
 *         // Do whatever you need here and say if you want to dismiss the dialog.
 *         DismissError(true)
 *     }
 * )
 *
 * // Create an instance with related cleanup code.
 * val error = errorBuilder.build {
 *     // `this` is the `FullscreenError` itself.
 *     state = state.copy(errors = state.errors - this)
 * }
 *
 * // Add the new instance to the state.
 * state = state.copy(errors = state.errors + error)
 * ```
 *
 * See also the specialized [FullscreenGenericErrorBuilder] and [FullscreenNetworkErrorBuilder]
 * to quickly build common errors.
 */
@Immutable
class FullscreenError private constructor(
    val title: Context.() -> String,
    val message: Context.() -> String,
    val primaryButton: Button,
    val secondaryButton: Button?,
    val errorAnimation: @Composable () -> Int,
    val dismiss: FullscreenError.() -> Unit,
    val backButtonEnabled: Boolean = true,
) : ErrorBase {

    class Builder(
        private val title: Context.() -> String,
        private val message: Context.() -> String,
        private val errorAnimation: @Composable () -> Int,
        private val primaryButton: Button,
        private val secondaryButton: Button? = null,
        private val backButtonEnabled: Boolean = true,
    ) {
        constructor(
            title: String,
            message: String,
            primaryButton: Button,
            errorAnimation: @Composable () -> Int = {
                DesignTheme.animation.genericError
            },
            secondaryButton: Button? = null,
            backButtonEnabled: Boolean = true,
        ) : this(
            title = { title },
            message = { message },
            primaryButton = primaryButton,
            secondaryButton = secondaryButton,
            errorAnimation = errorAnimation,
            backButtonEnabled = backButtonEnabled,
        )

        constructor(
            @StringRes title: Int,
            @StringRes message: Int,
            primaryButton: Button,
            errorAnimation: @Composable () -> Int = {
                DesignTheme.animation.genericError
            },
            secondaryButton: Button? = null,
            backButtonEnabled: Boolean = true,
        ) : this(
            title = { getString(title) },
            message = { getString(message) },
            primaryButton = primaryButton,
            secondaryButton = secondaryButton,
            errorAnimation = errorAnimation,
            backButtonEnabled = backButtonEnabled,
        )

        fun build(dismiss: FullscreenError.() -> Unit) = FullscreenError(
            title = title,
            message = message,
            primaryButton = primaryButton,
            secondaryButton = secondaryButton,
            errorAnimation = errorAnimation,
            dismiss = dismiss,
            backButtonEnabled = backButtonEnabled,
        )
    }

    @Immutable
    class Button(
        val text: Context.() -> String,
        val onClick: FullscreenError.() -> DismissError,
    ) {
        constructor(
            text: String,
            onClick: FullscreenError.() -> DismissError,
        ) : this(
            text = { text },
            onClick = onClick,
        )

        constructor(
            @StringRes text: Int,
            onClick: FullscreenError.() -> DismissError,
        ) : this(
            text = { getString(text) },
            onClick = onClick,
        )
    }

    companion object {

        @Suppress("FunctionName")
        fun RetryButton(onClick: FullscreenError.() -> DismissError) = Button(
            text = { getString(R.string.error_retry_button_text) },
            onClick = onClick,
        )
    }
}

/**
 * Build a [FullscreenError] for network errors.
 */
@Suppress("FunctionName")
fun FullscreenNetworkErrorBuilder(
    primaryButton: FullscreenError.Button,
    secondaryButton: FullscreenError.Button? = null,
    backButtonEnabled: Boolean = true,
) = FullscreenError.Builder(
    title = R.string.network_generic_error_title_fullscreen,
    message = R.string.network_generic_error_text_fullscreen,
    errorAnimation = { DesignTheme.animation.networkError },
    primaryButton = primaryButton,
    secondaryButton = secondaryButton,
    backButtonEnabled = backButtonEnabled,
)

/**
 * Build a [FullscreenError] for generic errors.
 *
 * ```
 * // Create a builder to define the UI.
 * val errorBuilder = FullscreenGenericError(
 *     primaryButton = FullscreenError.Button("Retry") { dismiss ->
 *         // Do whatever you need here and invoke the provided `dismiss` lambda.
 *         dismiss()
 *     }
 * )
 *
 * // Create an instance with related cleanup code.
 * val error = errorBuilder.build {
 *     // `this` is the `FullscreenError` itself.
 *     state = state.copy(errors = state.errors - this)
 * }
 *
 * // Add the new instance to the state.
 * state = state.copy(errors = state.errors + error)
 * ```
 */
@Suppress("FunctionName")
fun FullscreenGenericErrorBuilder(
    primaryButton: FullscreenError.Button,
    secondaryButton: FullscreenError.Button? = null,
    backButtonEnabled: Boolean = true,
) = FullscreenError.Builder(
    title = R.string.generic_error_title_fullscreen,
    message = R.string.generic_error_text_fullscreen,
    errorAnimation = { DesignTheme.animation.genericError },
    primaryButton = primaryButton,
    secondaryButton = secondaryButton,
    backButtonEnabled = backButtonEnabled,
)



/**
 * ```
 * // Create a builder to define the UI.
 * val snackbarBuilder = SnackbarError.Builder("The snackbar message)
 *
 * // Create an instance with related cleanup code.
 * val snackbar = snackbarBuilder.build {
 *     state = state.copy(errors = state.errors - this)
 * }
 *
 * // Add the new instance to the state.
 * state = state.copy(errors = state.errors + snackbar)
 * ```
 *
 * See also the specialized [SnackbarGenericErrorBuilder] and [SnackbarNetworkErrorBuilder]
 * to quickly build common errors.
 */
@Immutable
class SnackbarError private constructor(
    val message: Context.() -> String,
    val actionLabel: Context.() -> String?,
    val onResult: ErrorBase.(SnackbarResult) -> Unit,
    val duration: SnackbarDuration?,
    val swipeToDismiss: Boolean,
) : ErrorBase {

    @Immutable
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

        fun build(onResult: ErrorBase.(SnackbarResult) -> Unit) = SnackbarError(
            message = message,
            actionLabel = actionLabel,
            onResult = onResult,
            duration = duration,
            swipeToDismiss = swipeToDismiss,
        )
    }
}

/**
 * A [SnackbarError.Builder] for generic errors.
 */
val SnackbarGenericErrorBuilder = SnackbarError.Builder(R.string.generic_error_text_fullscreen)