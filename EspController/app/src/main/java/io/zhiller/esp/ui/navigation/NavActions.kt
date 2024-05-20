package io.zhiller.esp.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

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