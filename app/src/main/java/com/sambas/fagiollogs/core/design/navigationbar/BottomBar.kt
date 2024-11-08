package com.sambas.fagiollogs.core.design.navigationbar

import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.navigation.NavDestination
import com.sambas.fagiollogs.domain.navigation.MainNavigationGraph

internal enum class BottomBarDestination {
    HOME,
    STATS,
    SETTINGS
}

internal sealed class BottomNavItem(
    val destination: NavDestination,
    val route: BottomBarDestination,
    val title: String,
    val icon: Int
) {
    data object Home : BottomNavItem(
        destination = MainNavigationGraph.LandingScreenDestination,
        route = BottomBarDestination.HOME,
        title = "Home",
        icon = R.drawable.ic_calendar_minus
    )

    data object Stats : BottomNavItem(
        destination = MainNavigationGraph.StatsScreenDestination,
        route = BottomBarDestination.STATS,
        title = "Stats",
        icon = R.drawable.ic_stats
    )

    data object Settings : BottomNavItem(
        destination = MainNavigationGraph.SettingsScreenDestination,
        route = BottomBarDestination.SETTINGS,
        title = "Settings",
        icon = R.drawable.ic_settings
    )
}

@Composable
internal fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onClickTab: (BottomBarDestination, NavDestination) -> Unit
) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Stats,
        BottomNavItem.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentNavDestination = screens.find {
        it.destination.route.value == currentDestination?.route
    }

    NavigationBar(
        modifier = modifier.height(60.dp),
        containerColor = DesignTheme.colors.backgroundActionPrimary,
        tonalElevation = DesignTheme.assetDimen.dimen_mini
    ) {
        screens.forEach { screen ->
            val selected = when (screen.route) {
                BottomBarDestination.HOME ->
                    currentDestination?.route == MainNavigationGraph.LandingScreenDestination.route.value
                BottomBarDestination.STATS ->
                    currentDestination?.route == MainNavigationGraph.StatsScreenDestination.route.value
                BottomBarDestination.SETTINGS ->
                    currentDestination?.route == MainNavigationGraph.SettingsScreenDestination.route.value
            }

            NavBarItem(
                modifier = Modifier.weight(1f),
                icon = screen.icon,
                label = screen.title,
                selected = selected,
                onClick = { currentNavDestination?.let { onClickTab(screen.route, it.destination) } }
            )
        }
    }
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    PreviewTheme(true) {
        BottomNavigationBar(
            navController = rememberNavController(),
            onClickTab = {_, _ -> })
    }
}