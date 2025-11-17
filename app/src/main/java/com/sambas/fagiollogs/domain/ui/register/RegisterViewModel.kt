package com.sambas.fagiollogs.domain.ui.register

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.error.SnackbarError
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.viewmodel.AuthenticationBaseViewModel
import com.sambas.fagiollogs.domain.exception.PasswordNotMatchingException
import com.sambas.fagiollogs.domain.utils.extractPasswordRequirementsFromFirebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val TAG = "RegisterViewModel"

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
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
                if (name.isNotBlank()) {
                    // Register the user and password.
                    val result = auth.createUserWithEmailAndPassword(email, password).await()
                    // Update the user's profile with the provided name.
                    // It's good practice to ensure the user object is not null
                    result.user?.let { firebaseUser ->
                        val profileUpdate = UserProfileChangeRequest.Builder()
                            .setDisplayName(name)
                            .build()
                        firebaseUser.updateProfile(profileUpdate).await()
                    }
                        ?: throw IllegalStateException("User was null after creation, this should not happen.")
                    result // Return the AuthResult
                } else {
                    setState {
                        it.copy(errorUserName = true)
                    }
                    throw IllegalArgumentException("Name cannot be empty")
                }
            },
            onSuccess = {
                setState { state ->
                    state.copy(
                        // Review: If registration is successful, errors should typically be cleared.
                        // errorEmail = true, // This line might need review based on your logic
                        errorPassword = null,
                        errorRepeatedPassword = false
                    )
                }
                emitEvent(RegisterUiEvent.RegistrationSuccess)
            },
            onError = { exception ->
                when (exception) {
                    is PasswordNotMatchingException -> {
                        onNewError(SnackbarError.Builder(R.string.not_matching_password_error))
                    }

                    is FirebaseAuthInvalidCredentialsException -> {
                        setState { state ->
                            state.copy(
                                errorEmail = true
                            )
                        }
                        onNewError(SnackbarError.Builder(R.string.email_not_valid_error))
                    }

                    else -> {
                        val errorMessage =
                            extractPasswordRequirementsFromFirebase(exception.localizedMessage)
                        setState { state ->
                            state.copy(
                                errorPassword = errorMessage
                            )
                        }
                        onNewError(errorMessage?.let { message ->
                            SnackbarError.Builder(message)
                        } ?: SnackbarError.Builder(R.string.generic_error_text))
                    }
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

    fun saveNewUserToFirestore() {
        // Assuming launchAuthenticationNetworkCall handles loading states and general error scenarios
        launchAuthenticationNetworkCall(
            action = {
                val userId = authManager.getCurrentUser()?.uid
                if (userId.isNullOrEmpty()) {
                    Log.w(TAG, "User ID is null or empty, cannot save to Firestore.")
                    // This throw will be caught by onError in launchAuthenticationNetworkCall
                    throw IllegalStateException("User ID not available to save user data.")
                }

                val currentUser = authManager.getCurrentUser() // Get user once
                val data = hashMapOf(
                    "userId" to userId,
                    "name" to currentUser?.displayName.orEmpty(),
                    "email" to currentUser?.email.orEmpty(),
                    "timestamp" to FieldValue.serverTimestamp()
                )

                firestore.collection("users")
                    .document(userId)
                    .set(data, SetOptions.merge())
                    .await() // Wait for the operation to complete

                Log.d(TAG, "DocumentSnapshot successfully written for user ID: $userId")
                // Return Unit or any relevant result if your launchAuthenticationNetworkCall expects it
            },
            onSuccess = {
                Log.d(TAG, "Successfully saved new user to Firestore.")
                // Emit an event or update UI state here if needed
            },
            onError = { exception ->
                Log.e(TAG, "Error writing document to Firestore", exception)
                onNewError(SnackbarError.Builder(R.string.generic_error_text))
            }
        )
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