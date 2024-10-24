package com.sambas.fagiollogs.domain.ui.login

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.autentication.AuthState
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.error.SnackbarError
import com.sambas.fagiollogs.core.design.error.SnackbarGenericErrorBuilder
import com.sambas.fagiollogs.core.design.loader.ScreenLoadingType
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.viewmodel.AuthenticationBaseViewModel
import com.sambas.fagiollogs.core.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val auth: FirebaseAuth,
    authManager: AuthManager,
    savedStateHandle: SavedStateHandle
) : AuthenticationBaseViewModel<LoginUiState, LoginUiEvent>(
    savedStateHandle = savedStateHandle,
    initialState = LoginUiState(),
    authManager = authManager,
    loadingStateUpdater = { state, loadingType -> state.copy(loadingModel = loadingType.toLoadingModel()) }
) {

    private val oneTapClient: SignInClient = Identity.getSignInClient(context)

    init {
        checkUserLoggedIn()
    }

    fun loginUser(email: String, password: String) {
        launchAuthenticationNetworkCall(
            action = { auth.signInWithEmailAndPassword(email, password).await() },
            onSuccess = { emitEvent(LoginUiEvent.LoginSuccess) },
            onError = {
                onNewError(SnackbarGenericErrorBuilder)
            },
            onNoConnection = {
                onNewError(SnackbarGenericErrorBuilder)
            }
        )
    }

    fun initiateGoogleSignIn() {
        launchAuthenticationNetworkCall(
            action = {
                val signInRequest = BeginSignInRequest.builder()
                    .setGoogleIdTokenRequestOptions(
                        BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                            .setSupported(true)
                            .setServerClientId(context.getString(R.string.default_web_client_id))
                            .setFilterByAuthorizedAccounts(false)
                            .build()
                    )
                    .build()
                val result = oneTapClient.beginSignIn(signInRequest).await()
                emitEvent(LoginUiEvent.StartGoogleSignIn(result.pendingIntent.intentSender))
            },
            onSuccess = {

            },
            onError = { e ->
                emitEvent(LoginUiEvent.LoginError("Couldn't start Google Sign In: ${e.message}"))
            },
            loadingType = ScreenLoadingType.None
        )
    }

    fun handleGoogleSignInResult(credential: SignInCredential) {
        launchAuthenticationNetworkCall(
            action = {
                val firebaseCredential =
                    GoogleAuthProvider.getCredential(credential.googleIdToken, null)
                auth.signInWithCredential(firebaseCredential).await()
            },
            onSuccess = {
                emitEvent(LoginUiEvent.LoginSuccess)
            },
            onError = { e ->
                emitEvent(LoginUiEvent.LoginError("Google sign-in failed: ${e.message}"))
            }
        )
    }

    fun onPasswordChanged(password: String) {
        setState {
            state.value.copy(password = password)
        }
    }

    fun onEmailChanged(email: String) {
        setState {
            state.value.copy(email = email)
        }
    }

    private fun checkUserLoggedIn() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            emitEvent(LoginUiEvent.UserAlreadyLoggedIn)
        } else {
            emitEvent(LoginUiEvent.UserNotLoggedIn)
        }
        viewModelScope.launch {
            authManager.authState.collect { authState ->
                when (authState) {
                    is AuthState.Authenticated -> {
                        emitEvent(LoginUiEvent.UserAlreadyLoggedIn)
                    }

                    is AuthState.NotAuthenticated -> {
                        emitEvent(LoginUiEvent.UserNotLoggedIn)
                    }

                    is AuthState.Error -> {
                        emitEvent(LoginUiEvent.UserNotLoggedIn)
                    }
                }
            }
        }
    }

    /**
     * Method to set in the [LoginScreen] a new Error to be shown by the [BaseScaffold]
     *
     */
    private fun onNewError(errorMessage: SnackbarError.Builder) {
        val newError = errorMessage.build {
            setState { state ->
                state.copy(error = state.error.takeIf { it != this })
            }
        } as ErrorBase
        setState { it.copy(error = newError) }
    }
}