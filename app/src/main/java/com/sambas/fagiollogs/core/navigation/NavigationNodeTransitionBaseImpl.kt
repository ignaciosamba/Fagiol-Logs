package com.sambas.fagiollogs.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

const val NAVIGATION_ANIMATION_DURATION = 400
const val NAVIGATION_MODAL_DURATION = 400
val linearToEaseOut: Easing = CubicBezierEasing(0.35f, 0.91f, 0.33f, 0.97f)

object NoTransition : NavigationNodeTransition {
    override val enter: AnimatedContentTransitionScope<*>.() -> EnterTransition = { EnterTransition.None }
    override val exit: AnimatedContentTransitionScope<*>.() -> ExitTransition = { ExitTransition.None }
    override val popEnter: AnimatedContentTransitionScope<*>.() -> EnterTransition = { EnterTransition.None }
    override val popExit: AnimatedContentTransitionScope<*>.() -> ExitTransition = { ExitTransition.None }
}

object HorizontalTransition : NavigationNodeTransition {
    override val enter: AnimatedContentTransitionScope<*>.() -> EnterTransition = enterHorizontally
    override val exit: AnimatedContentTransitionScope<*>.() -> ExitTransition = exitHorizontally
    override val popEnter: AnimatedContentTransitionScope<*>.() -> EnterTransition = popEnterHorizontally
    override val popExit: AnimatedContentTransitionScope<*>.() -> ExitTransition = popExitHorizontally
}

object VerticalTransition : NavigationNodeTransition {
    override val enter: AnimatedContentTransitionScope<*>.() -> EnterTransition = enterVertically
    override val exit: AnimatedContentTransitionScope<*>.() -> ExitTransition = exitVertically
    override val popEnter: AnimatedContentTransitionScope<*>.() -> EnterTransition = popEnterVertically
    override val popExit: AnimatedContentTransitionScope<*>.() -> ExitTransition = popExitVertically
}

private val enterHorizontally: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(NAVIGATION_ANIMATION_DURATION, easing = linearToEaseOut),
    )
}

private val enterVertically: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Up,
        animationSpec = tween(NAVIGATION_MODAL_DURATION, easing = linearToEaseOut),
    )
}

private val exitHorizontally: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(NAVIGATION_ANIMATION_DURATION, easing = linearToEaseOut),
    )
}

private val exitVertically: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
    fadeOut(
        targetAlpha = 0.999f,
        animationSpec = tween(NAVIGATION_MODAL_DURATION),
    )
}

private val popEnterHorizontally: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
    slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(NAVIGATION_ANIMATION_DURATION, easing = linearToEaseOut),
    )
}

private val popEnterVertically: AnimatedContentTransitionScope<*>.() -> EnterTransition = {
    fadeIn(
        initialAlpha = 0.999f,
        animationSpec = tween(NAVIGATION_MODAL_DURATION),
    )
}

private val popExitHorizontally: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(NAVIGATION_ANIMATION_DURATION, easing = linearToEaseOut),
    )
}

private val popExitVertically: AnimatedContentTransitionScope<*>.() -> ExitTransition = {
    slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Down,
        animationSpec = tween(NAVIGATION_MODAL_DURATION, easing = linearToEaseOut),
    )
}
