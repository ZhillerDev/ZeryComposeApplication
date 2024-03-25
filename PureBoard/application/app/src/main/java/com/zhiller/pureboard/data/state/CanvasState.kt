package com.zhiller.pureboard.data.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.zhiller.pureboard.domain.constant.DrawMode
import com.zhiller.pureboard.domain.constant.PenMode

class CanvasState {
  /*画图参数*/
  var scale by mutableFloatStateOf(1f)
  var translation by mutableStateOf(Offset(0f, 0f))
  var pivot by mutableStateOf(Offset(0f, 0f))
  var size: Size? = null

  /*工具栏参数*/
  var currentMode: DrawMode by mutableStateOf(DrawMode.NORMAL) // 绘画模式
  var currentPenMode: PenMode by mutableStateOf(PenMode.Pen)   // 画笔模式
  var showOptions: Boolean by mutableStateOf(true)        // 纯净模式是否展示
  var currentSelected: Int by mutableIntStateOf(-1)            // 当前选择的工具栏下标

  /*绘画工具栏参数*/
  var toggleDeepMode: Boolean by mutableStateOf(false)     // 是否进入深度修改颜色模式
  var autoReverseColor: Boolean by mutableStateOf(false)    // 自动翻转笔画颜色

  /*全局参数*/
  var rememberAll: Boolean by mutableStateOf(false)       // 关闭app前自动保存所有画板状态
}