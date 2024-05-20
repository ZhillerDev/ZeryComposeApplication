package io.zhiller.esp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun PopDialog(
  title: String = "注意",
  content: String = "确认执行此操作吗？",
  onAccepted: () -> Unit = {},
  onCancelled: () -> Unit = {},
) {

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