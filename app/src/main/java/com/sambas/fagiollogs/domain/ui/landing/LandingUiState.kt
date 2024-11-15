package com.sambas.fagiollogs.domain.ui.landing

import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.dialog.DialogBase
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric

internal data class LandingUiState(
    val messageToTest: String = "",
    override val loadingModel: LoadingModel = LoadingModel.disable,
    override val error: ErrorBase? = null,
    override val dialog: DialogBase? = null,
    override val message: SnackBarGeneric? = null,
) : BaseUiState