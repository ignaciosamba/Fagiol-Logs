package com.sambas.fagiollogs.domain.navigation

import NavHost
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
import com.sambas.fagiollogs.domain.ui.settings.SettingScreen
import com.sambas.fagiollogs.domain.ui.settings.SettingViewModel
import com.sambas.fagiollogs.domain.ui.settings.SettingsUiEvent
import com.sambas.fagiollogs.domain.ui.settings.SettingsUiState
import com.sambas.fagiollogs.domain.ui.landing.LandingViewModel
import com.sambas.fagiollogs.domain.ui.settings.SettingsOptions

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
                                rootNavController = rootNavController,
                                onBackPressed = onBackPressed
                            )
                        }

                        is MainNavigationGraph.StatsScreenDestination -> {
                            statsScreen(
                                destination = destination,
                                navController = navController,
                                rootNavController = rootNavController,
                                onBackPressed = onBackPressed
                            )
                        }

                        is MainNavigationGraph.SettingsScreenDestination -> {
                            settingsScreen(
                                destination = destination,
                                navController = navController,
                                rootNavController = rootNavController,
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
    rootNavController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) {
        val viewModel: LandingViewModel = hiltViewModel()
        LandingScreen()
    }
}

private fun NavGraphBuilder.statsScreen(
    destination: MainNavigationGraph.StatsScreenDestination,
    navController: NavController,
    rootNavController: NavController,
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
    rootNavController: NavController,
    onBackPressed: () -> Unit
) {
    composable(destination) {
        // Will be StatsScreen()
        val viewModel: SettingViewModel = hiltViewModel()
        val state = viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(viewModel) {
            viewModel.events.collect { event ->
                when (event) {
                    SettingsUiEvent.LogoutSuccess -> {
                        rootNavController.navigate(
                            from = RootNavigationGraph.MainGraphDestination,
                            navigationUri = RootNavigationGraph.AccessGraphDestination.navigationUri(),
                            navOptions = navOptions {
                                popUpTo(RootNavigationGraph.MainGraphDestination) {
                                    inclusive = true
                                }
                            }
                        )
                    }
                }
            }
        }
        SettingScreen(
            settingsUiState = state.value,
            onLogoutClick = viewModel::logOut,
            onBackPressed = onBackPressed,
            onToggleClick = viewModel::onToggleClick,
            onOptionClick = viewModel::onToggleClick
        )
    }
}
