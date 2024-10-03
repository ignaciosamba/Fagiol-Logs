package com.sambas.fagiollogs.core.design.button

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeAnimationTarget
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowInsetsAnimationCompat
import com.sambas.fagiollogs.core.design.icon.DesignIcon
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerS
import com.sambas.fagiollogs.core.design.theme.SpacerXXS
import com.sambas.fagiollogs.core.design.theme.defaultMinSize
import com.sambas.fagiollogs.core.design.theme.size


public val DesignButtonShape = RoundedCornerShape(24.dp)
public val DesignSquaredButtonShape = RoundedCornerShape(8.dp)
public val DesignFullButtonShape = RoundedCornerShape(0.dp)

public object DesignButtonDefaults {

    private val ButtonHorizontalPadding = 16.dp
    private val ButtonVerticalPadding = 8.dp

    val ContentPadding =
        PaddingValues(
            start = ButtonHorizontalPadding,
            top = ButtonVerticalPadding,
            end = ButtonHorizontalPadding,
            bottom = ButtonVerticalPadding
        )
}

/**
 * The duration of the keyboard animation. This does not have to be accurate.
 * The value was chosen looking at what [WindowInsetsAnimationCompat] uses for API < 30
 * (`COMPAT_ANIMATION_DURATION` at the time of writing this).
 */
private const val KEYBOARD_ANIMATION_DURATION = 160

@Composable
fun TextButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape,
    contentPadding: PaddingValues = DesignButtonDefaults.ContentPadding,
    text: String,
    textStyle: TextStyle,
    onClick: () -> Unit
) = PressableButton(
    onClick = onClick,
    modifier = modifier,
    shape = shape,
    contentPadding = contentPadding,
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource
) {
    SpacerXXS()
    Text(
        text = text,
        color = LocalContentColor.current,
        style = textStyle
    )
    SpacerXXS()
}

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape,
    contentPadding: PaddingValues = DesignButtonDefaults.ContentPadding,
    painter: Painter,
    text: String,
    textStyle: TextStyle,
    iconDimen: Dp = 24.dp,
    onClick: () -> Unit
) = PressableButton(
    onClick = onClick,
    modifier = modifier,
    shape = shape,
    contentPadding = contentPadding,
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource
) {
//    SpacerXXS()
    DesignIcon(
        painter = painter,
        contentDescription = "",
        modifier = Modifier.size(iconDimen),
        tint = LocalContentColor.current
    )
    SpacerS()
    Text(
        text = text,
        color = LocalContentColor.current,
        style = textStyle
    )
    SpacerXXS()
}

@Composable
fun CircularButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    iconDimen: Dp,
    onClick: () -> Unit
) = PressableButton(
    onClick = onClick,
    modifier = modifier,
    shape = CircleShape,
    colors = colors,
    enabled = enabled,
    contentPadding = PaddingValues(0.dp),
    interactionSource = interactionSource
) {
    DesignIcon(
        painter = painter,
        contentDescription = "",
        modifier = Modifier.size(iconDimen),
        tint = LocalContentColor.current
    )
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    textStyle: TextStyle,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier,
    colors = colors,
    enabled = enabled,
    text = text,
    interactionSource = interactionSource,
    shape = DesignButtonShape,
    textStyle = textStyle,
    onClick = onClick
)

@Composable
fun ButtonFull(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier
        .defaultMinSize(DesignTheme.buttonDimension.buttonFull),
    colors = colors,
    enabled = enabled,
    text = text,
    interactionSource = interactionSource,
    shape = DesignFullButtonShape,
    textStyle = DesignTheme.typography.buttonLabel_l,
    onClick = onClick
)

@Composable
fun ButtonMedium(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier.defaultMinSize(
        DesignTheme.buttonDimension.buttonMedium,
    ),
    colors = colors,
    enabled = enabled,
    text = text,
    interactionSource = interactionSource,
    shape = DesignButtonShape,
    textStyle = DesignTheme.typography.buttonLabel_m,
    onClick = onClick
)

@Composable
fun ButtonSmall(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier.defaultMinSize(
        DesignTheme.buttonDimension.buttonSmall
    ),
    colors = colors,
    enabled = enabled,
    text = text,
    interactionSource = interactionSource,
    shape = DesignButtonShape,
    textStyle = DesignTheme.typography.buttonLabel_m,
    onClick = onClick
)

