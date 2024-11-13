package com.sambas.fagiollogs.domain.ui.login

import android.content.Context
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FieldValue
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.autentication.AuthState
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.error.SnackbarError
import com.sambas.fagiollogs.core.design.error.SnackbarGenericErrorBuilder
import com.sambas.fagiollogs.core.design.loader.ScreenLoadingType
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric
import com.sambas.fagiollogs.core.viewmodel.AuthenticationBaseViewModel
import com.sambas.fagiollogs.di.ApplicationScope
import com.sambas.fagiollogs.domain.utils.firestore.FirestoreUtils
import com.sambas.fagiollogs.domain.utils.firestore.USER_DB_BASE_PATH
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope private val applicationScope: CoroutineScope,
    private val auth: FirebaseAuth,
    authManager: AuthManager,
    private val googleSignInHelper: GoogleSignInHelper,
    savedStateHandle: SavedStateHandle,
    private val firestoreUtils: FirestoreUtils,
) : AuthenticationBaseViewModel<LoginUiState, LoginUiEvent>(
    savedStateHandle = savedStateHandle,
    initialState = LoginUiState(),
    authManager = authManager,
    loadingStateUpdater = { state, loadingType -> state.copy(loadingModel = loadingType.toLoadingModel()) }
) {

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
                // Get the credential using Credential Manager
                val googleCredential = googleSignInHelper.initiateGoogleSignIn(
                    clientId = "${context.getString(R.string.gcm_defaultSenderId)}-bamh93m5u9uao71svs4g16ansghe63vk.apps.googleusercontent.com"
                )

                // Create Firebase credential
                val firebaseCredential = GoogleAuthProvider.getCredential(
                    googleCredential.idToken, null
                )

                // Sign in to Firebase
                auth.signInWithCredential(firebaseCredential).await()
            },
            onSuccess = {
                emitEvent(LoginUiEvent.LoginSuccess)
            },
            onError = { e ->
                when (e) {
                    is GetCredentialCancellationException -> {
                        // User canceled the operation
                        emitEvent(LoginUiEvent.LoginError("Google Sign-in canceled"))
                    }

                    is NoCredentialException -> {
                        // No credentials available
                        emitEvent(LoginUiEvent.LoginError("No Google accounts found"))
                    }

                    else -> {
                        emitEvent(LoginUiEvent.LoginError("Google sign-in failed: ${e.message}"))
                    }
                }
            },
            loadingType = ScreenLoadingType.None
        )
    }

    fun onPasswordResetRequested(email: String) {
        launchAuthenticationNetworkCall(
            action = {
                if (email.isEmpty()) {
                    onNewError(SnackbarError.Builder(R.string.email_not_valid_error))
                } else {
                    auth.useAppLanguage()
                    auth.sendPasswordResetEmail(email)
                }
            },
            onSuccess = {
                onNewMessage(SnackBarGeneric.Builder(R.string.reset_password_email_sent))
            },
            onError = {
                onNewError(SnackbarGenericErrorBuilder)
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

    fun saveUserToFirestore() {
        applicationScope.launch {
            val userId = authManager.getCurrentUser()?.uid.orEmpty()
            val data = hashMapOf(
                "userId" to userId,
                "name" to authManager.getCurrentUser()?.displayName.orEmpty(),
                "email" to authManager.getCurrentUser()?.email.orEmpty(),
                "timestamp" to FieldValue.serverTimestamp()
            )

            firestoreUtils.addDocumentToCollection(
                collectionPath = "$USER_DB_BASE_PATH$userId",
                data = data
            )
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

    /**
     * Method to set in the [LoginScreen] a new Error to be shown by the [BaseScaffold]
     *
     */
    private fun onNewMessage(message: SnackBarGeneric.Builder) {
        val newMessage = message.build {
            setState { state ->
                state.copy(message = state.message.takeIf { it != this })
            }
        }
        setState { it.copy(message = newMessage) }
    }
}