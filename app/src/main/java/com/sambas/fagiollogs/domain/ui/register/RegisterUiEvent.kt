package com.sambas.fagiollogs.domain.ui.register

import com.sambas.fagiollogs.core.viewmodel.UiEvent

sealed class  RegisterUiEvent: UiEvent {
    data object RegistrationSuccess : RegisterUiEvent()
}