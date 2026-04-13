package com.inkfolio.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary          = Gold,
    onPrimary        = Background,
    background       = Background,
    onBackground     = OnSurface,
    surface          = SurfaceColor,
    onSurface        = OnSurface,
    surfaceVariant   = SurfaceVar,
    onSurfaceVariant = OnSurfaceMuted
)

@Composable
fun InkFolioTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        content     = content
    )
}
