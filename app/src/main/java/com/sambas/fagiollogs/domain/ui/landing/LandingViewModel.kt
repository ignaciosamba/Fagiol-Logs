package com.sambas.fagiollogs.domain.ui.landing

import androidx.lifecycle.SavedStateHandle
import com.google.firebase.firestore.FirebaseFirestore
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.viewmodel.AuthenticationBaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LandingViewModel @Inject constructor(
    savedState: SavedStateHandle,
    authManager: AuthManager,
): AuthenticationBaseViewModel<LandingUiState, LandingUiEvent>(
    initialState = LandingUiState(),
    savedStateHandle = savedState,
    authManager = authManager,
    loadingStateUpdater = { state, loadingType -> state.copy(loadingModel = loadingType.toLoadingModel()) }
) {
    init {
        loadDataFromFirestore()
    }

    private fun loadDataFromFirestore() {
        launchAuthenticationNetworkCall(
            action = {
                // here we will load the events of the day for the client.
            },
            onSuccess = {},
            onError = {},
            onNoConnection = {},
        )
    }
}