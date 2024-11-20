package com.sambas.fagiollogs.core.design.components.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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

@Composable
fun RowSelector(
    leftIcon: ImageVector? = null,
    rightIcon: ImageVector = ImageVector.vectorResource(id = R.drawable.ic_right_arrow),
    label: String = "Language",
    selectedLanguage: String = "English",
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
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
            DesignText.body.Small(
                text = selectedLanguage,
                color = DesignTheme.colors.backgroundDisabled
            )
            Icon(
                imageVector = rightIcon,
                contentDescription = null,
                tint = DesignTheme.colors.backgroundDisabled,
                modifier = Modifier.size(DesignTheme.assetDimen.dimen_xs)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LanguageSelectorPreview() {
    PreviewTheme(true) {
        RowSelector(
            label = "Language",
            selectedLanguage = "English",
            leftIcon = ImageVector.vectorResource(R.drawable.ic_language),
            )
    }
}
