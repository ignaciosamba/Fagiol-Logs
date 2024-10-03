package com.sambas.fagiollogs.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavBackStackEntry


public interface NavigationNodeTransition {
    public val enter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition
    public val exit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
    public val popEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition
    public val popExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition
}