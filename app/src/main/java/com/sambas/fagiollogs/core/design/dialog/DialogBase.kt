package com.sambas.fagiollogs.core.design.dialog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable

/**
 * A generic dialog.
 *
 * This uses a builder to make it possible to the UI and cleanup code separately, allowing for
 * example to define the UI from a Composable and the state management from a ViewModel.
 *
 * ```
 * // Create a builder to define the UI.
 * val dialogBuilder = DialogGeneric.Builder { closeDialog ->
 *     GenericDialog(
 *         title = "The title",
 *         primaryButton = GenericDialogButton("The button") {
 *             // Do whatever you need here and invoke the provided `closeDialog`,
 *             // which should update the state.
 *             closeDialog()
 *         }
 *     )
 * }
 *
 * // Create an instance with related cleanup code.
 * val dialog = dialogBuilder.build {
 *     // `this` is the `DialogGeneric` itself.
 *     state = state.copy(dialogs = state.dialogs - this)
 * }
 *
 * // Add the new instance to the state.
 * state = state.copy(dialogs = state.dialogs + dialog)
 * ```
 */
@Immutable
class DialogBase private constructor(
    val content: @Composable (closeDialog: () -> Unit) -> Unit,
    val dismiss: DialogBase.() -> Unit,
) {
    class Builder(
        private val content: @Composable (dismiss: () -> Unit) -> Unit,
    ) {
        fun build(dismiss: DialogBase.() -> Unit): DialogBase {
            return DialogBase(
                content = content,
                dismiss = dismiss,
            )
        }
    }
}