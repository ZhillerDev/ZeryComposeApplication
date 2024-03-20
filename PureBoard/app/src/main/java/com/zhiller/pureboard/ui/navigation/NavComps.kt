package com.zhiller.pureboard.ui.navigation

import AlbumScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.ui.screen.CanvasScreen
import com.zhiller.pureboard.ui.screen.NotFoundScreen
import com.zhiller.pureboard.ui.screen.SettingScreen
import com.zhiller.pureboard.util.MessageUtil

@Composable
fun NavRailComp(
  layoutVM: LayoutViewModel,
  selectedDestination: String,
  navToDestination: (RouterDestination) -> Unit,
) {
  var showToast by remember {
    mutableStateOf(false)
  }
  if (showToast) {
    showToast = false
    MessageUtil.Toast(title = "非常抱歉，您只能在画板界面隐藏侧边栏呜呜呜 QAQ")
  }

  NavigationRail(
    modifier = Modifier.fillMaxHeight(),
    containerColor = MaterialTheme.colorScheme.inverseOnSurface
  ) {
    Column(
      modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
    ) {
//      Icon(imageVector = Icons.Default.FormatPaint, contentDescription = null)
      NavigationRailItem(
        selected = false,
        onClick = {
          if (selectedDestination != Routes.CANVAS) showToast = true
          else layoutVM.state.showNavRail = false
          layoutVM.savePref()
        },
        icon = {
          Icon(
            imageVector = Icons.Default.FormatPaint,
            contentDescription = null
          )
        })
      Spacer(modifier = Modifier.height(8.dp))
      ROUTE_DESTINATION.forEach { item ->
        NavigationRailItem(
          selected = selectedDestination == item.route,
          onClick = { navToDestination(item) },
//          label = {
//            Text(text = stringResource(id = item.iconTextId))
//          },
          icon = {
            Icon(imageVector = item.icon, contentDescription = null)
          })
        Spacer(modifier = Modifier.height(4.dp))
      }
    }
  }
}


@Composable
fun NavHostComp(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  layoutVM: LayoutViewModel,
  canvasVM: CanvasViewModel,
  navActions: NavActions
) {
  NavHost(navController = navController, startDestination = Routes.CANVAS) {
    composable(Routes.CANVAS) {
      CanvasScreen(layoutVM = layoutVM, canvasVM = canvasVM)
    }
//    composable(Routes.ALBUM) {
//      AlbumScreen(layoutVM = layoutVM, canvasVM = canvasVM, navActions = navActions)
//    }
    composable(Routes.SETTING) {
      SettingScreen(layoutVM = layoutVM, canvasVM = canvasVM)
    }
//    composable(Routes.PERSON) {
//      NotFoundScreen()
//    }
  }
}

//@Preview(widthDp = 40)
//@Composable
//fun PreviewComp() {
//  Box() {
//    NavRailComp(layoutVM = LayoutViewModel(), selectedDestination = "", navToDestination = {})
//  }
//}

