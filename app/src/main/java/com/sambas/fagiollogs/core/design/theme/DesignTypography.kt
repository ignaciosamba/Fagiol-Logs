package com.sambas.fagiollogs.core.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Immutable
data class DesignTypography(
    val title_xs: TextStyle,
    val title_s: TextStyle,
    val title_m: TextStyle,
    val title_l: TextStyle,
    val headline_s: TextStyle,
    val headline_m: TextStyle,
    val body_xs: TextStyle,
    val body_xsb: TextStyle,
    val body_s: TextStyle,
    val body_sb: TextStyle,
    val body_m: TextStyle,
    val body_mb: TextStyle,
    val body_l: TextStyle,
    val body_lb: TextStyle,
    val body_xl: TextStyle,
    val body_xlb: TextStyle,
    val navigation_m: TextStyle,
    val navigation_l: TextStyle,
    val link_xs: TextStyle,
    val link_s: TextStyle,
    val link_m: TextStyle,
    val buttonLabel_xs: TextStyle,
    val buttonLabel_s: TextStyle,
    val buttonLabel_m: TextStyle,
    val buttonLabel_l: TextStyle,
)
val defaultDesignTypography = DesignTypography(
    title_xs = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    title_s = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    title_m = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    title_l = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    ),
    headline_s = TextStyle(
        fontFamily = dosis,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    headline_m = TextStyle(
        fontFamily = dosis,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    body_xs = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    ),
    body_xsb = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp
    ),
    body_s = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    body_sb = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    body_m = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    body_mb = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp
    ),
    body_l = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp
    ),
    body_lb = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    body_xl = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    body_xlb = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    navigation_m = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    ),
    navigation_l = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    link_xs = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        textDecoration = TextDecoration.Underline
    ),
    link_s = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        textDecoration = TextDecoration.Underline
    ),
    link_m = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        textDecoration = TextDecoration.Underline
    ),
    buttonLabel_xs = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 9.sp
    ),
    buttonLabel_s = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    buttonLabel_m = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    buttonLabel_l = TextStyle(
        fontFamily = openSans,
        fontWeight = FontWeight.Bold,
        fontSize = 17.sp
    )
)
val galanoDesignTypography = DesignTypography(
    title_xs = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    title_s = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    title_m = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    title_l = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 32.sp,
        lineHeight = 48.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    headline_s = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    headline_m = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),

        ),
    body_xs = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_xsb = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_s = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_sb = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_m = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_mb = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_l = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_lb = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_xl = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    body_xlb = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    navigation_m = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    navigation_l = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    link_xs = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        textDecoration = TextDecoration.Underline,
        lineHeight = 16.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    link_s = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        textDecoration = TextDecoration.Underline,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    link_m = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        textDecoration = TextDecoration.Underline,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    buttonLabel_xs = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    buttonLabel_s = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    buttonLabel_m = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
    buttonLabel_l = TextStyle(
        fontFamily = fagiols,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        platformStyle = PlatformTextStyle(
            includeFontPadding = false,
        ),
    ),
)