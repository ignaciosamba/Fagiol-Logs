package com.sambas.fagiollogs.core.design.button

import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R

object DesignButtons {
    val primary = DesignButtonTypes { primaryButtonColors() }

    val secondary = DesignButtonTypes { secondaryButtonColors() }

    val ghost = DesignButtonTypes { ghostButtonColors() }

    val inversePrimary = DesignButtonTypes { inversePrimaryButtonColors() }

    val inverseSecondary = DesignButtonTypes { inverseSecondaryButtonColors() }

    val inverseGhost = DesignButtonTypes { inverseGhostButtonColors() }

    val inverseText = DesignButtonTypes { inverseTextButtonColors() }

    val glass = DesignButtonTypes { glassButtonColors() }

    val text = DesignButtonTypes { textButtonColors() }
}

class DesignButtonTypes(
    private val designButtonColorsProvider: @Composable () -> DesignButtonColors
) {
    @Composable
    fun Small(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) = ButtonSmall(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        text = text,
        onClick = onClick
    )

    @Composable
    fun Medium(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) = ButtonMedium(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        text = text,
        onClick = onClick
    )

    @Composable
    fun Full(
        modifier: Modifier = Modifier,
        text: String,
        enabled: Boolean = true,
        onClick: () -> Unit
    ) = ButtonFull(
        modifier = modifier,
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        text = text,
        onClick = onClick
    )

    @Composable
    fun Button(
        modifier: Modifier = Modifier,
        width: Dp,
        height: Dp,
        text: String,
        enabled: Boolean = true,
        textStyle: TextStyle,
        onClick: () -> Unit
    ) = Button(
        modifier = modifier
            .width(width)
            .height(height),
        colors = designButtonColorsProvider(),
        enabled = enabled,
        interactionSource = remember { MutableInteractionSource() },
        text = text,
        textStyle,
        onClick = onClick
    )
}

@Preview
@Composable
internal fun ButtonPrimarySizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.primary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimarySizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.primary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimarySizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.primary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimarySizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.primary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimarySizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.primary.Small(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimarySizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.primary.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondarySizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.secondary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondarySizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.secondary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondarySizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.secondary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondarySizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.secondary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondarySizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.secondary.Small(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondarySizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.secondary.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.text.Medium(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.text.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.text.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.text.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.text.Small(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.text.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonGhostSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.ghost.Medium(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonGhostSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.ghost.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonGhostSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.ghost.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonGhostSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignImageButtons.ghost.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonGhostSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.ghost.Small(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonGhostSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignButtons.ghost.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimaryInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inversePrimary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimaryInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inversePrimary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimaryInverseSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inversePrimary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimaryInverseSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inversePrimary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimaryInverseSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inversePrimary.Small(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonPrimaryInverseSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inversePrimary.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondaryInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseSecondary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondaryInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseSecondary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondaryInverseSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inverseSecondary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondaryInverseSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inverseSecondary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondaryInverseSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseSecondary.Small(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSecondaryInverseSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseSecondary.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseText.Medium(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseText.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextInverseSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inverseText.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextInverseSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inverseText.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextInverseSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseText.Small(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonTextInverseSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseText.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonGhostInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseGhost.Medium(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonGhostInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseGhost.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonGhostInverseSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inverseGhost.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonGhostInverseSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignImageButtons.inverseGhost.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonGhostInverseSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseGhost.Small(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonGhostInverseSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignButtons.inverseGhost.Small(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimarySizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredButtons.primary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimarySizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredButtons.primary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimarySizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredImageButtons.primary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimarySizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredImageButtons.primary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondarySizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredButtons.secondary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondarySizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredButtons.secondary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondarySizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredImageButtons.secondary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondarySizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredImageButtons.secondary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonSquaredGhostSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredButtons.ghost.Medium(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonSquaredGhostSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredButtons.ghost.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonSquaredGhostSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredImageButtons.ghost.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun ButtonSquaredGhostSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignSquaredImageButtons.ghost.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimaryInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredButtons.inversePrimary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimaryInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredButtons.inversePrimary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimaryInverseSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredImageButtons.inversePrimary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredPrimaryInverseSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredImageButtons.inversePrimary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondaryInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredButtons.inverseSecondary.Medium(text = "Text") {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondaryInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredButtons.inverseSecondary.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondaryInverseSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredImageButtons.inverseSecondary.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonSquaredSecondaryInverseSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredImageButtons.inverseSecondary.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonSquaredGhostInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredButtons.inverseGhost.Medium(text = "Text") {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonSquaredGhostInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredButtons.inverseGhost.Medium(text = "Text", enabled = false) {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonSquaredGhostInverseSizeMIconPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredImageButtons.inverseGhost.Medium(
                text = "Text",
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF345676)
@Composable
internal fun ButtonSquaredGhostInverseSizeMIconDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignSquaredImageButtons.inverseGhost.Medium(
                text = "Text",
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCirclePrimarySizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.primary.Medium(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCirclePrimarySizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.primary.Medium(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircleSecondarySizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.secondary.Medium(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularSecondarySizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.secondary.Medium(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCirclePrimarySizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.primary.Small(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCirclePrimarySizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.primary.Small(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircleSecondarySizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.secondary.Small(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularSecondarySizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            DesignCircularButtons.secondary.Small(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularPrimaryInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inversePrimary.Medium(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularPrimaryInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inversePrimary.Medium(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularPrimaryInverseSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inversePrimary.Small(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularPrimaryInverseSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inversePrimary.Small(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularSecondaryInverseSizeMPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inverseSecondary.Medium(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularSecondaryInverseSizeMDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inverseSecondary.Medium(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularSecondaryInverseSizeSPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inverseSecondary.Small(
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}

@Preview
@Composable
internal fun ButtonCircularSecondaryInverseSizeSDisabledPreview() {
    PreviewTheme(fullScreen = false) {
        Column(
            modifier = Modifier.fillMaxWidth().background(Color(0xFF0C264F)).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DesignCircularButtons.inverseSecondary.Small(
                enabled = false,
                painter = painterResource(id = R.drawable.search_ic)
            ) {
            }
        }
    }
}
