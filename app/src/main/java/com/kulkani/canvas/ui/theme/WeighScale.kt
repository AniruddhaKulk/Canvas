package com.kulkani.canvas.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.foundation.Canvas
import androidx.compose.ui.graphics.Color

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
        drawCircle(
            color = Color.White,
            center = circleCenter,
            radius = radius.toPx(),
        )
    }
}