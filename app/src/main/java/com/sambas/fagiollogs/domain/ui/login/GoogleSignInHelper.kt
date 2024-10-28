package com.sambas.fagiollogs.domain.ui.login

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import javax.inject.Inject

class GoogleSignInHelper @Inject constructor(
    private val context: Context
) {
    private val credentialManager: CredentialManager = CredentialManager.create(context)

    suspend fun initiateGoogleSignIn(clientId: String): GoogleIdTokenCredential {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(clientId)
            .setNonce(null) // Optional: Add nonce if needed for additional security
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        val result = credentialManager.getCredential(
            request = request,
            context = context
        )

        val googleIdTokenCredential = handleSignInResult(result)
            ?: throw Exception("Failed to get Google credential")

        return googleIdTokenCredential
    }

    private fun handleSignInResult(result: GetCredentialResponse): GoogleIdTokenCredential? {
        val credential = result.credential
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            try {
                return GoogleIdTokenCredential.createFrom(credential.data)
            } catch (e: GoogleIdTokenParsingException) {
                throw Exception("Failed to parse Google ID token: ${e.message}")
            }
        }
        return null
    }
}