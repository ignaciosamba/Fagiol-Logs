import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sambas.fagiollogs.core.navigation.BaseNavGraph
import com.sambas.fagiollogs.core.navigation.NavGraphBuilder
import com.sambas.fagiollogs.core.navigation.NavigationNodeTransition

@Composable
public fun NavHost(
    navController: NavHostController,
    graph: BaseNavGraph<*>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = { fadeIn(tween(700)) },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = { fadeOut(tween(700)) },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = exitTransition,
    builder: NavGraphBuilder.() -> Unit
) {
    val routeToTransition = remember { mutableMapOf<String, NavigationNodeTransition>() }

    NavHost(
        navController = navController,
        startDestination = graph.route.value,
        modifier = modifier,
        contentAlignment = contentAlignment,
        route = null,
        enterTransition = {
            (routeToTransition[targetState.destination.route]?.enter ?: enterTransition).invoke(this)
        },
        exitTransition = {
            (routeToTransition[targetState.destination.route]?.exit ?: exitTransition).invoke(this)
        },
        popEnterTransition = {
            (routeToTransition[initialState.destination.route]?.popEnter ?: popEnterTransition).invoke(this)
        },
        popExitTransition = {
            (routeToTransition[initialState.destination.route]?.popExit ?: popExitTransition).invoke(this)
        },
        builder = remember {
            {
                builder(NavGraphBuilder(this, routeToTransition))
            }
        },
    )
}