@Composable
fun ImageButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    text: String,
    iconDimen: Dp,
    onClick: () -> Unit
) = ImageButton(
    onClick = onClick,
    modifier = modifier,
    colors = colors,
    enabled = enabled,
    iconDimen = iconDimen,
    interactionSource = interactionSource,
    shape = DesignButtonShape,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_m,
    painter = painter
)

@Composable
fun ImageButtonFull(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    text: String,
    onClick: () -> Unit
) = ImageButton(
    onClick = onClick,
    modifier = modifier.defaultMinSize(
        DesignTheme.buttonDimension.buttonFull
    ),
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource,
    shape = DesignFullButtonShape,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_m,
    painter = painter
)

@Composable
fun ImageButtonMedium(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    text: String,
    onClick: () -> Unit
) = ImageButton(
    onClick = onClick,
    modifier = modifier.defaultMinSize(
        DesignTheme.buttonDimension.buttonMedium
    ),
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource,
    shape = DesignButtonShape,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_m,
    painter = painter
)

@Composable
fun ImageButtonSmall(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    text: String,
    onClick: () -> Unit
) = ImageButton(
    onClick = onClick,
    modifier = modifier.defaultMinSize(
        DesignTheme.buttonDimension.buttonSmall
    ),
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource,
    shape = DesignButtonShape,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_m,
    painter = painter
)

@Composable
fun CircularButtonSmall(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    onClick: () -> Unit
) = CircularButton(
    modifier = modifier.size(
        DesignTheme.buttonDimension.circularButtonSmall
    ),
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource,
    painter = painter,
    iconDimen = DesignTheme.assetDimen.dimen_xs,
    onClick = onClick
)

@Composable
fun CircularButtonMedium(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    onClick: () -> Unit
) = CircularButton(
    modifier = modifier.size(
        DesignTheme.buttonDimension.circularButtonMedium
    ),
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource,
    painter = painter,
    iconDimen = DesignTheme.assetDimen.dimen_l,
    onClick = onClick
)

@Composable
fun CircularButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    onClick: () -> Unit
) = CircularButton(
    modifier = modifier,
    colors = colors,
    enabled = enabled,
    interactionSource = interactionSource,
    painter = painter,
    iconDimen = DesignTheme.assetDimen.dimen_l,
    onClick = onClick
)

@Composable
fun SquaredButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier,
    colors = colors,
    enabled = enabled,
    shape = DesignSquaredButtonShape,
    contentPadding = contentPadding,
    interactionSource = interactionSource,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_m,
    onClick = onClick
)

@Composable
fun SquaredButtonMedium(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    onClick: () -> Unit
) = TextButton(
    modifier = modifier.height(
        DesignTheme.buttonDimension.squaredButton.height
    ),
    colors = colors,
    enabled = enabled,
    shape = DesignSquaredButtonShape,
    contentPadding = contentPadding,
    interactionSource = interactionSource,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_m,
    onClick = onClick
)

@Composable
fun SquaredImageButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    text: String,
    onClick: () -> Unit
) = ImageButton(
    modifier = modifier,
    colors = colors,
    enabled = enabled,
    shape = DesignSquaredButtonShape,
    contentPadding = contentPadding,
    interactionSource = interactionSource,
    painter = painter,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_s,
    onClick = onClick
)

@Composable
fun SquaredImageButtonMedium(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    painter: Painter,
    text: String,
    iconDimen: Dp,
    onClick: () -> Unit
) = ImageButton(
    modifier = modifier.height(
        DesignTheme.buttonDimension.squaredImageButton.height
    ),
    colors = colors,
    enabled = enabled,
    shape = DesignSquaredButtonShape,
    contentPadding = contentPadding,
    interactionSource = interactionSource,
    painter = painter,
    text = text,
    textStyle = DesignTheme.typography.buttonLabel_m,
    iconDimen = iconDimen,
    onClick = onClick
)

