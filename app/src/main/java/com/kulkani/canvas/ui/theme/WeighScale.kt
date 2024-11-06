package com.kulkani.canvas.ui.theme

import android.graphics.Paint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import android.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun WeighScale(
    modifier: Modifier = Modifier,
    style: WeighScaleStyle = WeighScaleStyle(),
    minWeight: Int = 40,
    maxWeight: Int = 200,
    initialWeight: Int = 55,
    onWeightChange: (Int) -> Unit,
) {
    val radius = style.radius
    val scaleWidth = style.width
    //Center of coordinate system
    var center by remember { mutableStateOf(Offset.Zero) }
    var circleCenter by remember { mutableStateOf(Offset.Zero) }
    Canvas(modifier = modifier) {
        center = this.center
        circleCenter = Offset(center.x, scaleWidth.toPx() / 2f + radius.toPx())
        val innerCircleRadius = radius.toPx() - scaleWidth.toPx() / 2f
        val outerCircleRadius = radius.toPx() + scaleWidth.toPx() / 2f
        drawContext.canvas.nativeCanvas.apply {
            drawCircle(
                circleCenter.x,
                circleCenter.y,
                radius.toPx(),
                Paint().apply {
                    strokeWidth = scaleWidth.toPx()
                    color = Color.WHITE
                    setStyle(Paint.Style.STROKE)
                    setShadowLayer(40f, 0f, 0f, Color.argb(50, 0, 0, 0))
                }
            )
        }
        for (i in minWeight..maxWeight) {
            val angleInRadiance = (i - initialWeight - 120) * (PI / 180).toFloat()
            val lineType = when {
                i % 10 == 0 -> LineType.Major
                i % 5 == 0 -> LineType.Minor
                else -> LineType.Default
            }
            val (lineLength, lineColor) = when (lineType) {
                LineType.Default -> style.defaultLineLength to style.defaultLineColor
                LineType.Minor -> style.minorLineLength to style.minorLineColor
                LineType.Major -> style.majorLineLength to style.majorLineColor
            }
            val lineStart = Offset(
                x = circleCenter.x + (outerCircleRadius - lineLength.toPx()) * cos(angleInRadiance),
                y = circleCenter.y + (outerCircleRadius - lineLength.toPx()) * sin(angleInRadiance)
            )
            val lineEnd = Offset(
                x = circleCenter.x + outerCircleRadius * cos(angleInRadiance),
                y = circleCenter.y + outerCircleRadius * sin(angleInRadiance)
            )
            drawLine(
                color = lineColor,
                strokeWidth = 1.dp.toPx(),
                start = lineStart,
                end = lineEnd
            )
            drawContext.canvas.nativeCanvas.apply {
                if (lineType is LineType.Major) {
                    val textDistance =
                        outerCircleRadius - lineLength.toPx() - 8.dp.toPx() - style.textSize.toPx()
                    val x = textDistance * cos(angleInRadiance) + circleCenter.x
                    val y = textDistance * sin(angleInRadiance) + circleCenter.y
                    drawText(
                        i.toString(),
                        x,
                        y,
                        Paint().apply {
                            textAlign = Paint.Align.CENTER
                            textSize = style.textSize.toPx()
                        }
                    )
                }
            }

        }
    }
}