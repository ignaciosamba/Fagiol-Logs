package com.sambas.fagiollogs.core.design.dialog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sambas.fagiollogs.core.design.text.TextButtonLabel
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.SpacerXXS

@Composable
fun GenericDialogButton(
    positiveButton: GenericDialogButton,
    negativeButton: GenericDialogButton? = null
) {
    Row(
        modifier = Modifier
            .padding(
                top = DesignTheme.spacing.space_xxs,
                bottom = DesignTheme.spacing.space_xxs,
                end = DesignTheme.spacing.space_xs
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ){
        negativeButton?.let {
            TextButtonLabel.Medium(
                modifier = Modifier
                    .padding(end = DesignTheme.spacing.space_xs)
                    .clickable { negativeButton.onClick() },
                text = it.text,
                color = DesignTheme.colors.contentAction
            )
        }
        SpacerXXS()
        TextButtonLabel.Medium(
            modifier = Modifier
                .padding(end = DesignTheme.spacing.space_xxs)
                .clickable { positiveButton.onClick() },
            text = positiveButton.text,
            color = DesignTheme.colors.backgroundAccent
        )
    }
}