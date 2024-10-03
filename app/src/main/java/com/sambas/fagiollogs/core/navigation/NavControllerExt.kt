package com.sambas.fagiollogs.core.navigation

import android.os.Parcelable
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import androidx.navigation.PopUpToBuilder

/**
 * Navigate from the given [NavigationNode] with the given [NavigationUri].
 *
 * Nothing will happen if the current destination does not correspond to the given node,
 * unless [from] is null.
 */
public fun NavController.navigate(
    from: NavigationNodeNew?,
    navigationUri: NavUri,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
): Boolean {
    return navigate(from, navigationUri.uri) {
        navigate(navigationUri.uri, navOptions, navigatorExtras)
    }
}

/**
 * Navigate from the given [NavigationNodeNew] with the given [NavigationUriWithArgs].
 *
 * Nothing will happen if the current destination does not correspond to the given node,
 * unless [from] is null.
 */
public fun <ArgType : Parcelable> NavController.navigate(
    from: NavigationNodeNew?,
    navigationUriWithArgs: NavUriWithArgs<ArgType>,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null,
): Boolean {
    val uri = navigationUriWithArgs.uri

    NavConfig.argumentsDebugger?.invoke(navigationUriWithArgs.args)

    return navigate(from, uri) {
        navigate(uri, navOptions, navigatorExtras)
    }
}

private inline fun NavController.navigate(node: NavigationNodeNew?, with: String, block: () -> Unit): Boolean {
    val currentRoute = currentDestination?.route
    return if (node == null || currentRoute == node.route.value) {
        block()
        true
    } else {
        // This usually happens when tapping more than one button at once.
        val uriWithoutParams = with.substringBefore("?") // Remove the base64 encoded args.
//        NavConfig.logger?.logDebug(
//            "Ignoring uri=$uriWithoutParams, current route: $currentRoute, expected route: ${node.route.value}",
//        )
        false
    }
}

public fun NavController.popBackStack(
    destination: NavigationNodeNew,
    inclusive: Boolean,
    saveState: Boolean = false,
): Boolean = popBackStack(destination.route.value, inclusive, saveState)

public fun NavOptionsBuilder.popUpTo(destination: NavigationNodeNew, popUpToBuilder: PopUpToBuilder.() -> Unit = {}) {
    popUpTo(destination.route.value, popUpToBuilder)
}