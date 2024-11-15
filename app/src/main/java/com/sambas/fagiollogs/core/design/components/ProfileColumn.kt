package com.sambas.fagiollogs.core.design.components

import android.widget.EditText
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sambas.fagiollogs.R
import com.sambas.fagiollogs.core.design.button.DesignButtons
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerS
import com.sambas.fagiollogs.core.design.theme.SpacerXS
import com.sambas.fagiollogs.core.design.theme.SpacerXXS

@Composable
fun ProfileHeader(
    @DrawableRes profileImage: Int,
    name: String,
    email: String,
    buttonText: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(DesignTheme.spacing.space_xs),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = profileImage),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(DesignTheme.colors.backgroundGlass),
            contentScale = ContentScale.Crop
        )

        SpacerXXS()

        DesignText.body.MediumBold(text = name)

        DesignText.body.Small(text = email)

        SpacerS()

        DesignButtons.primary.Small(
            text = buttonText,
            onClick = onEditClick,
            modifier = Modifier.fillMaxWidth(0.7f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileHeaderPreview() {
    PreviewTheme(true) {
        ProfileHeader(
            profileImage = R.drawable.ic_mum_profile,
            name = "Fagiol's mum",
            email = "mum.fagiols@example.com",
            buttonText = "Edit profile",
            onEditClick = {}
        )
    }
}