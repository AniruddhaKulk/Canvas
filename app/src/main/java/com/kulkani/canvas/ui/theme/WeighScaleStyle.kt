package com.kulkani.canvas.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class WeighScaleStyle(
    val width: Dp = 100.dp,
    val radius: Dp = 550.dp,
    val textSize: TextUnit = 18.sp,
    val defaultLineColor: Color = Color.Gray,
    val minorLineColor: Color = Color.Green,
    val majorLineColor: Color = Color.Black,
    val scaleIndicatorColor: Color = Color.Red,
    val defaultLineLength: Dp = 10.dp,
    val minorLineLength: Dp = 20.dp,
    val majorLineLength: Dp = 30.dp,
    val scaleIndicatorLength: Dp = 50.dp
)
