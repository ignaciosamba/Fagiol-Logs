package com.sambas.fagiollogs.core.design.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.text.TextButtonLabel
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerXS

@Immutable
data class SnackbarAction(
    val label: String,
    val performAction: () -> Unit,
)

@Composable
fun Snackbar(
    message: String,
    backgroundColor: Color,
    textColor: Color = DesignTheme.colors.contentWhite,
    modifier: Modifier = Modifier,
    action: SnackbarAction? = null,
) {
    Column {
        val shape = RoundedCornerShape(8.dp)
        Card(
            modifier = modifier
                .padding(horizontal = DesignTheme.spacing.space_xs)
                .fillMaxWidth(),
            shape = shape,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
                    .requiredHeight(52.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                DesignText.body.Small(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = DesignTheme.spacing.space_xs),
                    text = message,
                    color = textColor,
                )
                if (action != null) {
                    Box(
                        modifier = Modifier
                            .clip(shape)
                            .clickable {
                                action.performAction()
                            }
                            .fillMaxHeight()
                            .padding(horizontal = DesignTheme.spacing.space_xs),
                        contentAlignment = Alignment.Center,
                    ) {
                        TextButtonLabel.Small(
                            text = action.label,
                            color = textColor,
                        )
                    }
                }
            }
        }
        SpacerXS()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
private fun MessageSnackbarPreview() {
    PreviewTheme(fullScreen = false) {
        Snackbar(
            modifier = Modifier.padding(top = DesignTheme.spacing.space_xs),
            message = "Feedback message",
            textColor = DesignTheme.colors.contentTertiary,
            action = SnackbarAction("Azione") {},
            backgroundColor = DesignTheme.colors.backgroundSecondary,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F5F5)
@Composable
private fun ErrorSnackbarPreview() {
    PreviewTheme(fullScreen = false) {
        Snackbar(
            modifier = Modifier.padding(top = DesignTheme.spacing.space_xs),
            message = "Error Feedback message",
            action = SnackbarAction("Action") {},
            backgroundColor = DesignTheme.colors.contentError,
        )
    }
}