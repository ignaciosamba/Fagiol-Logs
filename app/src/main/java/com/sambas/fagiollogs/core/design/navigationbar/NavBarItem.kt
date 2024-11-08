package com.sambas.fagiollogs.core.design.navigationbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme

@Composable
internal fun NavBarItem(
    icon: Int,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(DesignTheme.assetDimen.dimen_xs),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(DesignTheme.assetDimen.dimen_xs),
            tint = if (selected) {
                DesignTheme.colors.backgroundPrimary
            } else {
                DesignTheme.colors.backgroundDisabled
            }
        )
        DesignText.body.Small(
            text = label,
            color = if (selected) {
                DesignTheme.colors.backgroundPrimary
            } else {
                DesignTheme.colors.backgroundDisabled
            }
        )
    }
}

@Preview
@Composable
private fun NavBarItemPreview() {
    PreviewTheme(true) {
        NavBarItem(
            icon = R.drawable.ic_calendar_minus,
            label = "Home",
            selected = true,
            onClick = {}
        )
    }
}