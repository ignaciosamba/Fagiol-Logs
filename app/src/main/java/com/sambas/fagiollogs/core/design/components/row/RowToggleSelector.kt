package com.sambas.fagiollogs.core.design.components.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerXS

@Composable
fun RowToggleSelector(
    leftIcon: ImageVector? = null,
    isChecked: Boolean = false,
    label: String,
    onClick: (Boolean) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DesignTheme.spacing.space_xxs),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                DesignTheme.spacing.space_xxs
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leftIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    modifier = Modifier.size(DesignTheme.assetDimen.dimen_l)
                )
            }
            DesignText.body.Medium(
                text = label
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                modifier = Modifier
                    .size(DesignTheme.assetDimen.dimen_m)
                    .padding(end = DesignTheme.spacing.space_xxs),
                checked = isChecked,
                onCheckedChange = {
                    onClick(it)
                },
                colors = SwitchDefaults.colors(
                    checkedTrackColor = DesignTheme.colors.backgroundActionPrimary,
                    checkedThumbColor = DesignTheme.colors.contentWhite,
                    uncheckedTrackColor = DesignTheme.colors.backgroundDisabled,
                    uncheckedBorderColor = DesignTheme.colors.backgroundDisabled,
                    uncheckedThumbColor = DesignTheme.colors.contentWhite,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowToggleSelectorPreview() {
    PreviewTheme(true) {
        val selected = remember {
            mutableStateOf(true)
        }
        Column {
            RowToggleSelector(
                label = "Language",
                isChecked = selected.value,
                leftIcon = ImageVector.vectorResource(R.drawable.ic_language),
                onClick = {
                    selected.value = it
                }
            )
            SpacerXS()
            RowToggleSelector(
                label = "Notification",
                isChecked = false,
                leftIcon = ImageVector.vectorResource(R.drawable.ic_notification),
            )
        }
    }
}