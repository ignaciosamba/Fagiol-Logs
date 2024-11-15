package com.sambas.fagiollogs.domain.ui.landing

import com.sambas.fagiollogs.core.viewmodel.UiEvent

internal sealed class LandingUiEvent : UiEvent {
    // Will be use when the app load all the events from firestore
    data class LandingReady(val message: String) : LandingUiEvent()
}