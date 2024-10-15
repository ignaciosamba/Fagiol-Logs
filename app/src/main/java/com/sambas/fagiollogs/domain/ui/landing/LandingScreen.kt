package com.sambas.fagiollogs.domain.ui.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sambas.fagiollogs.core.design.text.DesignText

@Composable
fun LandingScreen(){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        DesignText.body.Medium("LANDING")
    }
}