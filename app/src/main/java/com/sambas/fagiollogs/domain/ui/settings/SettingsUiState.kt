package com.sambas.fagiollogs.domain.ui.settings

import android.os.Parcelable
import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.dialog.DialogBase
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class SettingsUiState(
    @IgnoredOnParcel
    override val loadingModel: LoadingModel = LoadingModel.disable,
    @IgnoredOnParcel
    override val error: ErrorBase? = null,
    @IgnoredOnParcel
    override val message: SnackBarGeneric? = null,
    @IgnoredOnParcel
    override val dialog: DialogBase? = null,
): BaseUiState, Parcelable