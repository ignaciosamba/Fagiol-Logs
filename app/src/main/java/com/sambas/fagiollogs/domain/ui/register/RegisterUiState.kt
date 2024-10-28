package com.sambas.fagiollogs.domain.ui.register

import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.dialog.DialogBase
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric

data class RegisterUiState (
    val email: String = "",
    val password: String = "",
    val secondPassword: String = "",
    val userName: String = "",
    val mustShowPassword: Boolean = false,
    val mustShowRepeatedPassword: Boolean = false,
    val errorEmail: Boolean = false,
    val errorPassword: String? = null,
    val errorRepeatedPassword: Boolean = false,
    override val loadingModel: LoadingModel = LoadingModel.disable,
    override val error: ErrorBase? = null,
    override val message: SnackBarGeneric? = null,
    override val dialog: DialogBase? = null,
) : BaseUiState