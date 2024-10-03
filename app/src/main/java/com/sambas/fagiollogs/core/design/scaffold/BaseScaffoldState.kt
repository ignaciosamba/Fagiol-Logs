package com.sambas.fagiollogs.core.design.scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.sambas.fagiollogs.core.design.snackbar.DesignSnackbarHostState

@Stable
sealed interface BaseScaffoldState {
    val snackbarHostState: DesignSnackbarHostState

    @Stable
    private class BaseScaffoldStateImpl(
        override val snackbarHostState: DesignSnackbarHostState
    ) : BaseScaffoldState

    companion object {
        operator fun invoke(
            snackbarHostState: DesignSnackbarHostState,
        ): BaseScaffoldState = BaseScaffoldStateImpl(snackbarHostState)
    }
}

/**
 * Creates a [BaseScaffoldState] with the default animation clock and memorizes it.
 *
 * @param snackbarHostState instance of [DesignSnackbarHostState] to be used to show [SnackBar] is
 * inside of the [Scaffold]
 */
@Composable
fun rememberBaseScaffoldState(
    snackbarHostState: DesignSnackbarHostState = remember { DesignSnackbarHostState() }
): BaseScaffoldState = remember {
    BaseScaffoldState(snackbarHostState)
}