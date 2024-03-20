package com.zhiller.pureboard.ui.view.canvas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Redo
import androidx.compose.material.icons.automirrored.filled.Undo
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RadioButtonChecked
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.zhiller.pureboard.R
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.domain.constant.DrawMode
import com.zhiller.pureboard.domain.constant.ExportFormat
import com.zhiller.pureboard.domain.constant.PenMode
import com.zhiller.pureboard.ui.component.PopDialog
import com.zhiller.pureboard.util.MessageUtil

@Composable
fun NormalToolbar(
  layoutVM: LayoutViewModel,
  canvasVM: CanvasViewModel
) {

  var isSave by remember {
    mutableStateOf(false)
  }
  var showToast by remember {
    mutableStateOf(0)
  }
  when (showToast) {
    1 -> {
      MessageUtil.Toast(title = "成功保存图片！")
      showToast = 0
    }

    2 -> {
      MessageUtil.Toast(title = "保存图片失败或取消保存")
      showToast = 0
    }
  }
  if (isSave) {
    PopDialog(
      title = "保存图片",
      content = "是否保存当前画布？图片将会默认保存于下载文件夹内",
      onAccepted = {
        canvasVM.saveBitmap(ExportFormat.PNG)
        showToast = 1
        isSave = false
      },
      onCancelled = {
        isSave = false
        showToast = 2
      },
    )
  }

  fun changeToolItem(index: Int, penMode: PenMode) {
    // 重复点击直接关掉展开的工具栏
    if (index == canvasVM.state.currentSelected) canvasVM.state.currentSelected = -1
    else canvasVM.state.currentSelected = index
    canvasVM.state.currentPenMode = penMode
    canvasVM.currentPath.penMode = penMode
  }

  Surface(
    modifier = Modifier
      .padding(10.dp)
      .clip(RoundedCornerShape(8.dp))
      .background(MaterialTheme.colorScheme.inverseOnSurface)
  ) {
    Column(
      modifier = Modifier
        .padding(start = 12.dp)
    ) {
      // 弹出绘画工具栏
      AnimatedVisibility(visible = canvasVM.state.currentSelected == 2) { PenSettingToolbar(canvasVM) }
      // 弹出画笔粗细工具栏
      AnimatedVisibility(visible = canvasVM.state.currentSelected == 3) {
        RadiusSettingToolbar(
          canvasVM
        )
      }

      // 底部按钮组
      Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {

        // 专业绘画操作按钮组
        Row(
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          IconButton(onClick = {
            changeToolItem(1, PenMode.None)
          }) {
            Icon(imageVector = Icons.Filled.BackHand, contentDescription = null)
          }
          IconButton(onClick = {
            changeToolItem(2, PenMode.Pen)
          }) {
            Icon(imageVector = Icons.Filled.ColorLens, contentDescription = null)
          }
          IconButton(onClick = {
            changeToolItem(3, PenMode.Pen)
          }) {
            Icon(imageVector = Icons.Filled.RadioButtonChecked, contentDescription = null)
          }
          IconButton(onClick = {
            changeToolItem(4, PenMode.Eraser)
          }) {
            Icon(
              painter = painterResource(id = R.drawable.ic_eraser_black_24dp),
              contentDescription = null
            )
          }
          IconButton(onClick = {
            if (canvasVM.paths.isNotEmpty()) {
              canvasVM.pathsUndone.add(canvasVM.paths.last())
              canvasVM.paths.removeLast()
            }
          }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Undo, contentDescription = null)
          }
          IconButton(onClick = {
            if (canvasVM.pathsUndone.isNotEmpty()) {
              canvasVM.paths.add(canvasVM.pathsUndone.last())
              canvasVM.pathsUndone.removeLast()
            }
          }) {
            Icon(imageVector = Icons.AutoMirrored.Filled.Redo, contentDescription = null)
          }
        }

        // 中间分割线
        Spacer(
          modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .height(28.dp)
            .padding(2.dp)
            .clip(CircleShape)
        )

        // 保存删除按钮组
        Row(
          horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
          IconButton(onClick = {
            canvasVM.updateState {
              showOptions = true
              currentMode = DrawMode.PURE
            }
          }) {
            Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
          }
          IconButton(onClick = { canvasVM.paths.clear() }) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
          }
          IconButton(onClick = {
            isSave = true
          }) {
            Icon(imageVector = Icons.Default.Save, contentDescription = null)
          }

          AnimatedVisibility(visible = !layoutVM.state.showNavRail) {
            IconButton(onClick = { layoutVM.state.showNavRail = true }) {
              Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
          }
        }
      }
    }
  }
}

//@Preview(widthDp = 1000)
//@Composable
//fun PreviewToolbar() {
//  Box {
//    NormalToolbar(canvasVM = CanvasViewModel(), layoutVM = LayoutViewModel())
//  }
//}