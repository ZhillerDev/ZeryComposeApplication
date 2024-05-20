package io.zhiller.esp.ui.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.zhiller.esp.data.viewmodel.LayoutViewModel
import io.zhiller.esp.ui.screens.BluetoothScreen
import io.zhiller.esp.ui.screens.HomeScreen
import io.zhiller.esp.ui.screens.SettingScreen


@Composable
fun NavBottomComp(
  layoutVM: LayoutViewModel,
  selectedDestination: String,
  navToDestination: (RouterDestination) -> Unit,
) {
  NavigationBar(
    modifier = Modifier.fillMaxWidth(),
    containerColor = MaterialTheme.colorScheme.inverseOnSurface
  ) {
    Row {
      ROUTE_DESTINATION.forEach { item ->
        NavigationBarItem(
          selected = selectedDestination == item.route,
          onClick = { navToDestination(item) },
          label = { Text(text = stringResource(id = item.iconTextId)) },
          icon = {
            Icon(
              imageVector = item.icon,
              contentDescription = stringResource(id = item.iconTextId)
            )
          },
          alwaysShowLabel = true
        )
      }
    }
  }
}

@Composable
fun NavHostComp(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  layoutVM: LayoutViewModel,
  navActions: NavActions
) {
  NavHost(navController = navController, startDestination = Routes.HOME) {
    composable(Routes.HOME) {
      HomeScreen(layoutVM = layoutVM)
    }
    composable(Routes.BLUETOOTH) {
      BluetoothScreen(layoutVM = layoutVM)
    }
    composable(Routes.SETTING) {
      SettingScreen(layoutVM = layoutVM)
    }
  }
}