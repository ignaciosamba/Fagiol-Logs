package com.sambas.fagiollogs.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

/**
 * Add the [Composable] to the [NavGraphBuilder].
 *
 * This is a wrapper of [androidx.navigation.compose.composable].
 */
public fun NavGraphBuilder.composable(
    node: NavigationNodeNew,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit,
) {
    if (node is AnimatedNavigationNode) {
        transitions[node.route.value] = node.transition
    }
    navGraphBuilder.composable(
        route = node.route.value,
        arguments = node.arguments,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content,
    )
}

/**
 * Construct a nested [NavGraph].
 *
 * This is a wrapper of [androidx.navigation.compose.navigation].
 */
public fun <Graph : BaseNavGraph<*>> NavGraphBuilder.navigation(
    graph: Graph,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null,
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null,
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = exitTransition,
    builder: NavGraphBuilder.(Graph) -> Unit,
) {
    navGraphBuilder.navigation(
        startDestination = graph.startDestination.route.value,
        route = graph.route.value,
        arguments = graph.arguments,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        builder = { builder(NavGraphBuilder(this, transitions), graph) },
    )
}