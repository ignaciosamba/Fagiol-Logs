package com.sambas.fagiollogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sambas.fagiollogs.core.design.theme.ComposeDesignTheme
import com.sambas.fagiollogs.domain.navigation.MainNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
public class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeDesignTheme(fullScreen = true) {
                MainNavHost(
                    onBackPressed = onBackPressedDispatcher::onBackPressed,
                    onClose = ::finish,
                )
            }
        }
    }
}
