package com.sambas.fagiollogs.domain.ui.landing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.button.DesignButtons
import com.sambas.fagiollogs.core.design.navigationbar.Toolbar
import com.sambas.fagiollogs.core.design.scaffold.BaseScaffold
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerM
import com.sambas.fagiollogs.core.design.theme.SpacerS
import com.sambas.fagiollogs.core.design.theme.SpacerXS
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
internal fun LandingScreen(
    modifier: Modifier = Modifier,
    landingUiState: LandingUiState = LandingUiState(),
    onAddEventClick: (BabyEventType) -> Unit = {},
    onEventItemClick: (String) -> Unit = {}
) {
    BaseScaffold(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
        uiState = landingUiState,
        topBar = {
            Toolbar(
                title = stringResource(id = R.string.app_name)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = DesignTheme.spacing.space_xs,
                    end = DesignTheme.spacing.space_xs,
                    top = paddingValues.calculateTopPadding() + DesignTheme.spacing.space_xs,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            // Today's date section
            item {
                TodayDateSection(babyName = landingUiState.babyName)
                SpacerM()
            }

            // Quick Add Buttons
            item {
                QuickAddSection(onAddEventClick = onAddEventClick)
                SpacerM()
            }

            // Recent events section
            item {
                DesignText.titles.Small(
                    text = "Recent Events",
                    modifier = Modifier.padding(bottom = DesignTheme.spacing.space_xs)
                )
            }

            // List of recent events
            if (landingUiState.recentEvents.isEmpty()) {
                item {
                    EmptyEventsCard()
                }
            } else {
                items(landingUiState.recentEvents) { event ->
                    EventCard(
                        event = event,
                        onClick = { onEventItemClick(event.id) }
                    )
                    SpacerS()
                }
            }
        }
    }
}

@Composable
private fun TodayDateSection(babyName: String) {
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d")
    val formattedDate = today.format(formatter)

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DesignText.body.Medium(
                text = formattedDate,
                color = DesignTheme.colors.contentSecondary
            )
        }
        SpacerXS()
        DesignText.titles.Medium(
            text = "Hello, parent of $babyName",
            color = DesignTheme.colors.contentPrimary
        )
    }
}

@Composable
private fun QuickAddSection(onAddEventClick: (BabyEventType) -> Unit = {}) {
    Column(modifier = Modifier.fillMaxWidth()) {
        DesignText.titles.Small(
            text = "Quick Add",
            modifier = Modifier.padding(bottom = DesignTheme.spacing.space_xs)
        )

        // Filter out the OTHER type for the quick add section
        val quickAddTypes = BabyEventType.entries.filter { it != BabyEventType.OTHER }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            quickAddTypes.forEach { eventType ->
                QuickAddButton(
                    text = eventType.displayName,
                    icon = eventType.iconResId,
                    modifier = Modifier.weight(1f),
                    onClick = { onAddEventClick(eventType) }
                )
            }
        }
    }
}

@Composable
private fun QuickAddButton(
    text: String,
    @androidx.annotation.DrawableRes icon: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(DesignTheme.colors.backgroundSecondary)
            .padding(vertical = DesignTheme.spacing.space_xs)
            .height(80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = text,
            tint = DesignTheme.colors.contentAction,
            modifier = Modifier.size(28.dp)
        )
        SpacerXS()
        DesignText.body.Small(
            text = text,
            color = DesignTheme.colors.contentSecondary
        )
    }
}

@Composable
private fun EventCard(
    event: BabyEvent,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = DesignTheme.colors.backgroundPrimary
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DesignTheme.spacing.space_xs),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Event type icon
            Icon(
                painter = painterResource(id = event.type.iconResId),
                contentDescription = event.type.displayName,
                tint = DesignTheme.colors.contentAction,
                modifier = Modifier
                    .size(32.dp)
                    .padding(end = DesignTheme.spacing.space_xxs)
            )

            // Event details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = DesignTheme.spacing.space_xxs)
            ) {
                DesignText.body.MediumBold(text = event.type.displayName)
                DesignText.body.Small(
                    text = event.description,
                    color = DesignTheme.colors.contentSecondary
                )
            }

            // Time
            DesignText.body.Small(
                text = event.time,
                color = DesignTheme.colors.contentTertiary,
                modifier = Modifier.padding(start = DesignTheme.spacing.space_xxs)
            )
        }
    }
}

@Composable
private fun EmptyEventsCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(DesignTheme.colors.backgroundSecondary)
            .padding(DesignTheme.spacing.space_m),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_calendar_minus),
                contentDescription = "No events",
                tint = DesignTheme.colors.contentSecondary,
                modifier = Modifier.size(48.dp)
            )
            SpacerS()
            DesignText.body.Medium(
                text = "No events recorded yet",
                color = DesignTheme.colors.contentSecondary,
                textAlign = TextAlign.Center
            )
            SpacerS()
            DesignButtons.primary.Small(
                text = "Add First Event",
                onClick = { /* Navigate to add event screen */ }
            )
        }
    }
}

@Preview
@Composable
fun LandingScreenPreview() {
    PreviewTheme(fullScreen = true, darkTheme = false) {
        val sampleEvents = listOf(
            BabyEvent(
                id = "1",
                type = BabyEventType.FEEDING,
                description = "Formula - 120ml",
                time = "10:30 AM"
            ),
            BabyEvent(
                id = "2",
                type = BabyEventType.DIAPER,
                description = "Wet diaper",
                time = "11:45 AM"
            ),
            BabyEvent(
                id = "3",
                type = BabyEventType.SLEEP,
                description = "Nap - 1 hour 30 minutes",
                time = "1:15 PM"
            )
        )

        LandingScreen(
            landingUiState = LandingUiState(
                babyName = "Fagiols",
                recentEvents = sampleEvents
            )
        )
    }
}

@Preview
@Composable
fun EmptyLandingScreenPreview() {
    PreviewTheme(fullScreen = true, darkTheme = true) {
        LandingScreen(
            landingUiState = LandingUiState(
                babyName = "Fagiols",
                recentEvents = emptyList()
            )
        )
    }
}