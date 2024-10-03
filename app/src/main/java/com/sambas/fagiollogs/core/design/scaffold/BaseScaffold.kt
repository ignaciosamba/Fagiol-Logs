package com.sambas.fagiollogs.core.design.scaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.snackbar.DesignSnackbarHost
import com.sambas.fagiollogs.core.design.snackbar.DesignSnackbarHostState
import com.sambas.fagiollogs.core.design.theme.DesignTheme


@Composable
fun BaseScaffold(
    uiState: BaseUiState,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    scaffoldState: BaseScaffoldState = rememberBaseScaffoldState(),
    snackbarHost: @Composable (DesignSnackbarHostState) -> Unit = { hostState ->
        DefaultSnackbarHost(
            hostState
        )
    },
    backgroundColor: Color = DesignTheme.colors.backgroundPrimary,
    contentColor: Color = DesignTheme.colors.contentPrimary,
    loaderAsDialog: Boolean = false,
    contentWindowInsets: WindowInsets = WindowInsets(0),
    content: @Composable (PaddingValues) -> Unit
) {
    Box(
        Modifier
            .background(backgroundColor)
            .then(modifier)) {
        BaseScaffoldImpl(
            uiState = uiState,
            topBar = topBar,
            scaffoldState = scaffoldState,
            loaderAsDialog = loaderAsDialog,
        ) {
            Scaffold(
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = {},
                containerColor = backgroundColor,
                contentColor = contentColor,
                contentWindowInsets = contentWindowInsets,
                content = content,
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = DesignTheme.spacing.space_l)
        ) {
            snackbarHost(scaffoldState.snackbarHostState)
        }
    }
}

@Composable
private fun DefaultSnackbarHost(hostState: DesignSnackbarHostState) {
    DesignSnackbarHost(hostState, Modifier.padding(bottom = DesignTheme.spacing.space_xs))
}