package com.sambas.fagiollogs.core.design.snackbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import com.sambas.fagiollogs.core.design.scaffold.waitSnackbarFadeOut
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

enum class DesignSnackbarType {
    Generic,
    Error
}


object DesignSnackbar {

    @Composable
    operator fun invoke(
        message: String,
        action: SnackbarAction? = null,
        type: DesignSnackbarType = DesignSnackbarType.Generic,
    ) {
        when (type) {
            DesignSnackbarType.Generic -> Generic(message, action)
            DesignSnackbarType.Error -> Error(message, action)
        }
    }

    @Composable
    fun Error(message: String, action: SnackbarAction? = null) = Snackbar(
        message = message,
        action = action,
        backgroundColor = DesignTheme.colors.contentError,
    )

    @Composable
    fun Generic(message: String, action: SnackbarAction? = null) = Snackbar(
        message = message,
        action = action,
        backgroundColor = DesignTheme.colors.backgroundSecondary,
    )
}

/**
 * A wrapper of [SnackbarHost] for custom snackbars.
 *
 * To use this class, build [Scaffold] as follows:
 *
 * ```
 * val snackbarHostState = remember { DesignSnackbarHostState() }
 * Scaffold(
 *     snackbarHost = { DesignSnackbarHost(snackbarHostState) }
 * ) {
 *     // The content
 * }
 * ```
 */
@Stable
class DesignSnackbarHostState {
    var type by mutableStateOf(DesignSnackbarType.Generic)
        private set

    internal var swipeToDismiss by mutableStateOf(true)
        private set

    internal val snackbarHostState = SnackbarHostState()

    val currentSnackbarData get() = snackbarHostState.currentSnackbarData

    private val mutex = Mutex()

    suspend fun showSnackbar(
        message: String,
        actionLabel: String?,
        duration: SnackbarDuration,
        swipeToDismiss: Boolean,
        type: DesignSnackbarType = DesignSnackbarType.Generic,
    ): SnackbarResult = mutex.withLock {
        this.type = type
        this.swipeToDismiss = swipeToDismiss
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = duration,
        )
    }
}

@Composable
fun DesignSnackbarHost(
    hostState: DesignSnackbarHostState,
    modifier: Modifier = Modifier,
    snackbar: @Composable (DesignSnackbarType, SnackbarData) -> Unit = snackbar(),
) {
    if (LocalInspectionMode.current) {
        // Needed to show the Snackbar in the preview.
        val snackbarData = hostState.currentSnackbarData ?: return
        Box(modifier = modifier) {
            SnackbarHostContent(hostState, snackbarData, snackbar)
        }
    } else {
        SnackbarHost(
            hostState = hostState.snackbarHostState,
            modifier = modifier,
        ) { snackbarData ->
            SnackbarHostContent(hostState, snackbarData, snackbar)
        }
    }
}

@Composable
private fun SnackbarHostContent(
    hostState: DesignSnackbarHostState,
    snackbarData: SnackbarData,
    snackbar: @Composable (DesignSnackbarType, SnackbarData) -> Unit,
) {
    if (hostState.swipeToDismiss) {
        SwipeableDesignSnackbarWrapper(hostState) {
            snackbar(hostState.type, snackbarData)
        }
    } else {
        snackbar(hostState.type, snackbarData)
    }
}

private fun snackbar(): @Composable (DesignSnackbarType, SnackbarData) -> Unit = { type, snackbarData ->
    DesignSnackbar(
        type = type,
        message = snackbarData.visuals.message,
        action = snackbarData.visuals.actionLabel?.let { label ->
            SnackbarAction(
                label = label,
                performAction = snackbarData::performAction,
            )
        },
    )
}

/**
 * A wrapper to make [DesignSnackbar] swipeable.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SwipeableDesignSnackbarWrapper(
    state: DesignSnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val dismissSnackbarState = rememberSwipeToDismissBoxState()

    LaunchedEffect(dismissSnackbarState.currentValue) {
        if (dismissSnackbarState.currentValue != SwipeToDismissBoxValue.Settled) {
            state.currentSnackbarData?.dismiss()
            waitSnackbarFadeOut {
                // Move the snackbar to its initial position once hidden.
                dismissSnackbarState.reset()
            }
        }
    }

    SwipeToDismissBox(
        modifier = modifier,
        state = dismissSnackbarState,
        backgroundContent = {},
        content = { content() },
    )
}

@Preview(showBackground = true)
@Composable
private fun DesignSnackbarPreview() {
    PreviewTheme(fullScreen = false) {
        Column {
            DesignSnackbar(
                type = DesignSnackbarType.Generic,
                message = "Error",
            )
            DesignSnackbar(
                type = DesignSnackbarType.Error,
                message = "Generic",
            )
        }
    }
}