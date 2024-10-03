package com.sambas.fagiollogs.domain.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.domain.login.LoginUiState

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    loginUiState: LoginUiState
) {
    BaseScaffold(
        uiState = loginUiState,
        backgroundColor = DesignTheme.colors.contentAction
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Login Illustration",
                modifier = Modifier
                    .size(180.dp)
                    .padding(DesignTheme.spacing.space_xs),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    PreviewTheme(fullScreen = true) {
        SplashScreen(loginUiState = LoginUiState("", ""))

    }
}