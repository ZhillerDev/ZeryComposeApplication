package com.zhiller.pureboard.util

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext

object MessageUtil {
  @Composable
  fun Toast(title: String) {
    val ctx = LocalContext.current
    LaunchedEffect(Unit) {
      Toast.makeText(ctx, title, Toast.LENGTH_SHORT).show()
    }
  }
}