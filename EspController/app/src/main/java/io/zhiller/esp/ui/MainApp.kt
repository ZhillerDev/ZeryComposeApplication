package io.zhiller.esp.ui

import android.content.ContentValues
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.zhiller.esp.data.viewmodel.LayoutViewModel
import io.zhiller.esp.ui.navigation.NavActions
import io.zhiller.esp.ui.navigation.NavBottomComp
import io.zhiller.esp.ui.navigation.NavHostComp
import io.zhiller.esp.ui.navigation.Routes
import io.zhiller.esp.utils.other.ComposableLifecycle

@Composable
fun MainApp() {
  val layoutVM = hiltViewModel<LayoutViewModel>()

  // 生命周期监听器
  // 当APP被关闭前自动保存setting数据到本地存储
  ComposableLifecycle { _, event ->
    when (event) {
      Lifecycle.Event.ON_CREATE -> {
        Log.d(ContentValues.TAG, "on Create")
      }

      Lifecycle.Event.ON_STOP -> {
        Log.d(ContentValues.TAG, "On Stop")
        layoutVM.saveData()
      }

      else -> {
        Log.d(ContentValues.TAG, "Other things")
      }
    }
  }

  MainAppWrapper(layoutVM = layoutVM)
}

@Composable
private fun MainAppWrapper(
  layoutVM: LayoutViewModel
) {
  val scope = rememberCoroutineScope()
  val navController = rememberNavController()
  val navActions = remember(navController) {
    NavActions(navController)
  }
  val navBackStackEntry by navController.currentBackStackEntryAsState()
  val selectedDestination =
    navBackStackEntry?.destination?.route ?: Routes.HOME

  Scaffold(
    bottomBar = {
      NavBottomComp(
        layoutVM = layoutVM,
        selectedDestination = selectedDestination,
        navToDestination = navActions::navTo,
      )
    }
  ) { it ->
    Box(
      Modifier.padding(it)
    ) {
      NavHostComp(
        navActions = navActions,
        navController = navController,
        layoutVM = layoutVM,
      )
    }
  }
//  Column(
//    modifier = Modifier
//      .fillMaxSize()
//      .windowInsetsPadding(WindowInsets.statusBars)
//  ) {
//    Box(
//      Modifier.fillMaxSize()
//    ) {
//      NavHostComp(
//        navActions = navActions,
//        navController = navController,
//        layoutVM = layoutVM,
//      )
//    }
//    NavBottomComp(
//      layoutVM = layoutVM,
//      selectedDestination = selectedDestination,
//      navToDestination = navActions::navTo,
//    )
//  }
}