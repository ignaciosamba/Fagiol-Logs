package com.sambas.fagiollogs.core.design.theme

data class DesignColorSystem(
    val light: DesignColors,
    val dark: DesignColors,
)

// region consumer colors
internal val fagiolsColorSystem by lazy {
    DesignColorSystem(
        light = designColorsLight(fagiolsColorPalette),
        dark = designColorsDark(fagiolsColorPalette)
    )
}