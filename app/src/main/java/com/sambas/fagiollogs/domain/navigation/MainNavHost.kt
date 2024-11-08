package com.sambas.fagiollogs.domain.navigation

import NavHost
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.sambas.fagiollogs.core.design.navigationbar.BottomBarDestination
import com.sambas.fagiollogs.core.design.navigationbar.BottomNavigationBar
import com.sambas.fagiollogs.core.navigation.NavGraphBuilder
import com.sambas.fagiollogs.core.navigation.composable
import com.sambas.fagiollogs.core.navigation.navigate
import com.sambas.fagiollogs.core.navigation.navigation
import com.sambas.fagiollogs.core.navigation.popUpTo
import com.sambas.fagiollogs.core.navigation.rememberNavControllerWithLogger
import com.sambas.fagiollogs.domain.ui.landing.LandingScreen

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    onBackPressed: () -> Unit,
    onClose: () -> Unit,
    rootNavController: NavController
) {
    val navController = rememberNavControllerWithLogger()

    Scaffold(
        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                onClickTab = { route, originDestination ->
                    when (route) {
                        BottomBarDestination.HOME -> {
                            navController.navigate(
                                from = originDestination,
                                navigationUri = MainNavigationGraph.LandingScreenDestination.navigationUri(),
                                navOptions = navOptions {
                                    popUpTo(originDestination) {
                                        inclusive = true
                                    }
                                }
                            )
                        }

                        BottomBarDestination.STATS -> {
                            navController.navigate(
                                from = originDestination,
                                navigationUri = MainNavigationGraph.StatsScreenDestination.navigationUri(),
                                navOptions = navOptions {
                                    popUpTo(originDestination) {
                                        inclusive = true
                                    }
                                }
                            )
                        }

                        BottomBarDestination.SETTINGS -> {
                            navController.navigate(
                                from = originDestination,
                                navigationUri = MainNavigationGraph.SettingsScreenDestination.navigationUri(),
                                navOptions = navOptions {
                                    popUpTo(originDestination) {
                                        inclusive = true
                                    }
                                }
                            )
                        }
                    }

                }
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            graph = MainNavigationGraph,
            modifier = modifier.fillMaxSize()
        ) {
            navigation(graph = MainNavigationGraph) {
                for (destination in MainNavigationGraph.destinations) {
                    when (destination) {
                        is MainNavigationGraph.LandingScreenDestination -> {
                            landingScreen(
                                destination = destination,
                                navController = navController,
                                onBackPressed = onBackPressed
                            )
                        }

                        is MainNavigationGraph.StatsScreenDestination -> {
                            statsScreen(
                                destination = destination,
                                navController = navController,
                                onBackPressed = onBackPressed
                            )
                        }

                        is MainNavigationGraph.SettingsScreenDestination -> {
                            settingsScreen(
                                destination = destination,
                                navController = navController,
                                onBackPressed = onBackPressed
                            )
                        }
                    }
                }
            }
        }
    }

}

private fun NavGraphBuilder.landingScreen(
    destination: MainNavigationGraph.LandingScreenDestination,
    navController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) {
        LandingScreen()
    }
}

private fun NavGraphBuilder.statsScreen(
    destination: MainNavigationGraph.StatsScreenDestination,
    navController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) {
        // Will be StatsScreen()
        LandingScreen(text = "STATS")
    }
}

private fun NavGraphBuilder.settingsScreen(
    destination: MainNavigationGraph.SettingsScreenDestination,
    navController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) {
        // Will be StatsScreen()
        LandingScreen(text = "SETTINGS")
    }
}
