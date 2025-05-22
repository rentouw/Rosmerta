package com.rentouw.rosmerta.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define your color palettes
// These would typically be in Color.kt but can be here for simplicity if Color.kt is also missing
private val DarkColorScheme = darkColorScheme(
    primary = Purple80, // Defined in your Color.kt or below
    secondary = PurpleGrey80, // Defined in your Color.kt or below
    tertiary = Pink80 // Defined in your Color.kt or below
    // You can add more overrides: background, surface, onPrimary, onError, etc.
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40, // Defined in your Color.kt or below
    secondary = PurpleGrey40, // Defined in your Color.kt or below
    tertiary = Pink40, // Defined in your Color.kt or below

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun RosmertaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    // Set to false if you prefer to strictly use your defined color schemes
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            // window?.statusBarColor = colorScheme.primary.toArgb() // Set status bar color // obsolite option on 14+
            WindowCompat.getInsetsController(window ?: return@SideEffect, view).isAppearanceLightStatusBars = !darkTheme // Set status bar icons' color
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Defined in your Type.kt
        // shapes = Shapes, // Optional: If you have custom shapes defined in Shapes.kt
        content = content
    )
}