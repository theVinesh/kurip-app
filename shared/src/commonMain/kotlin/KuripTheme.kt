import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
internal fun KuripTheme(
    colors: Colors = lightColors(
        primary = KuripColors.primary,
        primaryVariant = KuripColors.primaryVariant,
        secondary = KuripColors.secondary,
        secondaryVariant = KuripColors.secondaryVariant,
        background = KuripColors.background,
        surface = KuripColors.surface,
        error = KuripColors.error,
        onPrimary = KuripColors.onPrimary,
        onSecondary = KuripColors.onSecondary,
        onBackground = KuripColors.onBackground,
        onSurface = KuripColors.onSurface,
        onError = KuripColors.onError,
    ),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}

object KuripColors {
    val paperWhite = Color(0xFFE1E1DD)
    val inkBlue = Color(0xFF0039A6)
    val brickRed = Color(0xFF7E2811)
    val crimsonRed = Color(0xFFB90E0A)
    val blushRed = Color(0xFFBC544B)
    val bloodRed = Color(0xFF710C04)

    val primary: Color = brickRed
    val primaryVariant: Color = crimsonRed
    val secondary: Color = blushRed
    val secondaryVariant: Color = bloodRed
    val background: Color = Color.White
    val surface: Color = paperWhite
    val error: Color = crimsonRed
    val onPrimary: Color = Color.White
    val onSecondary: Color = Color.White
    val onBackground: Color = Color.Black
    val onSurface: Color = inkBlue
    val onError: Color = Color.White

}
