package com.zhiller.pureboard.ui.component

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.zhiller.pureboard.util.MessageUtil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopDialog(
  title: String = "注意",
  content: String = "确认执行此操作吗？",
  onAccepted: () -> Unit = {},
  onCancelled: () -> Unit = {},
) {
//  val context = LocalContext.current
//  Dialog(onDismissRequest = onCancelled) {
//    Surface(
//      modifier = Modifier.width(300.dp),
//      shape = RoundedCornerShape(10.dp)
//    ) {
//      Column(
//        modifier = Modifier.padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(10.dp)
//      ) {
//        Text(
//          text = title,
//          style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
//        )
//        Text(text = content, style = MaterialTheme.typography.bodyMedium)
//
//        Row(
//          Modifier
//            .fillMaxWidth(),
//          verticalAlignment = Alignment.CenterVertically,
//          horizontalArrangement = Arrangement.spacedBy(10.dp)
//        ) {
//          Button(onClick = onCancelled, Modifier.weight(1f)) {
//            Text(text = "取消")
//          }
//          Button(onClick = onAccepted, Modifier.weight(1f)) {
//            Text(text = "确认")
//          }
//        }
//      }
//    }
//  }

  AlertDialog(
    onDismissRequest = onCancelled,
    title = { Text(text = title) },
    text = { Text(text = content) },
    confirmButton = {
      TextButton(onClick = {
        onAccepted()
      }) {
        Text(text = "确认")
      }
    },
    dismissButton = {
      TextButton(onClick = onCancelled) {
        Text(text = "取消")
      }
    },
  )
}