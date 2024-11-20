package com.sambas.fagiollogs.domain.ui.settings

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.lifecycle.SavedStateHandle
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.autentication.AuthManager
import com.sambas.fagiollogs.core.design.dialog.DialogBase
import com.sambas.fagiollogs.core.design.dialog.GenericDialogButton
import com.sambas.fagiollogs.core.design.dialog.SelectorDialog
import com.sambas.fagiollogs.core.design.dialog.TextDialog
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.error.SnackbarError
import com.sambas.fagiollogs.core.design.error.SnackbarGenericErrorBuilder
import com.sambas.fagiollogs.core.design.error.SnackbarNoConnectionGenericErrorBuilder
import com.sambas.fagiollogs.core.design.loader.toLoadingModel
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.viewmodel.AuthenticationBaseViewModel
import com.sambas.fagiollogs.domain.model.ThemeTypesEnum
import com.sambas.fagiollogs.domain.ui.login.LoginScreen
import com.sambas.fagiollogs.persistant.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle,
    authManager: AuthManager,
    private val appPreferences: AppPreferences,
) : AuthenticationBaseViewModel<SettingsUiState, SettingsUiEvent>(
    savedStateHandle = savedStateHandle,
    initialState = SettingsUiState(
        parentName = authManager.getCurrentUser()?.displayName.orEmpty(),
        parentEmail = authManager.getCurrentUser()?.email.orEmpty(),
        babyName = "Loving and Taking care of Fagiols"
    ),
    authManager = authManager,
    loadingStateUpdater = { state, loadingType -> state.copy(loadingModel = loadingType.toLoadingModel()) }
) {

    init {
        setState {
            it.copy(
                themeLabel = appPreferences.themeModeValue
            )
        }
    }

    fun logOut() {
        onNewDialog(
            DialogBase.Builder { dismiss ->
                TextDialog(
                    description = context.getString(R.string.logout_dialog_text),
                    onDismissRequest = dismiss,
                    positiveButton = GenericDialogButton(
                        text = context.getString(R.string.yes_button_text),
                        onClick = { authLogOut() }
                    ),
                    negativeButton = GenericDialogButton(
                        text = context.getString(R.string.cancel_button_text),
                        onClick = dismiss
                    )
                )

            }
        )
    }

    private fun authLogOut() {
        launchAuthenticationNetworkCall(
            action = {
                authManager.signOut()
            },
            onSuccess = {
                emitEvent(SettingsUiEvent.LogoutSuccess)
            },
            onError = {
                onNewError(SnackbarGenericErrorBuilder)
            },
            onNoConnection = {
                onNewError(SnackbarNoConnectionGenericErrorBuilder)
            }
        )
    }

    fun onToggleClick(option: SettingsOptions, isChecked: Boolean = false) {
        when (option) {
            SettingsOptions.LANGUAGE -> { /*nothing to do here*/ }
            SettingsOptions.THEME -> {
                val selectedDialogItem = state.value.themeLabel
                onNewDialog(
                    DialogBase.Builder{ dismiss ->
                        SelectorDialog(
                            title = context.getString(R.string.theme_dialog_title),
                            listOfItems = ThemeTypesEnum.entries,
                            selectedItem = selectedDialogItem,
                            optionTextSelected = { it.text },
                            onItemSelected = { themeSelected ->
                                setState {
                                    it.copy(themeLabel = themeSelected)
                                }
                            },
                            positiveButton = GenericDialogButton(
                                text = context.getString(R.string.accept_button_text),
                                onClick =  {
                                    // set the theme with the viewModel state
                                    appPreferences.themeModeValue = state.value.themeLabel
                                    dismiss()
                                }
                            ),
                            negativeButton = GenericDialogButton(
                                text = context.getString(R.string.cancel_button_text),
                                onClick = dismiss
                            ),
                        )
                    }
                )
            }
            SettingsOptions.NOTIFICATION -> {
                // Activate/deactivate notifications as preferences
                setState {
                    it.copy(notificationSelected = isChecked)
                }
            }
            SettingsOptions.METRIC_SYSTEM -> {
                // Set metric system as preferences
                setState {
                    it.copy(metricSelected = isChecked)
                }
            }
        }
    }

    private fun onNewDialog(dialog: DialogBase.Builder) {
        val newDialog = dialog.build {
            setState { state ->
                state.copy(dialog = state.dialog.takeIf { it != this })
            }
        }
        setState { it.copy(dialog = newDialog) }
    }

    private fun onNewError(errorMessage: SnackbarError.Builder) {
        val newError = errorMessage.build {
            setState { state ->
                state.copy(error = state.error.takeIf { it != this })
            }
        } as ErrorBase
        setState { it.copy(error = newError) }
    }
}