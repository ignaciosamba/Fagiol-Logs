package com.sambas.fagiollogs.domain.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme

@Composable
fun LandingScreen(text: String = "LANDING"){
    Box(modifier = Modifier.fillMaxSize().background(DesignTheme.colors.contentBackground),
        contentAlignment = Alignment.Center) {
        DesignText.body.Medium(text)
    }
}

@Preview
@Composable
fun LandingScreenPreview(){
    PreviewTheme(fullScreen = true, darkTheme = true) {
        LandingScreen()
    }
}