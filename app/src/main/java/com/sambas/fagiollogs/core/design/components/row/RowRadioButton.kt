package com.sambas.fagiollogs.core.design.components.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerMini

@Composable
internal fun RowRadioButton(
    modifier: Modifier = Modifier,
    optionText: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier.clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonColors(
                selectedColor = DesignTheme.colors.contentAction,
                unselectedColor = DesignTheme.colors.contentTertiary,
                disabledSelectedColor = DesignTheme.colors.contentTertiary,
                disabledUnselectedColor = DesignTheme.colors.contentTertiary
            )
        )
        SpacerMini()
        DesignText.body.Small(
            text = optionText,
            modifier = Modifier.padding(end = DesignTheme.spacing.space_xxs)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RowRadioButtonPreview() {
    PreviewTheme(true, darkTheme = false) {
        RowRadioButton(
            optionText = "Option to select",
            selected = true,
            onClick = {}
        )
    }
}