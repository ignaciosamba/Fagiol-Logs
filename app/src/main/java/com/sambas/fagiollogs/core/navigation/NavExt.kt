package com.sambas.fagiollogs.core.navigation

import android.util.Log
import androidx.navigation.NavController

fun NavController.addDestinationChangeLogger(): NavController.OnDestinationChangedListener {
    val listener = NavController.OnDestinationChangedListener { _, destination, arguments ->
        Log.d("ScreenView","Destination: $destination")

        // Verbose logs are only local, arguments can have data that should
        // not be included in crash reports.
        Log.d("ScreenView","Destination arguments: $arguments")
    }
    addOnDestinationChangedListener(listener)
    return listener
}