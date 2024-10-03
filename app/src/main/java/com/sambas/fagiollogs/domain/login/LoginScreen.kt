package com.sambas.fagiollogs.domain.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.button.DesignButtons
import com.sambas.fagiollogs.core.design.button.DesignImageButtons
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerM
import com.sambas.fagiollogs.core.design.theme.SpacerS

@Composable
fun LoginScreen(
    loginUiState: LoginUiState,
    onLoginClick: (String, String) -> Unit,
    onLoginGoogleClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onCreateAccountClick: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    BaseScaffold(
        uiState = loginUiState,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = DesignTheme.spacing.space_m,
                    vertical = DesignTheme.spacing.space_m
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FAGIOL'S LOG",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = DesignTheme.colors.contentPrimary,
                modifier = Modifier
                    .padding(
                        top = DesignTheme.spacing.space_l,
                        bottom = DesignTheme.spacing.space_xs
                    )
            )

            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Login Illustration",
                modifier = Modifier
                    .size(180.dp)
                    .padding(DesignTheme.spacing.space_xs),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "LOGIN",
                style = DesignTheme.typography.title_m,
                fontWeight = FontWeight.Bold,
                color = DesignTheme.colors.contentPrimary,
                modifier = Modifier.padding(bottom = DesignTheme.spacing.space_m)
            )

            TextField(
                value = username,
                onValueChange = { username = it },
                label = {
                    Text(
                        text = "Username",
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
                value = password,
                onValueChange = { password = it },
                label = {
                    Text(
                        text = "Password",
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
                    .padding(bottom = DesignTheme.spacing.space_s),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = DesignTheme.colors.backgroundPrimary,
                    unfocusedContainerColor = DesignTheme.colors.backgroundSecondary,
                    disabledContainerColor = DesignTheme.colors.backgroundSecondary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            DesignButtons.primary.Medium(
                text = "Login",
                onClick = { onLoginClick(username, password) },
            )

            SpacerM()

            DesignImageButtons.ghost.Medium(
                text = "Continue with Google",
                painter = painterResource(id = R.drawable.google_ic)
            ) {
                onLoginGoogleClick()
            }

            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier.padding(top = DesignTheme.spacing.space_xxs)
            ) {
                Text(
                    "Forgot Password?",
                    color = DesignTheme.colors.contentSecondary
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = DesignTheme.spacing.space_xs),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Not Registered?",
                    color = DesignTheme.colors.contentSecondary
                )
                TextButton(onClick = onCreateAccountClick) {
                    Text("Create account", color = DesignTheme.colors.contentPrimary)
                }
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
            onCreateAccountClick = {}
        )
    }
}