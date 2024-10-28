package com.sambas.fagiollogs.core.design.minicomponents

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme

@Composable
fun DividerWithText(
    text: String,
    modifier: Modifier = Modifier,
    dividerColor: Color = DesignTheme.colors.borderDividerSecondary,
    dividerThickness: Dp = 1.dp
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = dividerThickness,
            color = dividerColor
        )
        DesignText.body.Small(
            text = text,
            color = dividerColor,
            modifier = Modifier.padding(horizontal = DesignTheme.spacing.space_xxs),
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            thickness = dividerThickness,
            color = dividerColor
        )
    }
}

// Example
@Preview(showBackground = true)
@Composable
private fun DividerWithTextPreview() {
    PreviewTheme(true) {
        DividerWithText(
            text = "or"
        )
    }
}