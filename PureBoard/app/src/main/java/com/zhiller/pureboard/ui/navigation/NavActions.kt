package com.zhiller.pureboard.ui.navigation

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel

class NavActions(
  private val navController: NavHostController,
) {
  fun navTo(destination: RouterDestination) {
    navController.navigate(destination.route) {
      popUpTo(navController.graph.findStartDestination().id) {
        saveState = true
      }
      launchSingleTop = true
      restoreState = true
    }
  }
}

// 获取回退底部的路由信息
fun GetBottomNav(navBackStackEntry: NavBackStackEntry?) {
  val value = navBackStackEntry?.arguments
  Log.d(TAG, "getBottomNav: $value")
}

