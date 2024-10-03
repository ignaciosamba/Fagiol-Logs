package com.sambas.fagiollogs.core.navigation

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry

/**
 * Helper for extract destination arguments.
 */
public object BaseNavArgs {

    public fun <T : Parcelable> get(
        destination: NavDestinationWithArgs<T>,
        navBackStackEntry: NavBackStackEntry
    ): T {
        val arguments = requireNotNull(navBackStackEntry.arguments) { "Missing arguments" }
        val result = arguments.getParcelable<T>(destination.argsKey)
        return requireNotNull(result) { "Missing args key" }
    }

    public fun <T : Parcelable> getOrNull(
        destination: NavDestinationWithArgs<T>,
        navBackStackEntry: NavBackStackEntry,
    ): T? {
        return if (navBackStackEntry.arguments?.containsKey(destination.argsKey) == true) {
            get(destination, navBackStackEntry)
        } else {
            null
        }
    }

    public fun <T : Parcelable> get(
        destination: NavDestinationWithArgs<T>,
        savedStateHandle: SavedStateHandle,
    ): T {
        return requireNotNull(savedStateHandle.get<T>(destination.argsKey)) { "Missing args key" }
    }

    public fun <T : Parcelable> getOrNull(
        destination: NavDestinationWithArgs<T>,
        savedStateHandle: SavedStateHandle,
    ): T? {
        return if (savedStateHandle.contains(destination.argsKey)) {
            get(destination, savedStateHandle)
        } else {
            null
        }
    }
}