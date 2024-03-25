package com.zhiller.pureboard.domain.constant

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint

// 定义两种绘画模式
enum class DrawMode {
  PURE, NORMAL
}

enum class PenMode {
  Pen, Eraser, None
}

enum class ExportFormat(
  val format: Bitmap.CompressFormat,
  val extension: String,
  var backgroundColor: Paint?
) {
  PNG(Bitmap.CompressFormat.PNG, "png", null),
  JPEG(Bitmap.CompressFormat.JPEG, "jpg", Paint().apply { color = Color.WHITE })
}

enum class MotionEvent {
  Idle, Down, Move, Up
}