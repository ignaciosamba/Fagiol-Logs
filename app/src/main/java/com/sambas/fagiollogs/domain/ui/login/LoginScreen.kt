package com.sambas.fagiollogs.domain.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.button.DesignButtons
import com.sambas.fagiollogs.core.design.button.DesignImageButtons
import com.sambas.fagiollogs.core.design.minicomponents.DividerWithText
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerXS
import com.sambas.fagiollogs.core.design.theme.SpacerXXXS

@Composable
fun LoginScreen(
    loginUiState: LoginUiState,
    onLoginClick: (String, String) -> Unit,
    onLoginGoogleClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onCreateAccountClick: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit
) {

    BaseScaffold(
        modifier = Modifier.fillMaxSize(),
        uiState = loginUiState,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = DesignTheme.spacing.space_xs,
                    vertical = DesignTheme.spacing.space_m
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignText.titles.Medium(
                text = "FAGIOL'S LOG",
                color = DesignTheme.colors.contentPrimary,
                modifier = Modifier
                    .padding(
                        top = DesignTheme.spacing.space_l,
                        bottom = DesignTheme.spacing.space_xs
                    )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription ="Login Illustration",
                modifier = Modifier
                    .size(180.dp)
                    .padding(DesignTheme.spacing.space_xs),
                contentScale = ContentScale.Fit
            )

            DesignText.titles.Medium(
                text = stringResource(id = R.string.login_title),
                textAlign = TextAlign.Center,
                color = DesignTheme.colors.contentPrimary,
                modifier = Modifier.padding(bottom = DesignTheme.spacing.space_m)
            )

            TextField(
                value = loginUiState.email,
                onValueChange = {
                    onEmailChange(it)
                },
                label = {
                    DesignText.body.Medium(
                        text = "Email address",
                        color = DesignTheme.colors.contentSecondary
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Username",
                        tint = DesignTheme.colors.contentPrimary,
                        modifier = Modifier.size(DesignTheme.spacing.space_xs)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = DesignTheme.spacing.space_xs),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DesignTheme.colors.backgroundPrimary,
                    unfocusedContainerColor = DesignTheme.colors.backgroundSecondary,
                    disabledContainerColor = DesignTheme.colors.backgroundSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            TextField(
                value = loginUiState.password,
                onValueChange = {
                    onPasswordChange(it)
                },
                label = {
                    DesignText.body.Medium(
                        text = stringResource(R.string.password_label),
                        color = DesignTheme.colors.contentSecondary
                    )
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_lock),
                        contentDescription = "Password",
                        tint = DesignTheme.colors.contentPrimary,
                        modifier = Modifier.size(DesignTheme.spacing.space_xs)
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = DesignTheme.spacing.space_xxs),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DesignTheme.colors.backgroundPrimary,
                    unfocusedContainerColor = DesignTheme.colors.backgroundSecondary,
                    disabledContainerColor = DesignTheme.colors.backgroundSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            DesignText.body.Small(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = DesignTheme.spacing.space_s,
                        end = DesignTheme.spacing.space_mini
                    )
                    .clickable {
                        onForgotPasswordClick()
                    },
                textAlign = TextAlign.End,
                text = stringResource(R.string.forgot_password_text),
                color = DesignTheme.colors.contentSecondary
            )

            DesignButtons.primary.Medium(
                text = stringResource(R.string.login_button),
                onClick = {
                    onLoginClick(
                        loginUiState.email,
                        loginUiState.password
                    )
                },
            )

            SpacerXS()

            DividerWithText(text = "or")

            SpacerXS()

            DesignImageButtons.ghost.Medium(
                text = stringResource(R.string.continue_with_google_button),
                painter = painterResource(id = R.drawable.google_ic)
            ) {
                onLoginGoogleClick()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = DesignTheme.spacing.space_m),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = CenterVertically
            ) {
                Box(contentAlignment = Center) {
                    DesignText.body.Medium(
                        text = stringResource(R.string.not_already_registered),
                        color = DesignTheme.colors.contentSecondary
                    )
                }
                SpacerXXXS()
                DesignText.body.MediumBold(
                    modifier = Modifier.clickable {
                        onCreateAccountClick()
                    },
                    text = stringResource(R.string.create_account),
                    color = DesignTheme.colors.contentAction
                )
            }
        }
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    PreviewTheme(fullScreen = true) {
        LoginScreen(
            loginUiState = LoginUiState(),
            onLoginClick = { _, _ -> },
            onForgotPasswordClick = {},
            onLoginGoogleClick = {},
            onCreateAccountClick = {},
            onPasswordChange = {},
            onEmailChange = {}
        )
    }
}