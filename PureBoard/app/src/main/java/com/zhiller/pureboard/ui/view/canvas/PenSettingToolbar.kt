package com.zhiller.pureboard.ui.view.canvas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.ui.component.PlainWrapCard

@Composable
fun PenSettingToolbar(
  canvasVM: CanvasViewModel
) {
  PlainWrapCard {
    Row(
      horizontalArrangement = Arrangement.spacedBy(12.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "绘图设置",
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
      )
      Text(
        text = if (canvasVM.state.toggleDeepMode) "深度颜色修改模式" else "快速颜色选择模式",
        style = MaterialTheme.typography.titleSmall
      )
      Switch(
        modifier = Modifier.scale(0.7f),
        checked = canvasVM.state.toggleDeepMode,
        onCheckedChange = {
          canvasVM.state.toggleDeepMode = !canvasVM.state.toggleDeepMode
        })
    }

    AnimatedVisibility(visible = canvasVM.state.toggleDeepMode) {
      DeepColorSelector(canvasVM)
    }
    AnimatedVisibility(visible = !canvasVM.state.toggleDeepMode) {
      QuickColorSelector(canvasVM)
    }
  }
}

@Composable
private fun DeepColorSelector(canvasVM: CanvasViewModel) {
  Column {
    Text(text = "深度选择模式")
  }
}

@Composable
private fun QuickColorSelector(canvasVM: CanvasViewModel) {
  val colorList = listOf(
    Color.White,
    Color.Black,
    Color(0xFFFF0040),
    Color(0xFFFF00FF),
    Color(0xFF8000FF),
    Color(0xFF0000FF),
    Color(0xFF0080FF),
    Color(0xFF00FFFF),
    Color(0xFF00FF80),
    Color(0xFF00FF00),
    Color(0xFF80FF00),
    Color(0xFFFFFF00),
    Color(0xFFFF8000),
  )

  LazyRow {
    items(colorList) {
//      Button(
//        modifier = Modifier
//          .padding(end = 8.dp)
//          .width(60.dp)
//          .height(40.dp),
//        shape = RoundedCornerShape(8.dp),
//
//        onClick = { canvasVM.currentPath.color = it }
//      ) {
//
//      }
      Box(
        modifier =
        Modifier
          .width(50.dp)
          .height(30.dp)
          .background(it, RoundedCornerShape(8.dp))
          .clip(RoundedCornerShape(8.dp))
          .clickable { canvasVM.currentPath.color = it }
      )
      Spacer(modifier = Modifier.padding(start = 8.dp))
    }
  }
}