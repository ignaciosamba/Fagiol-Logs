package com.sambas.fagiollogs.domain.ui.register

import androidx.lifecycle.SavedStateHandle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.error.SnackbarError
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.viewmodel.BaseViewModel
import com.sambas.fagiollogs.domain.exception.PasswordNotMatchingException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<RegisterUiState, RegisterUiEvent>(
    savedStateHandle = savedStateHandle,
    initialState = RegisterUiState(),
    loadingStateUpdater = { state, loadingType -> state.copy(loadingModel = loadingType.toLoadingModel()) }
) {

    fun registerUser(name: String, email: String, password: String) {
        launchNetworkCall(
            action = {
                // Check if the passwords match.
                if (password != state.value.secondPassword) {
                    throw PasswordNotMatchingException()
                }
                // Register the user and password.
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val profileUpdate = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                result.user?.updateProfile(profileUpdate)?.await()
                result
            },
            onSuccess = { emitEvent(RegisterUiEvent.RegistrationSuccess) },
            onError = {
                if (it is PasswordNotMatchingException) {
                    onNewError(SnackbarError.Builder(R.string.not_matching_password_error))
                } else {
                    emitEvent(
                        RegisterUiEvent.RegistrationError(
                            it.message ?: "Registration failed"
                        )
                    )
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

    /**
     * Method to set in the [RegisterScreen] a new Error to be shown by the [TelepassScaffold]
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