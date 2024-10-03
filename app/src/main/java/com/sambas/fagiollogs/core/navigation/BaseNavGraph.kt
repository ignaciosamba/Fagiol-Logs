package com.sambas.fagiollogs.core.navigation

public interface BaseNavGraph<T : NavigationNodeNew> : NavigationNodeNew {

    /**
     * The start destination of this navigation graph.
     */
    public val startDestination: T

    /**
     * The complete list of destinations of this navigation graph.
     */
    public val destinations: List<T>
}