package com.zhiller.pureboard.ui.view.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.toSize
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.domain.common.PenConfig
import com.zhiller.pureboard.domain.constant.MotionEvent

@Composable
fun CanvasBoard(
  canvasVM: CanvasViewModel
) {
  val drawModifier = Modifier
    .fillMaxSize()
    .background(MaterialTheme.colorScheme.background)
    .onSizeChanged {
      canvasVM.state.size = it.toSize()
      canvasVM.state.translation = Offset(it.width / 2f, it.height / 2f)
    }
    .pointerInput(Unit) {
      awaitEachGesture {
        val downEvent = awaitFirstDown()

        // 当用户开始绘画时，隐藏所有展开的工具栏
        canvasVM.state.currentSelected = -1

        canvasVM.currentPosition =
          (downEvent.position - canvasVM.state.translation) / canvasVM.state.scale
        canvasVM.motionEvent = MotionEvent.Down
        if (downEvent.pressed != downEvent.previousPressed) downEvent.consume()
        var canvasMoved = false
        do {
          val event = awaitPointerEvent()
          if (event.changes.size == 1) {
            if (canvasMoved) break
            canvasVM.currentPosition =
              (event.changes[0].position - canvasVM.state.translation) / canvasVM.state.scale
            canvasVM.motionEvent = MotionEvent.Move
            if (event.changes[0].positionChange() != Offset.Zero) event.changes[0].consume()
          } else if (event.changes.size > 1) {
            val zoom = event.calculateZoom()
            canvasVM.state.scale =
              (canvasVM.state.scale * zoom).coerceIn(0.5f..2f)
            val pan = event.calculatePan()
            canvasVM.state.translation += pan
            canvasMoved = true
          }
        } while (event.changes.any { it.pressed })
        canvasVM.motionEvent = MotionEvent.Up
      }
    }

  Canvas(modifier = drawModifier) {
    withTransform({
      translate(
        canvasVM.state.translation.x,
        canvasVM.state.translation.y
      )
      scale(
        canvasVM.state.scale,
        canvasVM.state.scale,
        canvasVM.state.pivot
      )
    }) {
      with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        when (canvasVM.motionEvent) {
          MotionEvent.Idle -> Unit
          MotionEvent.Down -> {
            canvasVM.paths.add(canvasVM.currentPath)
            canvasVM.currentPath.path.moveTo(
              canvasVM.currentPosition.x,
              canvasVM.currentPosition.y
            )
          }

          MotionEvent.Move -> {
            canvasVM.currentPath.path.lineTo(
              canvasVM.currentPosition.x,
              canvasVM.currentPosition.y
            )
            drawCircle(
              center = canvasVM.currentPosition,
              color = Color.Gray,
              radius = canvasVM.currentPath.strokeWidth / 2,
              style = Stroke(
                width = 1f
              )
            )
          }

          MotionEvent.Up -> {
            canvasVM.currentPath.path.lineTo(
              canvasVM.currentPosition.x,
              canvasVM.currentPosition.y
            )
            canvasVM.currentPath = PenConfig(
              path = Path(),
              strokeWidth = canvasVM.currentPath.strokeWidth,
              color = canvasVM.currentPath.color,
              penMode = canvasVM.currentPath.penMode
            )
            canvasVM.pathsUndone.clear()
            canvasVM.currentPosition = Offset.Unspecified
            canvasVM.motionEvent = MotionEvent.Idle
          }
        }
        canvasVM.paths.forEach { path ->
          path.draw(this@withTransform /*, bitmap*/)
        }
        restoreToCount(checkPoint)
      }
    }
  }

}