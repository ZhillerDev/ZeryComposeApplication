package io.zhiller.esp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bluetooth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import io.zhiller.esp.R

object Routes {
  const val HOME = "home"
  const val BLUETOOTH = "bluetooth"
  const val SETTING = "setting"
}

data class RouterDestination(
  val route: String,
  val icon: ImageVector,
  val iconTextId: Int
)

val ROUTE_DESTINATION = listOf(
  RouterDestination(
    route = Routes.HOME,
    icon = Icons.Filled.Home,
    iconTextId = R.string.route_home
  ),
  RouterDestination(
    route = Routes.BLUETOOTH,
    icon = Icons.Filled.Bluetooth,
    iconTextId = R.string.route_bluetooth
  ),
  RouterDestination(
    route = Routes.SETTING,
    icon = Icons.Filled.Settings,
    iconTextId = R.string.route_setting
  ),
)