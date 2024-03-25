package com.zhiller.pureboard.ui.view.album

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.ui.navigation.NavActions
import com.zhiller.pureboard.ui.navigation.ROUTE_DESTINATION
import com.zhiller.pureboard.ui.navigation.RouterDestination
import com.zhiller.pureboard.ui.navigation.Routes
import com.zhiller.pureboard.util.IntentUtil

private class AlbumItems(
  val icon: ImageVector,
  val onClick: () -> Unit = {}
)

@Composable
fun AlbumBottomToolbar(navActions: NavActions) {
  val context = LocalContext.current

  var currentSelected: Int by remember {
    mutableIntStateOf(0)
  }
  when (currentSelected) {
    1 -> {
      IntentUtil.OpenGallery(context)
    }

    2 -> {
      IntentUtil.OpenDownload(context)
    }

    else -> {}
  }
  fun changeSelected(index: Int) {
    currentSelected = 0
    currentSelected = 1
  }

  val items = listOf(
    AlbumItems(Icons.Default.Photo) { changeSelected(1) },
    AlbumItems(Icons.Default.Download) { changeSelected(2) }
  )

  BottomAppBar(
    modifier = Modifier.height(70.dp),
    actions = {
      items.forEach {
        IconButton(onClick = { it.onClick }) {
          Icon(imageVector = it.icon, contentDescription = null)
        }
      }
    },
    floatingActionButton = {
      SmallFloatingActionButton(onClick = {
        navActions.navTo(ROUTE_DESTINATION[0])

      }) {
        Icon(imageVector = Icons.Filled.Edit, contentDescription = null)
      }
    }
  )
}

