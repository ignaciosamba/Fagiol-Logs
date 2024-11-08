package com.sambas.fagiollogs.domain.navigation

import NavHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sambas.fagiollogs.core.navigation.composable
import com.sambas.fagiollogs.core.navigation.navigation
import com.sambas.fagiollogs.core.navigation.rememberNavControllerWithLogger

@Composable
fun RootNavHost(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onClose: () -> Unit
) {
    val rootNavController = rememberNavControllerWithLogger()

    NavHost(
        navController = rootNavController,
        graph = RootNavigationGraph,
        modifier = modifier
    ) {
        navigation(graph = RootNavigationGraph) {
            for (destination in RootNavigationGraph.destinations) {
                when (destination) {
                    RootNavigationGraph.AccessGraphDestination -> {
                        composable(RootNavigationGraph.AccessGraphDestination) {
                            AccessNavHost(
                                modifier = modifier,
                                onBackPressed = onBackPressed,
                                onClose = onClose,
                                rootNavController = rootNavController
                            )
                        }
                    }

                    RootNavigationGraph.MainGraphDestination ->
                        composable(RootNavigationGraph.MainGraphDestination) {
                            MainNavHost(
                                modifier = modifier,
                                onBackPressed = onBackPressed,
                                onClose = onClose,
                                rootNavController = rootNavController
                            )
                        }
                }
            }
        }
    }
}