package com.mada.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val MadaColorScheme = darkColorScheme(
    primary             = MadaPurple,
    onPrimary           = MadaText,
    primaryContainer    = MadaPurpleDim,
    onPrimaryContainer  = MadaPurple,
    secondary           = MadaTeal,
    onSecondary         = MadaText,
    secondaryContainer  = MadaTealDim,
    onSecondaryContainer= MadaTeal,
    tertiary            = MadaYellow,
    tertiaryContainer   = MadaYellowDim,
    onTertiaryContainer = MadaYellow,
    background          = MadaBg,
    onBackground        = MadaText,
    surface             = MadaSurface,
    onSurface           = MadaText,
    surfaceVariant      = MadaSurface2,
    onSurfaceVariant    = MadaText2,
    outline             = MadaLine,
    outlineVariant      = MadaSurface3,
)

@Composable
fun MadaTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MadaColorScheme,
        typography  = MadaTypography,
        content     = content
    )
}
