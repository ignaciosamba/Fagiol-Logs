package com.sambas.fagiollogs.core.autentication

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val auth: FirebaseAuth
) : DefaultLifecycleObserver {

    private val _authState = MutableStateFlow<AuthState>(AuthState.NotAuthenticated)
    val authState: StateFlow<AuthState> = _authState

    private var authStateListener: FirebaseAuth.AuthStateListener? = null

    init {
        setupAuthStateListener()
        setupLifecycleObserver()
    }

    /**
     * Listens for changes in the FirebaseAuth instance and updates the authState accordingly.
     * This is useful to keep the authState up-to-date when the user logs in or out from other devices
     * and also to prevent the authState from becoming NOT_AUTHENTICATED when the user is not logged in yet.
     */
    private fun setupAuthStateListener() {
        authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                refreshToken()
            } else {
                _authState.value = AuthState.NotAuthenticated
            }
        }
        // Eventually we can check the authState and trigger a re-check authentication or maybe an exception.
        auth.addAuthStateListener(authStateListener!!)
    }

    /**
     * Setup the observe to be consume from the app.
     */
    private fun setupLifecycleObserver() {
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        refreshToken()
    }

    private fun refreshToken() {
        val user = auth.currentUser
        if (user != null) {
            user.getIdToken(true)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val token = task.result?.token
                        _authState.value = AuthState.Authenticated(token)
                    } else {
                        _authState.value = AuthState.Error("Failed to refresh token")
                    }
                }
        } else {
            _authState.value = AuthState.NotAuthenticated
        }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = AuthState.NotAuthenticated
    }

    fun cleanup() {
        authStateListener?.let { auth.removeAuthStateListener(it) }
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
    }
}

sealed class AuthState {
    data object NotAuthenticated : AuthState()
    data class Authenticated(val token: String?) : AuthState()
    data class Error(val message: String) : AuthState()
}