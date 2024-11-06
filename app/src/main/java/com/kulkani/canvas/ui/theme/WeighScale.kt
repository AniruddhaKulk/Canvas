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

@Composable
fun WeightScale(
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
    }
}