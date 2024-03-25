package com.zhiller.pureboard.ui.view.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.ui.component.PlainCard
import com.zhiller.pureboard.ui.component.PlainWrapCard

@Composable
fun RadiusSettingToolbar(
  canvasVM: CanvasViewModel
) {
  var brushSize by remember { mutableFloatStateOf(canvasVM.currentPath.strokeWidth) }
  PlainCard {
    Text(
      text = "画笔粗细",
      style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
    )
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Canvas(
        modifier = Modifier
          .padding(20.dp)
          .size(20.dp)
      ) {
        drawCircle(
          center = Offset(size.width / 2, size.height / 2),
          color = canvasVM.currentPath.color,
          radius = brushSize * canvasVM.state.scale / 2
        )
      }
      Slider(
        modifier = Modifier.weight(1f).padding(start = 10.dp),
        value = brushSize,
        onValueChange = { size ->
          brushSize = size
          canvasVM.currentPath.strokeWidth = brushSize
        },
        valueRange = 1f..150f
      )
      Text(modifier = Modifier.width(100.dp), text = brushSize.toString())
    }
  }
}