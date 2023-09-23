package ui

import KuripColors
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.dp

@Composable
fun KuripSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val punchHoleSize = 16.dp
    val punchHoleVerticalPadding = 48.dp
    val punchHoleHorizontalPadding = 4.dp
    val punchHoleColor = KuripColors.background
    val lineVerticalPadding = 24.dp
    val lineHorizontalPadding = 8.dp
    val lineColor = KuripColors.onBackground


    fun DrawScope.drawPunchHoles() {
        val height = size.height
        val noOfPunchHoles =
            height / (punchHoleSize + punchHoleVerticalPadding).toPx()
        repeat(noOfPunchHoles.toInt()) { i ->
            drawCircle(
                color = punchHoleColor,
                radius = punchHoleSize.toPx() / 2,
                center = Offset(
                    x = (punchHoleSize.toPx() / 2) + punchHoleHorizontalPadding.toPx(),
                    y = (punchHoleSize.toPx() + punchHoleVerticalPadding.toPx()) * i + punchHoleSize.toPx() / 2
                ),
                style = Fill
            )
        }
    }

    fun DrawScope.drawLines() {
        val height = size.height
        val noOfLines = height / lineVerticalPadding.toPx()
        repeat(noOfLines.toInt()) { i ->
            val yOffset = lineVerticalPadding.toPx() * i
            val pathEffect = if (i == 0)
                PathEffect.dashPathEffect(floatArrayOf(5.dp.toPx(), 5.dp.toPx()), 0f)
            else null

            drawLine(
                color = lineColor,
                strokeWidth = 1.dp.toPx(),
                pathEffect = pathEffect,
                alpha = 0.2f,
                start = Offset(
                    x = 0f + lineHorizontalPadding.toPx(),
                    y = yOffset
                ),
                end = Offset(
                    x = size.width - lineHorizontalPadding.toPx(),
                    y = yOffset
                ),
            )
        }
    }

    Surface(
        modifier = modifier
            .defaultMinSize(minHeight = punchHoleSize + punchHoleVerticalPadding)
    ) {
        Row(
            modifier = Modifier.drawBehind {
                drawLines()
                drawPunchHoles()
            }
        ) {
            Spacer(modifier = Modifier.width(punchHoleSize + punchHoleHorizontalPadding * 2))
            content()
        }
    }
}
