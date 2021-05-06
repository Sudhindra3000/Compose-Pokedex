package com.sudhindra.composepokedex.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = DarkPrimary,
    primaryVariant = DarkPrimary,
    secondary = DarkSecondary
)

private val LightColorPalette = lightColors(
    primary = LightPrimary,
    primaryVariant = LightPrimary,
    secondary = LightSecondary
)

@Composable
fun ComposePokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
