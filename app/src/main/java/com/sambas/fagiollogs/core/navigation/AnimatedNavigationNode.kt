package com.sambas.fagiollogs.core.navigation

/**
 * An animated navigation node.
 */
public interface AnimatedNavigationNode {
    public val route: NavRoute
    public val transition: NavigationNodeTransition
}