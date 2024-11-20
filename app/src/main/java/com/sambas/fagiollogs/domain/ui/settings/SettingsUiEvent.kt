package com.sambas.fagiollogs.domain.ui.settings

import com.sambas.fagiollogs.core.viewmodel.UiEvent

sealed class SettingsUiEvent: UiEvent {
    data object LogoutSuccess : SettingsUiEvent()
}