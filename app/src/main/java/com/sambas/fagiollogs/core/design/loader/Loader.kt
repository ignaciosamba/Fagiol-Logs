package com.sambas.fagiollogs.core.design.loader

import android.util.Log
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.min

@Composable
internal fun Loader() {
    FagiolsLoader(
        loaderAnimation = rememberFagiolsLoaderAnimation(),
        modifier = Modifier
            .padding(9.dp)
            .size(64.dp),
    )
}

@Composable
private fun FagiolsLoader(
    loaderAnimation: LoaderAnimation,
    modifier: Modifier = Modifier,
) {
    Spacer(
        modifier = modifier
            .aspectRatio(1f)
            .padding(loaderAnimation.arcWidth / 2)
            .drawBehind {
                for (arcAnimation in loaderAnimation.arcAnimations) {
                    drawArc(
                        color = arcAnimation.color,
                        startAngle = readSafely { arcAnimation.offset.value - 90 },
                        sweepAngle = readSafely { arcAnimation.sweepAngle.value },
                        useCenter = false,
                        style = Stroke(
                            width = loaderAnimation.arcWidth.toPx(),
                            cap = StrokeCap.Round,
                        ),
                    )
                }
            },
    )
}

// For some reason reading the value of some States is throwing NPEs.
// https://issuetracker.google.com/issues/288499771
private inline fun readSafely(block: () -> Float): Float {
    return runCatching { block() }
        .getOrElse { error ->
            Log.e(error.localizedMessage, "Could not read the value, defaulting to 0")
            0f
        }
}

@Stable
private class LoaderAnimation(
    val arcAnimations: List<ArcAnimation>,
    val arcWidth: Dp,
)

@Stable
private interface ArcAnimation {
    val color: Color
    val offset: State<Float>
    val sweepAngle: State<Float>
}

private val fagiolsColors = listOf(
    Color(0xFFE64E1F),
    Color(0xFFD18E2A),
    Color(0xFFFADC17),
)

@Stable
@Composable
private fun rememberFagiolsLoaderAnimation(
    colors: List<Color> = fagiolsColors,
    arcWidth: Dp = 5.dp,
    duration: Int = 1280,
): LoaderAnimation {
    val arcAnimations = remember {
        colors.map { MutableArcAnimation(it) }
    }

    val finalPause = duration / 4
    val arcAnimationDuration = duration - finalPause

    InfiniteAnimation {
        arcAnimations.withIndex().map { (index, arcAnimation) ->
            launch {
                val sweepAngle = arcAnimation.sweepAngle
                val offset = arcAnimation.offset

                // Magic values obtained after some trial and error.
                val easing = CubicBezierEasing(0.25f, 0f, 0.65f, 0.9f)
                val offsetDelay = (arcAnimationDuration / 4f).toInt()
                val arcAnimationDelay = (arcAnimationDuration / 7) * index

                launch {
                    animate(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = tween(
                            durationMillis = arcAnimationDuration,
                            easing = easing,
                            delayMillis = arcAnimationDelay,
                        ),
                    ) { value, _ ->
                        sweepAngle.value = value
                    }
                }

                launch {
                    animate(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = tween(
                            durationMillis = arcAnimationDuration,
                            easing = easing,
                            delayMillis = offsetDelay + arcAnimationDelay,
                        ),
                    ) { value, _ ->
                        offset.value = value
                    }
                }
            }
        }.joinAll()
        delay(finalPause.toLong())
    }

    return remember(arcAnimations) {
        LoaderAnimation(
            arcAnimations = arcAnimations
                .map { it.toCoercedArcAnimation() }
                .reversed(), // Reverse the list to to respect the z-index.
            arcWidth = arcWidth,
        )
    }
}

private fun ArcAnimation.toCoercedArcAnimation(): ArcAnimation {
    // The sweep angle needs to be adjusted depending on the offset and it should never be
    // bigger than 360f. Applying this constraint at the end simplifies the code.
    val sweepAngle = derivedStateOf { min(360f, sweepAngle.value - offset.value) }
    return ArcAnimationImpl(
        color = color,
        offset = offset,
        sweepAngle = sweepAngle,
    )
}

@Stable
private class ArcAnimationImpl(
    override val color: Color,
    override val offset: State<Float>,
    override val sweepAngle: State<Float>,
) : ArcAnimation

private class MutableArcAnimation(
    override val color: Color,
    override val offset: MutableState<Float> = mutableStateOf(0f),
    override val sweepAngle: MutableState<Float> = mutableStateOf(0f),
) : ArcAnimation

@Composable
private inline fun InfiniteAnimation(crossinline animation: suspend CoroutineScope.() -> Unit) {
    LaunchedEffect(Unit) {
        withContext(FixedMotionDurationScale) {
            while (true) {
                // In case of infinite animations that repeat themselves, it is much better to
                // define a single iteration and then loop that. Otherwise if the animation
                // uses multiple coroutines things start falling apart after few loops, given that
                // delays are not exact. Requiring to have a well defined section to repeat ensures
                // that everything gets synced after each animation loop.
                animation()
            }
        }
    }
}

private object FixedMotionDurationScale : MotionDurationScale {
    override val scaleFactor: Float = 1f
}