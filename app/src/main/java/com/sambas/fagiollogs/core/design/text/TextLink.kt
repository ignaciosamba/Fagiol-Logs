package com.sambas.fagiollogs.core.design.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.sambas.fagiollogs.core.design.theme.DesignTheme

object TextLink {

    @Composable
    fun Medium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.link_m,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
        )
    }

    @Composable
    fun Medium(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.link_m,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
        )
    }

    @Composable
    fun Small(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.link_s,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
        )
    }

    @Composable
    fun Small(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.link_s,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
        )
    }

    @Composable
    fun XSmall(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.link_xs,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
        )
    }

    @Composable
    fun XSmall(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.link_xs,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
        )
    }
}
