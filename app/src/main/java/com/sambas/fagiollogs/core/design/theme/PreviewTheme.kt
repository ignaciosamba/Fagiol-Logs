package com.sambas.fagiollogs.core.design.theme

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat

/**
 * A Composable that provides access to the current [Window].
 *
 * This is useful to setup some flags when using [Preview], which provides close to no control
 * on the Context in which the Composable is hosted.
 */
@Composable
fun SetupPreviewWindow(block: Window.() -> Unit) {
    val context = LocalContext.current
    SideEffect {
        context.findActivity()?.window?.let(block)
    }
}

@Composable
fun PreviewTheme(
    fullScreen: Boolean,
    themeConfig: ThemeConfig = fagiolsThemeConfig,
    content: @Composable () -> Unit,
) {
    if (fullScreen) {
        SetupPreviewWindow {
            WindowCompat.setDecorFitsSystemWindows(this, false)
        }
    }

    CompositionLocalProvider(
        LocalThemeConfig provides themeConfig,
    ) {
        ComposeDesignTheme(fullScreen = fullScreen) {
            Box {
             content()
            }
        }
    }
}


/**
 * Function that looks for the closest [Activity] in a given [Context].
 *
 * @return The closest [Activity] for the given [Context] if found, null otherwise.
 */
fun Context?.findActivity() = findContext(Activity::class.java)


private tailrec fun <T> Context?.findContext(contextType: Class<T>): T? = when {
    this == null -> null
    contextType.isAssignableFrom(this::class.java) -> contextType.cast(this)
    this is ContextWrapper -> baseContext.findContext(contextType)
    else -> null
}