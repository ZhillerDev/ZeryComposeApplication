package io.zhiller.esp.utils.other

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

object MessageUtil {
  @Composable
  fun Toast(title: String) {
    val ctx = LocalContext.current
    LaunchedEffect(Unit) {
      android.widget.Toast.makeText(ctx, title, android.widget.Toast.LENGTH_SHORT).show()
    }
  }
}