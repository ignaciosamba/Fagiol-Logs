package com.sambas.fagiollogs.core.navigation

import androidx.navigation.NamedNavArgument

/**
 * A navigation graph.
 */
public abstract class NavigationGraph<T : NavigationNodeNew> : BaseNavGraph<T> {

    /**
     * The route of this navigation graph.
     */
    override val route: NavRoute = generateRoute()

    /**
     * The navigation URI of this graph.
     */
    public open fun navigationUri(): NavUri = NavUri(route.value)

    /**
     * A list of all the arguments of this node.
     */
    override val arguments: List<NamedNavArgument> = emptyList()
}