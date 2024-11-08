package com.sambas.fagiollogs.domain.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme

@Composable
fun LandingScreen(text: String = "LANDING"){
    Box(modifier = Modifier.fillMaxSize().background(DesignTheme.colors.backgroundPrimary),
        contentAlignment = Alignment.Center) {
        DesignText.body.Medium(text)
    }
}