/**
 * This is a [TextButton] that performs an animation when the keyboard appears/disappears.
 * In order for this to work properly, the following conditions have to be met:
 *
 * 1. The container activity must declare [WindowCompat.setDecorFitsSystemWindows(window, false)].
 * 2. It is necessary that it is placed in the bottomBar of the [Scaffold].
 * 3. It is necessary to add the [Modifier.navigationBarsWithImePaddingPreviewable()] in order to add the right bottom
 * padding to the [Scaffold].
 *
 * Due to the fact that the [WindowInsets] APIs have been added in Android 11, on the older versions
 * of Android the animation won't be as smooth.
 *
 * @version 1.0.0
 * @since 4.27.0
 *
 * @param modifier for the AnimatedButton
 * @param colors a [DesignButtonColors] object to define the colors of the [AnimatedButton].
 * @param enabled a boolean value that defines whether the button is enabled or not
 * @param interactionSource the [MutableInteractionSource] object.
 * @param text the button's label.
 * @param downHorizontalPadding The horizontal padding that the button has on each corner when the keyboard is DOWN.
 * @param upHorizontalPadding the horizontal padding that the button has on each corner when the keyboard is UP.
 * @param downVerticalPadding the vertical padding that the button has on each corner when the keyboard is DOWN.
 * @param upVerticalPadding the vertical padding that the button has on each corner when the keyboard is UP.
 * @param downCornerRadius the corner radius (in Dp) that the button has on each corner when the keyboard is DOWN.
 * @param upCornerRadius the corner radius (in Dp) that the button has on each corner when the keyboard is UP.
 * @param onAnimationEnded the listener that will be triggered upon animation end. The boolean will indicate if the keyboard is opened or not.
 * @param animationSpec the animation to be performed upon keyboard state change
 * @param onClick the action to perform upon button's click.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AnimatedButton(
    modifier: Modifier = Modifier,
    colors: DesignButtonColors,
    enabled: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    text: String,
    downHorizontalPadding: Dp = DesignTheme.spacing.space_l,
    upHorizontalPadding: Dp = 0.dp,
    downVerticalPadding: Dp = DesignTheme.spacing.space_m,
    upVerticalPadding: Dp = 0.dp,
    downCornerRadius: Dp = 24.dp,
    upCornerRadius: Dp = 0.dp,
    onAnimationEnded: ((isOpen: Boolean) -> Unit)? = null,
    animationSpec: AnimationSpec<Dp> = tween(
        durationMillis = KEYBOARD_ANIMATION_DURATION,
        easing = LinearEasing
    ),
    onClick: () -> Unit
) {
    val imeAnimationTarget = WindowInsets.imeAnimationTarget
    val density = LocalDensity.current
    val willExpand =
        remember(imeAnimationTarget) { derivedStateOf { imeAnimationTarget.getBottom(density) > 0 } }
    val cornerRadiusAnimation = animateDpAsState(
        targetValue = if (willExpand.value) {
            upCornerRadius
        } else {
            downCornerRadius
        },
        animationSpec = animationSpec,
        label = "cornerRadius Animation",
        finishedListener = {
            onAnimationEnded?.invoke(willExpand.value)
        }
    )
    val horizontalPaddingAnimation = animateDpAsState(
        targetValue = if (willExpand.value) {
            upHorizontalPadding
        } else {
            downHorizontalPadding
        },
        animationSpec = animationSpec,
        label = "horizontalPadding Animation"
    )
    val verticalPaddingAnimation = animateDpAsState(
        targetValue = if (willExpand.value) {
            upVerticalPadding
        } else {
            downVerticalPadding
        },
        animationSpec = animationSpec,
        label = "verticalPadding Animation"
    )
    TextButton(
        modifier = modifier
            .padding(
                horizontal = horizontalPaddingAnimation.value,
                vertical = verticalPaddingAnimation.value
            )
            .defaultMinSize(
                DesignTheme.buttonDimension.buttonFull,
            ),
        colors = colors,
        enabled = enabled,
        text = text,
        interactionSource = interactionSource,
        shape = RoundedCornerShape(cornerRadiusAnimation.value),
        textStyle = DesignTheme.typography.buttonLabel_m,
        onClick = onClick
    )
}

@Preview
@Composable
private fun AnimatedButtonPreview() {
    val text = remember { mutableStateOf("") }
    val context = LocalContext.current
    PreviewTheme(
        fullScreen = true
    ) {
    }
}
