package com.sambas.fagiollogs.core.design.navigationbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerM

@Immutable
sealed interface ToolBarButton {
    @Immutable
    data class Back(val onClick: () -> Unit) : ToolBarButton

    @Immutable
    data class Close(val onClick: () -> Unit) : ToolBarButton

    @Immutable
    data class Custom(val button: @Composable () -> Unit) : ToolBarButton

    data object None : ToolBarButton
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String? = null,
    button: ToolBarButton = ToolBarButton.None,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = DesignTheme.colors.contentWhite,
    contentColor: Color = DesignTheme.colors.contentPrimary
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                title?.let {
                    DesignText.navigation.Medium(
                        text = it,
                        color = contentColor,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        navigationIcon = {
            when (button) {
                is ToolBarButton.Back -> {
                    IconButton(onClick = button.onClick) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = "Back",
                            modifier = Modifier.size(DesignTheme.assetDimen.dimen_m)
                        )
                    }
                }

                is ToolBarButton.Close -> {
                    IconButton(onClick = button.onClick) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                            contentDescription = "Back",
                            modifier = Modifier.size(DesignTheme.assetDimen.dimen_m)
                        )
                    }
                }

                is ToolBarButton.Custom -> {
                    button.button()
                }

                ToolBarButton.None -> Unit
            }
        },
        actions = actions,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            titleContentColor = contentColor
        )
    )
}

@Preview
@Composable
fun ScreenWithToolbar() {
    PreviewTheme(true) {
        Column (){
            Toolbar(
                title = "Title",
                button = ToolBarButton.Back(onClick = { /* Handle back button click */ }),
                actions = {
                    IconButton(onClick = { /* Handle action */ }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
                            contentDescription = "Settings",
                            modifier = Modifier.size(DesignTheme.assetDimen.dimen_m)
                        )
                    }
                }
            )
            SpacerM()
            Toolbar(
                button = ToolBarButton.Close(onClick = { /* Handle back button click */ }),
                actions = {
                    DesignText.body.Small(
                        modifier = Modifier.padding(end = DesignTheme.spacing.space_xs),
                        text = "Logout"
                    )
                }
            )
            SpacerM()
            Toolbar(
                button = ToolBarButton.None,
                actions = {
                    DesignText.body.Small(
                        modifier = Modifier.padding(end = DesignTheme.spacing.space_xs),
                        text = "Logout"
                    )
                }
            )
    }
    }
}