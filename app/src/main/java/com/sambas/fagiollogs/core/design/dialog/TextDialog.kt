package com.sambas.fagiollogs.core.design.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.RoundedCornerShape
import com.sambas.fagiollogs.core.design.theme.SpacerXS
import com.sambas.fagiollogs.core.design.theme.SpacerXXS

@Composable
fun TextDialog(
    title: String? = null,
    description: String? = null,
    onDismissRequest: () -> Unit,
    dialogWidth: Dp = DesignTheme.assetDimen.dimen_mega_xl,
    positiveButton: GenericDialogButton,
    negativeButton: GenericDialogButton? = null
) {
    Dialog(
        properties = DialogProperties(),
        onDismissRequest = onDismissRequest,
        content = {
            Column (
                modifier = Modifier
                    .widthIn(max = dialogWidth)
                    .padding(end = DesignTheme.spacing.space_xs)
                    .background(DesignTheme.colors.backgroundDialog, RoundedCornerShape.small)
            ){
                SpacerXS()
                title?.let {
                    DesignText.navigation.Medium(
                        modifier = Modifier.padding(
                            start = DesignTheme.spacing.space_xs
                        ),
                        text = it,
                        textAlign = TextAlign.Start
                    )
                }
                SpacerXXS()
                description?.let {
                    DesignText.body.Small(
                        modifier = Modifier.padding(
                            start = DesignTheme.spacing.space_xs
                        ),
                        text = it,
                        color = DesignTheme.colors.contentSecondary
                    )
                    SpacerXXS()
                }
                GenericDialogButton(
                    positiveButton = positiveButton,
                    negativeButton = negativeButton
                )
            }
        }
    )
}

@Preview
@Composable
private fun TextDialogPreview(){
    PreviewTheme(fullScreen = true, darkTheme = false) {
        TextDialog(
            title = "Title",
            description = "This is a description",
            onDismissRequest = { },
            positiveButton = GenericDialogButton(
                text = "OK",
                onClick =  { }
            )
        )
    }
}