/*
 * Zalith Launcher 2
 * Copyright (C) 2025 MovTery <movtery228@qq.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 *
 * Modified for Nux Launcher 2 (2026). See NOTICE.md.
 */

package com.israadev.nuxlauncherv2.ui.theme

import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.materialkolor.PaletteStyle
import com.materialkolor.dynamicColorScheme
import com.israadev.nuxlauncherv2.setting.AllSettings
import com.israadev.nuxlauncherv2.setting.enums.isLauncherInDarkTheme
import com.israadev.nuxlauncherv2.ui.theme.components.activeMaskView
import com.israadev.nuxlauncherv2.utils.festival.Festival
import com.israadev.nuxlauncherv2.utils.festival.LocalFestivals
import com.israadev.nuxlauncherv2.viewmodel.BackgroundViewModel
import com.israadev.nuxlauncherv2.viewmodel.LocalBackgroundViewModel

/**
 * Forest green, taken from the launcher mark. Light mode leans on the dark tone,
 * because a pale green primary disappears on a light surface.
 */
private val NuxSeedLight = Color(0xFF2E6B45)

/**
 * Sage green, the accent of the launcher mark. Dark mode needs the lighter tone
 * so the primary stays legible on a near-black surface.
 */
private val NuxSeedDark = Color(0xFF6CB178)

private val nuxLight: ColorScheme = dynamicColorScheme(
    primary = NuxSeedLight,
    isDark = false,
    style = PaletteStyle.TonalSpot,
)

private val nuxDark: ColorScheme = dynamicColorScheme(
    primary = NuxSeedDark,
    isDark = true,
    style = PaletteStyle.TonalSpot,
)

private fun customLight(
    color: Color,
    style: PaletteStyle,
): ColorScheme {
    return dynamicColorScheme(
        primary = color,
        isDark = false,
        style = style,
    )
}

private fun customDark(
    color: Color,
    style: PaletteStyle,
): ColorScheme {
    return dynamicColorScheme(
        primary = color,
        isDark = true,
        style = style,
    )
}

@Composable
fun NuxLauncherTheme(
    darkTheme: Boolean = isLauncherInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    backgroundViewModel: BackgroundViewModel? = null,
    festivals: List<Festival> = emptyList(),
    content: @Composable () -> Unit
) {
    val colorTheme = AllSettings.launcherColorTheme.state
    val customColorInt = AllSettings.launcherCustomColor.state
    val customColor = Color(customColorInt)
    val customPaletteStyle = AllSettings.launcherCustomPaletteStyle.state

    val context = LocalContext.current

    val targetColorScheme = remember(darkTheme, dynamicColor, colorTheme, customColor, customPaletteStyle) {
        when {
            dynamicColor && colorTheme == ColorThemeType.DYNAMIC && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> when (colorTheme) {
                ColorThemeType.CUSTOM -> customDark(
                    color = customColor,
                    style = customPaletteStyle
                )
                else -> nuxDark
            }

            else -> when (colorTheme) {
                ColorThemeType.CUSTOM -> customLight(
                    color = customColor,
                    style = customPaletteStyle
                )
                else -> nuxLight
            }
        }
    }

    var currentDarkTheme by remember { mutableStateOf(darkTheme) }
    var currentDisplayScheme by remember { mutableStateOf(targetColorScheme) }

    LaunchedEffect(darkTheme, targetColorScheme) {
        if (darkTheme != currentDarkTheme) {
            context.activeMaskView(
                maskComplete = {
                    currentDarkTheme = darkTheme
                    currentDisplayScheme = targetColorScheme
                },
                maskAnimFinish = {

                }
            )
        } else {
            currentDarkTheme = darkTheme
            currentDisplayScheme = targetColorScheme
        }
    }

    CompositionLocalProvider(
        LocalBackgroundViewModel provides backgroundViewModel,
        LocalFestivals provides festivals
    ) {
        MaterialExpressiveTheme(
            colorScheme = currentDisplayScheme,
            motionScheme = MotionScheme.expressive(),
            typography = AppTypography,
            content = content
        )
    }
}
