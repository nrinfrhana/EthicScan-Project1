package com.example.a212019_nurinfarhana_drrimaniza_project1.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val EthicScanColors = lightColorScheme(
    primary             = Green800,
    onPrimary           = White,
    primaryContainer    = Green100,
    onPrimaryContainer  = Green900,
    secondary           = Green600,
    onSecondary         = White,
    secondaryContainer  = Green50,
    onSecondaryContainer= Green800,
    background          = Beige,
    onBackground        = TextPrimary,
    surface             = White,
    onSurface           = TextPrimary,
    surfaceVariant      = BeigeCard,
    onSurfaceVariant    = TextSecondary,
    error               = ErrorRed,
    onError             = White,
    outline             = BeigeDark,
)

@Composable
fun EthicScanTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = EthicScanColors,
        typography  = Typography,
        content     = content
    )
}