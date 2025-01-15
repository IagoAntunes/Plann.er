package com.iagoaf.plannerjetpack.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.iagoaf.plannerjetpack.R

private val DarkColorScheme = darkColorScheme(
    primary = lime300,
    secondary = lime950,
    tertiary = zinc500
)

private val LightColorScheme = lightColorScheme(
    primary = lime300,
    secondary = lime950,
    tertiary = zinc500

)

val interFontFamily = FontFamily(
    Font(R.font.inter_regular, FontWeight.Medium),
    Font(R.font.inter_extrabold, FontWeight.ExtraBold),
    Font(R.font.inter_medium, FontWeight.Medium),
    Font(R.font.inter_semibold, FontWeight.SemiBold),
    Font(R.font.inter_24_semibold, FontWeight.SemiBold),
    Font(R.font.inter_thin, FontWeight.Thin),
)


@Composable
fun PlannerJetpackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography.Typography,
        content = content
    )
}