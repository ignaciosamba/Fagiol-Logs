package com.sambas.fagiollogs.core.design.error

import android.annotation.SuppressLint
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sambas.fagiollogs.core.design.button.DesignButtons
import com.sambas.fagiollogs.core.design.dialog.GenericDialogButton
import com.sambas.fagiollogs.core.design.text.DesignText
import com.sambas.fagiollogs.core.design.theme.DesignTheme
import com.sambas.fagiollogs.core.design.theme.PreviewTheme
import com.sambas.fagiollogs.core.design.theme.SpacerM
import com.sambas.fagiollogs.core.design.theme.SpacerS
import com.sambas.fagiollogs.core.design.theme.SpacerXS

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ErrorView(
    title: String?,
    message: String?,
    modifier: Modifier = Modifier,
    primaryButton: GenericDialogButton? = null,
    secondaryButton: GenericDialogButton? = null,
    @RawRes animation: Int = DesignTheme.animation.genericError,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DesignTheme.colors.backgroundPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(DesignTheme.assetDimen.dimen_super),
            composition = composition,
        )
        SpacerS()
        DesignText.titles.Small(
            modifier = Modifier.padding(horizontal = DesignTheme.spacing.space_xs),
            text = title.orEmpty()
        )
        SpacerXS()
        DesignText.body.Medium(
            modifier = Modifier.padding(horizontal = DesignTheme.spacing.space_xs),
            text = message.orEmpty(),
            textAlign = TextAlign.Center
        )
        SpacerM()

        if (primaryButton != null) {
            DesignButtons.secondary.Medium(
                text = primaryButton.text,
                onClick = primaryButton.onClick,
            )
            SpacerXS()
        }

        if (secondaryButton != null) {
            DesignButtons.text.Medium(
                text = secondaryButton.text,
                onClick = secondaryButton.onClick,
            )
        }
    }
}

@Preview
@Composable
private fun ErrorViewPreview() {
    PreviewTheme(fullScreen = false) {
        ErrorView(
            message = "C’è stato un piccolo problema tecnico che stiamo già risolvendo.\nRiprova subito o tra pochi minuti.",
            title = "Qualcosa non va!",
            primaryButton = GenericDialogButton("Primary button") {},
            secondaryButton = GenericDialogButton("Secondary button") {},
            animation = DesignTheme.animation.genericError
        )
    }
}