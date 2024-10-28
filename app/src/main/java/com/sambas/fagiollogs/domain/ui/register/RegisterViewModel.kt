package com.sambas.fagiollogs.domain.ui.register

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.UserProfileChangeRequest
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.error.SnackbarError
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.viewmodel.AuthenticationBaseViewModel
import com.sambas.fagiollogs.core.viewmodel.BaseViewModel
import com.sambas.fagiollogs.domain.exception.PasswordNotMatchingException
import com.sambas.fagiollogs.domain.utils.extractPasswordRequirementsFromFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    savedStateHandle: SavedStateHandle,
    authManager: AuthManager
) : AuthenticationBaseViewModel<RegisterUiState, RegisterUiEvent>(
    savedStateHandle = savedStateHandle,
    initialState = RegisterUiState(),
    authManager = authManager,
    loadingStateUpdater = { state, loadingType -> state.copy(loadingModel = loadingType.toLoadingModel()) }
) {

    fun registerUser(name: String, email: String, password: String) {
        launchAuthenticationNetworkCall(
            action = {
                // Register the user and password.
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                // Update the user's profile with the provided name.
                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                result.user?.updateProfile(profileUpdate)?.await()
                result
            },
            onSuccess = {
                setState { state ->
                    state.copy(
                        errorEmail = true,
                        errorPassword = null,
                        errorRepeatedPassword = false
                    )
                }
                emitEvent(RegisterUiEvent.RegistrationSuccess)
            },
            onError = {
                if (it is PasswordNotMatchingException) {
                    onNewError(SnackbarError.Builder(R.string.not_matching_password_error))
                } else if (it is FirebaseAuthInvalidCredentialsException) {
                    setState { state ->
                        state.copy(
                            errorEmail = true
                        )
                    }
                    onNewError(SnackbarError.Builder(R.string.email_not_valid_error))
                } else {
                    val errorMessage = extractPasswordRequirementsFromFirebase(it.localizedMessage)
                    setState { state ->
                        state.copy(
                            errorPassword = errorMessage
                        )
                    }
                    onNewError(errorMessage?.let { message ->
                        SnackbarError.Builder(message)
                    } ?: SnackbarError.Builder(R.string.generic_error_text_fullscreen))
                }
            }
        )
    }

    fun onNameChanged(name: String) {
        setState {
            state.value.copy(
                userName = name
            )
        }
    }

    fun onPasswordChanged(password: String) {
        setState {
            state.value.copy(
                password = password
            )
        }
    }

    fun onPasswordRepeatedChange(password: String) {
        setState {
            state.value.copy(
                secondPassword = password
            )
        }
    }

    fun onEmailChanged(email: String) {
        setState {
            state.value.copy(
                email = email
            )
        }
    }

    fun onShowPasswordText() {
        setState {
            state.value.copy(mustShowPassword = !state.value.mustShowPassword)
        }
    }

    fun onShowRepeatedPasswordText() {
        setState {
            state.value.copy(mustShowRepeatedPassword = !state.value.mustShowRepeatedPassword)
        }
    }

    fun onValidateRepeatedPassword() {
        // Check if the passwords match.
        if (state.value.password != state.value.secondPassword) {
            setState {
                it.copy(errorRepeatedPassword = true)
            }
            onNewError(SnackbarError.Builder(R.string.not_matching_password_error))
        } else {
            setState {
                it.copy(errorRepeatedPassword = false)
            }
        }
    }

    /**
     * Method to set in the [RegisterScreen] a new Error to be shown by the [BaseScaffold]
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