package com.sambas.fagiollogs.domain.ui.login

import android.content.IntentSender
import com.sambas.fagiollogs.core.viewmodel.UiEvent

sealed class LoginUiEvent : UiEvent {
    data object LoginSuccess : LoginUiEvent()
    data class StartGoogleSignIn(val intentSender: IntentSender) : LoginUiEvent()
    data class LoginError(val message: String) : LoginUiEvent()
    data object UserAlreadyLoggedIn : LoginUiEvent()
    data object UserNotLoggedIn : LoginUiEvent()
}