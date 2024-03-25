package com.zhiller.pureboard.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zhiller.pureboard.R
import com.zhiller.pureboard.data.viewmodel.CanvasViewModel
import com.zhiller.pureboard.data.viewmodel.LayoutViewModel
import com.zhiller.pureboard.ui.component.PlainCard
import com.zhiller.pureboard.ui.component.PopDialog

@Composable
fun SettingScreen(
  layoutVM: LayoutViewModel,
  canvasVM: CanvasViewModel
) {
  var isSave by remember {
    mutableStateOf(false)
  }
  if (isSave) {
    PopDialog(
      title = "保存设置？",
      content = "确认保持当前设置？之前的设置都会被覆盖",
      onAccepted = {
        layoutVM.savePref()
        canvasVM.savePref()
        isSave = false
      },
      onCancelled = { isSave = false }
    )
  }

  Surface(
    modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Text(
          text = stringResource(id = R.string.setting_title),
          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
        )
        Button(onClick = { isSave = true }, modifier = Modifier.clip(CircleShape)) {
          Text(text = "保存设置")
        }
      }

      LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        item {
          /*主界面设置*/
          PlainCard {
            Text(
              text = "界面设置",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "显示侧边栏")
              Switch(checked = layoutVM.state.showNavRail, onCheckedChange = {
                layoutVM.state.showNavRail = !layoutVM.state.showNavRail
              }, modifier = Modifier.scale(0.7f))
            }
          }
          Spacer(modifier = Modifier.padding(bottom = 8.dp))

          /*画板设置*/
          PlainCard {
            Text(
              text = "画板设置",
              style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "画笔随明暗模式切换而自适应(即切换暗黑模式，笔白色，反之黑色)")
              Switch(checked = canvasVM.state.autoReverseColor, onCheckedChange = {
                canvasVM.state.autoReverseColor = !canvasVM.state.autoReverseColor
              }, modifier = Modifier.scale(0.7f))
            }
            Row(
              horizontalArrangement = Arrangement.spacedBy(6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = "应用关闭前自动记忆所有画布信息")
              Switch(checked = canvasVM.state.rememberAll, onCheckedChange = {
                canvasVM.state.rememberAll = !canvasVM.state.rememberAll
              }, modifier = Modifier.scale(0.7f))
            }
          }
          Spacer(modifier = Modifier.padding(bottom = 8.dp))
        }
      }
    }
  }
}

//@Preview(widthDp = 1100, heightDp = 700)
//@Composable
//fun PreviewSetting() {
//  Box {
//    SettingScreen(LayoutViewModel())
//  }
//}