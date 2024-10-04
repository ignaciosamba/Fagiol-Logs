package com.sambas.fagiollogs.domain.ui.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.button.DesignButtons
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerXS

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    registerUiState: RegisterUiState,
    onUserNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordRepeatedChange: (String) -> Unit,
    onRegisterClick: (String, String, String) -> Unit,
    onBackPressed: () -> Unit
) {

    BaseScaffold(
        modifier = Modifier.fillMaxSize(),
        uiState = registerUiState,
        bottomBar = {
            DesignButtons.primary.Medium(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = DesignTheme.spacing.space_xs,
                        vertical = DesignTheme.spacing.space_s
                    ),
                text = "Create account",
                onClick = {
                    onRegisterClick(
                        registerUiState.userName,
                        registerUiState.email,
                        registerUiState.password
                    )
                },
            )
        },
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
                textAlign = TextAlign.Center,
                text = "Setup your account.",
                color = DesignTheme.colors.contentPrimary,
                modifier = Modifier
                    .padding(
                        top = DesignTheme.spacing.space_l
                    )
            )
            DesignText.body.Large(
                textAlign = TextAlign.Center,
                text = "And start tracking your baby's progress and habits",
                color = DesignTheme.colors.contentPrimary,
                modifier = Modifier
                    .padding(
                        top = DesignTheme.spacing.space_l,
                        bottom = DesignTheme.spacing.space_s
                    )
            )

            TextField(
                value = registerUiState.userName,
                onValueChange = {
                    onUserNameChange(it)
                },
                label = {
                    DesignText.body.Medium(
                        text = "Name",
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
                value = registerUiState.email,
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
                value = registerUiState.password,
                onValueChange = {
                    onPasswordChange(it)
                },
                label = {
                    DesignText.body.Medium(
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
                value = registerUiState.secondPassword,
                onValueChange = {
                    onPasswordRepeatedChange(it)
                },
                label = {
                    DesignText.body.Medium(
                        text = "Repeat the Password",
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
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview(){
    PreviewTheme(true) {
        RegisterScreen(
            registerUiState = RegisterUiState(
                email = "",
                password = "",
                userName = ""
            ),
            onUserNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onRegisterClick = {_,_,_ ->},
            onPasswordRepeatedChange = {},
            onBackPressed = {}
        )
    }
}