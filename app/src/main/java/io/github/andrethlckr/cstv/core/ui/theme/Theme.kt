package io.github.andrethlckr.cstv.core.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val ColorPalette = darkColors(
    background = EerieBlack,
    surface = YankeesBlue,
    onBackground = Color.White,
    onSurface = Color.White,


    primary = Color.Black,
    secondary = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
)

val Colors.highlightColor: Color
    get() = ImperialRed

val Colors.placeholderColor: Color
    get() = SilverSand

val Colors.secondaryTextColor: Color
    get() = OldLavender

@Composable
fun CSTVTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colors = ColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
