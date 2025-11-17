package com.sambas.fagiollogs.domain.ui.landing

import androidx.annotation.DrawableRes
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.dialog.DialogBase
import com.sambas.fagiollogs.core.design.error.ErrorBase
import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import com.sambas.fagiollogs.core.design.snackbar.SnackBarGeneric

/**
 * Data class representing a baby-related event
 */
data class BabyEvent(
    val id: String,
    val type: BabyEventType,
    val description: String,
    val time: String
)

enum class BabyEventType(
    val displayName: String,
    @DrawableRes val iconResId: Int
) {
    FEEDING("Feeding", R.drawable.ic_bottle),
    DIAPER("Diaper Change", R.drawable.ic_diaper),
    SLEEP("Sleep", R.drawable.ic_sleep),
    OTHER("Other", R.drawable.ic_calendar_minus);

    companion object {
        fun fromString(type: String): BabyEventType {
            return when (type.lowercase()) {
                "feeding" -> FEEDING
                "diaper", "diaper change" -> DIAPER
                "sleep" -> SLEEP
                else -> OTHER
            }
        }
    }
}

internal data class LandingUiState(
    val babyName: String = "Fagiols",
    val recentEvents: List<BabyEvent> = emptyList(),
    override val loadingModel: LoadingModel = LoadingModel.disable,
    override val error: ErrorBase? = null,
    override val dialog: DialogBase? = null,
    override val message: SnackBarGeneric? = null,
) : BaseUiState