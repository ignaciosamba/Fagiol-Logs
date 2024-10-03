package com.sambas.fagiollogs.core.design

import androidx.compose.runtime.Immutable
import com.sambas.fagiollogs.core.design.dialog.DialogBase
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric

@Immutable
public interface BaseUiState {
    public val loadingModel: LoadingModel
    public val error: ErrorBase?
    public val message: SnackBarGeneric?
    public val dialog: DialogBase?

    public companion object {
        public operator fun invoke(
            loadingModel: LoadingModel,
            error: ErrorBase?,
            message: SnackBarGeneric? = null,
            dialog: DialogBase? = null,
        ): BaseUiState = BaseUiStateImpl(loadingModel, error, message, dialog)
    }
}

@Immutable
private data class BaseUiStateImpl(
    override val loadingModel: LoadingModel,
    override val error: ErrorBase?,
    override val message: SnackBarGeneric?,
    override val dialog: DialogBase?,
) : BaseUiState