package com.sambas.fagiollogs.domain.login

import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.dialog.DialogBase
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    override val loadingModel: LoadingModel = LoadingModel.disable,
    override val error: ErrorBase? = null,
    override val message: SnackBarGeneric? = null,
    override val dialog: DialogBase? = null,
) : BaseUiState