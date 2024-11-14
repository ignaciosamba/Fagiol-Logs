package com.sambas.fagiollogs.domain.ui.settings

import androidx.lifecycle.SavedStateHandle
import com.google.firebase.auth.FirebaseAuth
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.viewmodel.AuthenticationBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    authManager: AuthManager
) : AuthenticationBaseViewModel<SettingsUiState, SettingsUiEvent>(
    savedStateHandle = savedStateHandle,
    initialState = SettingsUiState(),
    authManager = authManager,
    loadingStateUpdater = { state, loadingType -> state.copy(loadingModel = loadingType.toLoadingModel()) }
) {
    fun logOut() {
        launchAuthenticationNetworkCall(
            action = {
                authManager.signOut()
            },
            onSuccess = {
                emitEvent(SettingsUiEvent.LogoutSuccess)
            },
            onError = {

            },
            onNoConnection = {

            }
        )
    }
}