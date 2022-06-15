package com.ayd.cryptoapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color



private val DarkColorPalette = lightColors(
    primary = Color(0xFF6372C9),
    primaryVariant = RichBlack,
    secondary = Color(0xFFE1F5FE)

)


private val LightColorPalette = lightColors(
    primary = Color(0xFF6372C9),
    primaryVariant = RichBlack,
    secondary = Color(0xFFE1F5FE)

)

@Composable
fun CryptoAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}