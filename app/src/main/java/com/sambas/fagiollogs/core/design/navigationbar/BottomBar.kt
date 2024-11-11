package com.sambas.fagiollogs.core.design.navigationbar

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
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
    HOME, STATS, SETTINGS
}

/**
 * List of option on the bottom navigation bar
 */
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

    companion object {
        val items = listOf(Home, Stats, Settings)
    }
}

private fun NavDestination.isCurrentDestination(currentRoute: String?) =
    route.value == currentRoute

@Composable
internal fun BottomNavigationBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onClickTab: (BottomBarDestination, NavDestination) -> Unit
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val currentNavItem = remember(currentRoute) {
        BottomNavItem.items.find { it.destination.route.value == currentRoute }
    }

    val configuration = LocalConfiguration.current
    val itemWidth = configuration.screenWidthDp.dp / BottomNavItem.items.size

    val selectedIndex = remember(currentRoute) {
        BottomNavItem.items.indexOfFirst {
            it.destination.isCurrentDestination(currentRoute)
        }.let {
            // To correct the offset when the first item is selected
            if (it == -1) 0 else (it - 1)
        }
    }

    val indicatorOffset by animateDpAsState(
        targetValue = itemWidth * selectedIndex,
        animationSpec = tween(durationMillis = 300, easing = EaseInOut),
        label = "indicator"
    )

    Box(modifier = modifier) {
        NavigationBar(
            modifier = modifier.height(60.dp),
            containerColor = DesignTheme.colors.contentWhite,
            contentColor = DesignTheme.colors.backgroundActionPrimary,
            tonalElevation = DesignTheme.assetDimen.dimen_mini,
        ) {
            BottomNavItem.items.forEach { item ->
                val isSelected = item.destination.isCurrentDestination(currentRoute)

                NavBarItem(
                    modifier = Modifier.weight(1f),
                    icon = item.icon,
                    label = item.title,
                    selected = isSelected,
                    onClick = {
                        if (currentNavItem != null && currentNavItem.route != item.route) {
                            onClickTab(item.route, currentNavItem.destination)
                        }
                    }
                )
            }
        }

        // Animated indicator
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(x = indicatorOffset)
                .width(itemWidth)
                .padding(top = 40.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                        color = DesignTheme.colors.backgroundActionPrimary,
                        shape = RoundedCornerShape(8.dp)
                    )
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
            onClickTab = { _, _ -> })
    }
}