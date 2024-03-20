package com.zhiller.pureboard.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Draw
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.zhiller.pureboard.R

object Routes {
  const val CANVAS = "canvas"
//  const val ALBUM = "album"
  const val SETTING = "setting"
//  const val PERSON = "person"
}

data class RouterDestination(
  val route: String,
  val icon: ImageVector,
  val iconTextId: Int
)

val ROUTE_DESTINATION = listOf(
  RouterDestination(
    route = Routes.CANVAS,
    icon = Icons.Filled.Draw,
    iconTextId = R.string.route_canvas
  ),
//  RouterDestination(
//    route = Routes.ALBUM,
//    icon = Icons.Filled.Folder,
//    iconTextId = R.string.route_album
//  ),
  RouterDestination(
    route = Routes.SETTING,
    icon = Icons.Filled.Settings,
    iconTextId = R.string.route_setting
  ),
//  RouterDestination(
//    route = Routes.PERSON,
//    icon = Icons.Filled.Person,
//    iconTextId = R.string.route_person
//  )
)