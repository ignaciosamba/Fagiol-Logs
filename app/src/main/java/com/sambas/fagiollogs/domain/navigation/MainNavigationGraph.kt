package com.sambas.fagiollogs.domain.navigation

import android.os.Parcelable
import com.sambas.fagiollogs.core.navigation.AnimatedNavigationNode
import com.sambas.fagiollogs.core.navigation.NavDestination
import com.sambas.fagiollogs.core.navigation.NavDestinationWithArgs
import com.sambas.fagiollogs.core.navigation.NavigationGraph
import com.sambas.fagiollogs.core.navigation.NavigationNodeNew
import com.sambas.fagiollogs.core.navigation.VerticalTransition
import com.sambas.fagiollogs.core.navigation.findDestinations
import kotlinx.parcelize.Parcelize

internal interface MainNavigationGraph : NavigationNodeNew, AnimatedNavigationNode {

    companion object : NavigationGraph<MainNavigationGraph>() {
        override val destinations = findDestinations()
        override val startDestination = SplashScreenDestination
    }

    object SplashScreenDestination :
        NavDestination(),
        MainNavigationGraph {
        override val transition = VerticalTransition
    }

    object LogInScreenDestination :
        NavDestinationWithArgs<LogInScreenDestination.Args>(),
        MainNavigationGraph {
        override val transition = VerticalTransition

        @Parcelize
        data class Args(
            val isLoggedIn: Boolean,
        ) : Parcelable
    }

    object RegisterScreenDestination :
        NavDestination(),
        MainNavigationGraph {
        override val transition = VerticalTransition
    }
}
