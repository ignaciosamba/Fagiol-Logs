package com.sambas.fagiollogs.domain.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.components.ProfileHeader
import com.sambas.fagiollogs.core.design.components.row.RowSelector
import com.sambas.fagiollogs.core.design.components.row.RowToggleSelector
import com.sambas.fagiollogs.core.design.navigationbar.Toolbar
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerXXS

@Composable
internal fun SettingScreen(
    modifier: Modifier = Modifier,
    settingsUiState: SettingsUiState,
    onOptionClick: (SettingsOptions) -> Unit,
    onToggleClick: (SettingsOptions, Boolean) -> Unit,
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
                        text = stringResource(R.string.logout_title)
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
            item {
                ProfileHeader(
                    modifier = Modifier.padding(vertical = DesignTheme.spacing.space_m),
                    profileImage = R.drawable.ic_mum_profile,
                    name = settingsUiState.parentName,
                    email = settingsUiState.parentEmail,
                    buttonText = "Edit profile",
                    onEditClick = {
                        // Here we can navigate to the edit profile screen
                    }
                )
            }
            items(SettingsOptions.entries) { settingItem ->
                if (settingItem.toggleType) {
                    RowToggleSelector(
                        label = stringResource(id = settingItem.text),
                        leftIcon = ImageVector.vectorResource(settingItem.icon),
                        isChecked = when(settingItem) {
                            SettingsOptions.NOTIFICATION -> settingsUiState.notificationSelected
                            SettingsOptions.METRIC_SYSTEM -> settingsUiState.metricSelected
                            else -> false
                        },
                        onClick = { onToggleClick(settingItem, it) }
                    )
                } else {
                    RowSelector(
                        label = stringResource(id = settingItem.text),
                        selectedLanguage = when(settingItem) {
                            SettingsOptions.LANGUAGE -> settingsUiState.languageLabel
                            SettingsOptions.THEME -> settingsUiState.themeLabel.text
                            else -> ""
                        },
                        leftIcon = ImageVector.vectorResource(settingItem.icon),
                        onClick = { onOptionClick(settingItem) }
                    )
                }
                SpacerXXS()
            }
        }

    }
}

@Preview
@Composable
private fun SettingScreenPreview() {
    PreviewTheme(true) {
        SettingScreen(
            settingsUiState = SettingsUiState(
                parentName = "Parent Name",
                parentEmail = "ParentEmail@email.com",
                babyName = "Parent Baby Name"
            ),
            onOptionClick = {},
            onLogoutClick = {},
            onToggleClick = { _, _ -> },
            onBackPressed = {}
        )
    }
}