package com.sambas.fagiollogs.core.design.scaffold

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.dialog.GenericDialogButton
import com.sambas.fagiollogs.core.design.error.ErrorView
import com.sambas.fagiollogs.core.design.error.FullscreenError
import com.sambas.fagiollogs.core.design.error.SnackbarError
import com.sambas.fagiollogs.core.design.loader.Loader
import com.sambas.fagiollogs.core.design.snackbar.DesignSnackbarType
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun BaseScaffoldImpl(
    uiState: BaseUiState,
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit)?,
    scaffoldState: BaseScaffoldState,
    loaderAsDialog: Boolean,
    scaffold: @Composable () -> Unit,
) {
    val context = LocalContext.current
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current
    val coroutineScope = rememberCoroutineScope()

    val loadingModel = uiState.loadingModel
    val error = uiState.error
    val dialog = uiState.dialog
    val message = uiState.message

    val backPressedCallback = remember {
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                // Do not do anything.
            }
        }
    }

    backPressedCallback.isEnabled = loadingModel.state == LoadingState.Loading ||
            ((error as? FullscreenError)?.backButtonEnabled == false)

    DisposableEffect(backPressedDispatcher) {
        backPressedDispatcher?.onBackPressedDispatcher?.addCallback(backPressedCallback)
        onDispose {
            backPressedCallback.remove()
        }
    }

    when {
        error is SnackbarError -> {
            LaunchedEffect(error) {
                scaffoldState.snackbarHostState.snackbarHostState.currentSnackbarData?.dismiss()
                coroutineScope.launch {
                    val actionLabel = error.actionLabel(context)
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = error.message(context),
                        actionLabel = actionLabel,
                        type = DesignSnackbarType.Error,
                        duration = error.duration ?: SnackbarDuration.Short,
                        swipeToDismiss = error.swipeToDismiss,
                    )
                    waitSnackbarFadeOut {
                        error.onResult(error, result)
                    }
                }
            }
        }

        message is SnackBarGeneric -> {
            LaunchedEffect(message) {
                scaffoldState.snackbarHostState.snackbarHostState.currentSnackbarData?.dismiss()
                coroutineScope.launch {
                    val actionLabel = message.actionLabel(context)
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = message.message(context),
                        actionLabel = actionLabel,
                        type = DesignSnackbarType.Generic,
                        duration = message.duration ?: SnackbarDuration.Short,
                        swipeToDismiss = message.swipeToDismiss,
                    )
                    waitSnackbarFadeOut {
                        message.onResult(message, result)
                    }
                }
            }
        }

        else -> {
            // This allows to dismiss snackbars with infinite duration by simply removing them from the state.
            scaffoldState.snackbarHostState.snackbarHostState.currentSnackbarData?.dismiss()
        }
    }

    Box(modifier = modifier.semantics { testTagsAsResourceId = true }) {
        scaffold()

        AnimatedContent(
            targetState = error,
            transitionSpec = {
                fadeIn(animationSpec = tween(220, delayMillis = 90)) togetherWith fadeOut(
                    animationSpec = tween(90)
                )
            },
            label = "FullScreenError visibility animation",
        ) { error ->
            @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
            if (error is FullscreenError) {
                // We want the topBar, but not the bottomBar of the main content.
                // We could probably hide the bottomBar conditionally, but a separate Scaffold
                // is maybe simpler since it does not change the layout.
                Scaffold(
                    topBar = topBar ?: {},
                ) { contentPadding ->
                    contentPadding
                    ErrorView(
                        title = error.title(context),
                        message = error.message(context),
                        animation = error.errorAnimation(),
                        primaryButton = error.primaryButton.toGenericDialogButton(context, error),
                        secondaryButton = error.secondaryButton?.toGenericDialogButton(
                            context,
                            error
                        ),
                    )
                }
            } else {
                AnimatedContentPlaceholder()
            }
        }
    }

    dialog?.content?.invoke { dialog.dismiss(dialog) }


    AnimatedContent(
        targetState = loadingModel,
        modifier = Modifier.fillMaxSize(),
        label = "LoadingAnimation",
    ) { model ->
        if (model.state != LoadingState.None) {
            Loader()
        } else {
            AnimatedContentPlaceholder()
        }
    }

}

// The Snackbar has a fade out animation that lasts 75ms (SnackbarFadeOutMillis in SnackbarHost).
// The extra time is to take into account that the animation does not necessarily start immediately
// and 25ms shouldn't be noticeable.
private const val SNACKBAR_ANIMATION_DELAY = 100L

internal suspend inline fun waitSnackbarFadeOut(resultCallback: () -> Unit) {
    // The snackbar has a subtle fade-out animation, but changes to the status may cause it to
    // disappear immediately. This adds enough delay for the animation to play fully.
    try {
        delay(SNACKBAR_ANIMATION_DELAY)
    } finally {
        // This ensures we execute the callback even when the job gets cancelled. In this way
        // the code behaves as if the delay was never there.
        resultCallback()
    }
}

/**
 * [AnimatedContent] provides access to the target state, which allows to fade out a Composable properly
 * while it is being removed from the screen. However, this only works properly when replacing the exiting
 * Composable with one that is as big as the one being removed.
 * This is what [AnimatedContentPlaceholder] is for.
 */
@Suppress("NOTHING_TO_INLINE")
@Composable
private inline fun AnimatedContentPlaceholder() = Spacer(modifier = Modifier.fillMaxSize())

private fun FullscreenError.Button.toGenericDialogButton(
    context: Context,
    error: FullscreenError,
): GenericDialogButton {
    return GenericDialogButton(
        text = text(context),
        onClick = {
            if (onClick(error).value) {
                error.dismiss(error)
            }
        },
    )
}
