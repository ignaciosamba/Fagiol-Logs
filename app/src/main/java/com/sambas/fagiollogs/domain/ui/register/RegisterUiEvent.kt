package com.sambas.fagiollogs.domain.ui.register

import com.sambas.fagiollogs.core.viewmodel.UiEvent
import com.sambas.fagiollogs.domain.ui.login.LoginUiEvent

sealed class  RegisterUiEvent: UiEvent {
    data object RegistrationSuccess : RegisterUiEvent()
    data class RegistrationError(val message: String) : RegisterUiEvent()
}