package com.sambas.fagiollogs.domain.navigation

import com.sambas.fagiollogs.core.navigation.AnimatedNavigationNode
import com.sambas.fagiollogs.core.navigation.HorizontalTransition
import com.sambas.fagiollogs.core.navigation.NavDestination
import com.sambas.fagiollogs.core.navigation.NavigationGraph
import com.sambas.fagiollogs.core.navigation.NavigationNodeNew
import com.sambas.fagiollogs.core.navigation.VerticalTransition
import com.sambas.fagiollogs.core.navigation.findDestinations
import com.sambas.fagiollogs.domain.navigation.AccessNavigationGraph.SplashScreenDestination

internal interface MainNavigationGraph : NavigationNodeNew, AnimatedNavigationNode {

    companion object : NavigationGraph<MainNavigationGraph>() {
        override val destinations = findDestinations()
        override val startDestination = LandingScreenDestination
    }

    object LandingScreenDestination :
        NavDestination(),
        MainNavigationGraph {
        override val transition = HorizontalTransition
    }

    object StatsScreenDestination :
        NavDestination(),
        MainNavigationGraph {
        override val transition = HorizontalTransition
    }

    object SettingsScreenDestination :
        NavDestination(),
        MainNavigationGraph {
        override val transition = HorizontalTransition
    }
}