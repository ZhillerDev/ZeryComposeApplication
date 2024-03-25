package com.zhiller.pureboard.ui.view.canvas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.util.copy
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.domain.constant.DrawMode
import com.zhiller.pureboard.ui.component.PopDialog

@Composable
fun PureToolbar(
  modifier: Modifier = Modifier,
  canvasVM: CanvasViewModel,
) {
  var showSaveDialog by remember { mutableStateOf(false) }

  if (showSaveDialog) PopDialog(onCancelled = { showSaveDialog = false })

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {


    Crossfade(targetState = !canvasVM.state.showOptions, label = "") {
      AnimatedVisibility(
        visible = !it,
        enter = fadeIn(animationSpec = tween(500)),
        exit = fadeOut(animationSpec = tween(100))
      ) {
        Column(
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          FloatingActionButton(
            onClick = {
              canvasVM.paths.clear()
            },
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
          ) {
            Icon(imageVector = Icons.Filled.Delete, contentDescription = null)
          }
//          FloatingActionButton(
//            onClick = { showSaveDialog = true },
//            shape = CircleShape,
//            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
//          ) {
//            Icon(imageVector = Icons.Default.Save, contentDescription = null)
//          }
          FloatingActionButton(
            onClick = {
              canvasVM.updateState {
                currentMode = DrawMode.NORMAL
                showOptions = false
              }
            },
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
          ) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = null)
          }
        }
      }
    }

    FloatingActionButton(
      onClick = { canvasVM.state.showOptions = !canvasVM.state.showOptions },
      shape = CircleShape,
      elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
    ) {
//      if (homeUiState.showOptions) Icon(imageVector = Icons.Default.Close, contentDescription = null)
//      else Icon(imageVector = Icons.Default.Add, contentDescription = null)
      Crossfade(targetState = canvasVM.state.showOptions, label = "") {
        if (it) {
          AnimatedVisibility(
            visible = it,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(100))
          ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = null)
          }
        } else {
          AnimatedVisibility(
            visible = !it,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(100))
          ) {
            Icon(imageVector = Icons.Default.Menu, contentDescription = null)
          }
        }
      }
    }
  }
}