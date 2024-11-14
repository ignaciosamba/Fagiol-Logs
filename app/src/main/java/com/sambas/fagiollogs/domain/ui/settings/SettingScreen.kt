package com.sambas.fagiollogs.domain.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.sambas.fagiollogs.core.design.navigationbar.Toolbar
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme

@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    settingsUiState: SettingsUiState,
    onOptionClick: (String) -> Unit,
    onLogoutClick: () -> Unit,
    onBackPressed: () -> Unit,
) {

    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        uiState = settingsUiState,
        topBar = {
            Toolbar(
                actions = {
                    DesignText.body.Small(
                        modifier = Modifier
                            .padding(end = DesignTheme.spacing.space_xs)
                            .clickable { onLogoutClick() },
                        text = "Logout"
                    )
                }
            )
        },
        bottomBar = {},
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = DesignTheme.spacing.space_xs,
                    end = DesignTheme.spacing.space_xs,
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()

                )
        ) {
            items(SettingsOptions.entries) { settingItem ->
                DesignText.body.Medium(
                    text = stringResource(settingItem.text)
                )
            }
        }

    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    PreviewTheme(true) {
        SettingScreen(
            settingsUiState = SettingsUiState(),
            onOptionClick = {},
            onLogoutClick = {},
            onBackPressed = {}
        )
    }
}