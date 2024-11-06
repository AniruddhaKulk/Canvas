package com.kulkani.canvas.ui.theme

sealed class LineType {
    data object Default : LineType()
    data object Minor : LineType()
    data object Major : LineType()
}