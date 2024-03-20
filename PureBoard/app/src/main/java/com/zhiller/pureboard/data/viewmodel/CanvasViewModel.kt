package com.zhiller.pureboard.data.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhiller.pureboard.data.preference.CanvasPrefModel
import com.zhiller.pureboard.data.preference.PreferenceRepo
import com.zhiller.pureboard.data.preference.UserPrefModel
import com.zhiller.pureboard.data.state.CanvasState
import com.zhiller.pureboard.domain.common.PenConfig
import com.zhiller.pureboard.domain.constant.DrawMode
import com.zhiller.pureboard.domain.constant.ExportFormat
import com.zhiller.pureboard.domain.constant.MotionEvent
import com.zhiller.pureboard.util.StorageUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class CanvasViewModel @Inject constructor(
  private val preferenceRepo: PreferenceRepo
) : ViewModel() {
  var paths = mutableStateListOf<PenConfig>()
  var pathsUndone = mutableStateListOf<PenConfig>()
  var motionEvent by mutableStateOf(MotionEvent.Idle)
  var currentPath by mutableStateOf(PenConfig())
  var currentPosition by mutableStateOf(Offset.Unspecified)

  val state = CanvasState()

  init {
    initData()
  }

  private fun initData() = viewModelScope.launch {
    val result = preferenceRepo.getCanvasSetting()
    if (result.isSuccess) {
      result.getOrNull()?.apply {
        state.autoReverseColor = autoReverseColor
        state.rememberAll = rememberAllSettings

        if (rememberAllSettings) {
          currentPath.strokeWidth = strokeWidth
//          currentPath.color = Color(penColor)
        }
      }
    }
  }

  fun updateState(fn: CanvasState.() -> Unit) = viewModelScope.launch {
    state.fn()
  }

  fun savePref() = viewModelScope.launch {
    val canvasPrefModel = CanvasPrefModel(
      autoReverseColor = state.autoReverseColor,
      rememberAllSettings = state.rememberAll,
      strokeWidth = currentPath.strokeWidth,
//      penColor = currentPath.color.value.toLong()
    )
    preferenceRepo.saveCanvasSetting(canvasPrefModel)
  }

  fun saveBitmap(format: ExportFormat) {
    if (state.size != null && paths.isNotEmpty()) {
      viewModelScope.launch {
        withContext(Dispatchers.IO) {
          val bitmap =
            Bitmap.createBitmap(
              state.size!!.width.toInt(),
              state.size!!.height.toInt(),
              Bitmap.Config.ARGB_8888
            )
          val canvas = Canvas(bitmap)
          format.backgroundColor?.let { bgColor ->
            canvas.drawPaint(bgColor)
          }
          canvas.translate(state.translation.x, state.translation.y)
          canvas.scale(state.scale, state.scale)
          val outputStream =
            FileOutputStream(StorageUtil.getDownloadPath(format.extension))

          paths.forEach { path ->
            path.drawNative(canvas)
          }

          bitmap.compress(format.format, 100, outputStream)

          outputStream.flush()
          outputStream.close()
        }
      }
    }
  }
}