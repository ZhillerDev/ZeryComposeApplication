package com.zhiller.pureboard.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.domain.constant.DrawMode
import com.zhiller.pureboard.ui.view.canvas.CanvasBoard
import com.zhiller.pureboard.ui.view.canvas.NormalToolbar
import com.zhiller.pureboard.ui.view.canvas.PureToolbar

@Composable
fun CanvasScreen(
  layoutVM: LayoutViewModel,
  canvasVM: CanvasViewModel
) {
//  val isDark = isSystemInDarkTheme()
//  if (canvasVM.state.autoReverseColor) {
//    if (isDark) canvasVM.currentPath.color = Color.White
//    else canvasVM.currentPath.color = Color.Black
//  }
//  LaunchedEffect(key1 = isDark) {
//    if (canvasVM.state.autoReverseColor) {
//      if (isDark) canvasVM.currentPath.color = Color.White
//      else canvasVM.currentPath.color = Color.Black
//    }
//  }


  Scaffold(
    modifier = Modifier.fillMaxSize(),
    floatingActionButton = {
      AnimatedVisibility(visible = canvasVM.state.currentMode == DrawMode.PURE) {
        PureToolbar(canvasVM = canvasVM)
      }
    },
    floatingActionButtonPosition = FabPosition.End,
    bottomBar = {
      AnimatedVisibility(visible = canvasVM.state.currentMode == DrawMode.NORMAL) {
        NormalToolbar(layoutVM = layoutVM, canvasVM = canvasVM)
      }
//      if (homeUiState.showOptions) {
//
//      }else null
    },
    content = {
      Column(
        Modifier
          .fillMaxSize()
          .padding(it)
      ) {
        CanvasBoard(canvasVM = canvasVM)
      }
    }
  )
}

//@Preview(widthDp = 1100, heightDp = 700)
//@Composable
//fun PreviewFloat() {
//  Box(modifier = Modifier.fillMaxSize()) {
//    CanvasScreen(layoutVM = LayoutViewModel())
//  }
//}