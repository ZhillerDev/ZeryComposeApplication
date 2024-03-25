package com.zhiller.pureboard.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.ui.navigation.NavActions
import com.zhiller.pureboard.ui.navigation.NavHostComp
import com.zhiller.pureboard.ui.navigation.NavRailComp
import com.zhiller.pureboard.ui.navigation.Routes
import com.zhiller.pureboard.util.ComposableLifecycle


@Composable
fun PureBoardApp() {
  val layoutVM = hiltViewModel<LayoutViewModel>()
  val canvasVM = hiltViewModel<CanvasViewModel>()

  /*首次初始化时才会对颜色执行自动翻转，后续将关闭此功能*/
  val isDark = isSystemInDarkTheme()
  if (canvasVM.state.autoReverseColor and layoutVM.firstStart) {
    layoutVM.firstStart = !layoutVM.firstStart
    if (isDark) canvasVM.currentPath.color = Color.White
    else canvasVM.currentPath.color = Color.Black
  }

  ComposableLifecycle { _, event ->
    when (event) {
      Lifecycle.Event.ON_CREATE -> {
        Log.d(TAG, "onCreate")
      }

      Lifecycle.Event.ON_STOP -> {
        Log.d(TAG, "On Stop")
        // 仅当用户允许保存笔画信息时，才允许程序进入后台自动存储
        // 只要app进入后台则必定触发此生命周期回调，所以都能保证在用户关闭app前存储笔画数据
        // 强制后台杀死app的情况除外
        if (canvasVM.state.rememberAll) {
          canvasVM.savePref()
        }
      }

      else -> {}
    }
  }

  PureLayoutWrapper(layoutVM, canvasVM)
}

@Composable
private fun PureLayoutWrapper(layoutVM: LayoutViewModel, canvasVM: CanvasViewModel) {
  val scope = rememberCoroutineScope()

  val navController = rememberNavController()
  val navActions = remember(navController) {
    NavActions(navController)
  }
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val selectedDestination =
    navBackStackEntry?.destination?.route ?: Routes.CANVAS

  Row(
    modifier = Modifier.fillMaxSize()
  ) {
    AnimatedVisibility(visible = layoutVM.state.showNavRail) {
      NavRailComp(
        layoutVM = layoutVM,
        selectedDestination = selectedDestination,
        navToDestination = navActions::navTo,
      )
    }
    Box(
      Modifier.fillMaxSize()
    ) {
      NavHostComp(
        navActions = navActions,
        navController = navController,
        layoutVM = layoutVM,
        canvasVM = canvasVM
      )
    }
  }
}