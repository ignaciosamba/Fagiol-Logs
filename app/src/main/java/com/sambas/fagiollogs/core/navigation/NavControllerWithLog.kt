package com.sambas.fagiollogs.core.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


/**
 * Create a [NavHostController] with [addDestinationChangeLogger] applied.
 */
@SuppressLint("NavControllerDetector")
@Composable
fun rememberNavControllerWithLogger(): NavHostController {
    val navController = rememberNavController()
    DisposableEffect(navController) {
        val listener = navController.addDestinationChangeLogger()
        onDispose { navController.removeOnDestinationChangedListener(listener) }
    }
    return navController
}
