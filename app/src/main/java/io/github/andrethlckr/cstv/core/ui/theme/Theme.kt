package io.github.andrethlckr.cstv.core.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

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

val Colors.placeholder: Color
    get() = SilverSand

val Colors.highlight: Color
    get() = ImperialRed

val Colors.secondaryText: Color
    get() = OldLavender

fun TextStyle.bolder() = copy(fontWeight = FontWeight.W700)

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
