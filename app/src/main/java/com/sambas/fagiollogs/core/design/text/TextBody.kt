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

object TextBody {

    @Composable
    fun ExtraLarge(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xl,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun ExtraLarge(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xl,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun ExtraLargeBold(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xlb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun ExtraLargeBold(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xlb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun Large(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_l,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun Large(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_l,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun LargeBold(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_lb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun LargeBold(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_lb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun Medium(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_m,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
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
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_m,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun MediumBold(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_mb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun MediumBold(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_mb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
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
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_s,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
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
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_s,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun SmallBold(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_sb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun SmallBold(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_sb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
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
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xs,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
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
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xs,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun XSmallBold(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xsb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }

    @Composable
    fun XSmallBold(
        text: AnnotatedString,
        modifier: Modifier = Modifier,
        color: Color = DesignTheme.colors.contentPrimary,
        textAlign: TextAlign? = null,
        lineHeight: TextUnit = TextUnit.Unspecified,
        overflow: TextOverflow = TextOverflow.Clip,
        softWrap: Boolean = true,
        minLines: Int = 1,
        maxLines: Int = Int.MAX_VALUE,
    ) {
        Text(
            text = text,
            modifier = modifier,
            style = DesignTheme.typography.body_xsb,
            color = color,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            softWrap = softWrap,
            minLines = minLines,
            maxLines = maxLines,
        )
    }
}
