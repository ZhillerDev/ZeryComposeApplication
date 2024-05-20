package io.zhiller.esp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.zhiller.esp.data.viewmodel.LayoutViewModel

@Composable
fun HomeScreen(
  layoutVM: LayoutViewModel
) {
  Box(modifier = Modifier.fillMaxSize()) {
    Text(text = "this is home")
  }
}