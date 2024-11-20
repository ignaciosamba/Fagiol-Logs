package com.sambas.fagiollogs.core.design.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.components.row.RowRadioButton
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.text.TextButtonLabel
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.RoundedCornerShape
import com.sambas.fagiollogs.core.design.theme.SpacerXS
import com.sambas.fagiollogs.core.design.theme.SpacerXXS
import com.sambas.fagiollogs.domain.model.ThemeTypesEnum

@Composable
fun <T> SelectorDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    listOfItems: List<T>,
    selectedItem: T? = null,
    optionTextSelected: (T) -> String,
    onItemSelected: (T) -> Unit,
    onDismissRequest: () -> Unit = {},
    dialogWidth: Dp = DesignTheme.assetDimen.dimen_mega_xl,
    positiveButton: GenericDialogButton,
    negativeButton: GenericDialogButton
) {
    val itemSelected = remember {
        mutableStateOf(selectedItem)
    }

    Dialog(
        properties = DialogProperties(),
        onDismissRequest = onDismissRequest,
        content = {
            LazyColumn(
                modifier = Modifier
                    .widthIn(max = dialogWidth)
                    .background(DesignTheme.colors.backgroundDialog, RoundedCornerShape.small)
            ) {
                item{
                    SpacerXS()
                    DesignText.navigation.Medium(
                        modifier = Modifier.padding(
                            start = DesignTheme.spacing.space_xs
                        ),
                        text = title,
                        textAlign = TextAlign.Start
                    )
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
                }
                itemsIndexed(listOfItems) { index, item ->
                    RowRadioButton(
                        modifier = Modifier
                            .padding(end = DesignTheme.spacing.space_xs)
                            .fillMaxWidth(),
                        optionText = optionTextSelected(item),
                        selected = itemSelected.value == item,
                        onClick = {
                            itemSelected.value = item
                            onItemSelected(item)
                        }
                    )
                }
                item {
                    GenericDialogButton(
                        positiveButton = positiveButton,
                        negativeButton = negativeButton
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun SelectorDialogPreview() {
    val listOfItems = ThemeTypesEnum.entries

    PreviewTheme(fullScreen = true, darkTheme = false) {
        SelectorDialog(
            title = "Select an option",
            description = "This is a description",
            listOfItems = listOfItems,
            selectedItem = ThemeTypesEnum.DARK,
            optionTextSelected = { it.text },
            onItemSelected = {},
            positiveButton = GenericDialogButton(
                text = "Accept",
                onClick =  { }
            ),
            negativeButton = GenericDialogButton(
                text = "Cancel",
                onClick =  { }
            ),
        )
    }
}