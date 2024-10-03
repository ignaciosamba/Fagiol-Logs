package com.sambas.fagiollogs.core.navigation

import androidx.navigation.NavGraphBuilder

public class NavGraphBuilder internal constructor(
    internal val navGraphBuilder: NavGraphBuilder,
    internal val transitions: MutableMap<String, NavigationNodeTransition>,
)