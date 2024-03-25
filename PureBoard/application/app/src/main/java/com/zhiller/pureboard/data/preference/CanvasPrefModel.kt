package com.zhiller.pureboard.data.preference

import androidx.compose.ui.graphics.Color

data class CanvasPrefModel(
  val autoReverseColor: Boolean = false,
  val rememberAllSettings: Boolean = false,
  val strokeWidth: Float = 10f,
  val penColor: Long = Color.Blue.value.toLong()
)