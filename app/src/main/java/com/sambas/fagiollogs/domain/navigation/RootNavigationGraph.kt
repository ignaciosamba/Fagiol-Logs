package com.sambas.fagiollogs.domain.navigation

import com.sambas.fagiollogs.core.navigation.AnimatedNavigationNode
import com.sambas.fagiollogs.core.navigation.NavDestination
import com.sambas.fagiollogs.core.navigation.NavigationGraph
import com.sambas.fagiollogs.core.navigation.NavigationNodeNew
import com.sambas.fagiollogs.core.navigation.VerticalTransition
import com.sambas.fagiollogs.core.navigation.findDestinations

internal interface RootNavigationGraph: NavigationNodeNew, AnimatedNavigationNode {

    companion object : NavigationGraph<RootNavigationGraph>() {
        override val destinations = findDestinations()
        override val startDestination = AccessGraphDestination
    }

    object AccessGraphDestination :
        NavDestination(),
        RootNavigationGraph {
        override val transition = VerticalTransition
    }

    object MainGraphDestination :
        NavDestination(),
        RootNavigationGraph {
        override val transition = VerticalTransition
    }
}
