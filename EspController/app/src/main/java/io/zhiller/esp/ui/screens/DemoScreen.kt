package io.zhiller.esp.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import io.zhiller.esp.domain.Demo
import io.zhiller.esp.data.viewmodel.DemoViewModel

@Composable
fun DemoScreen() {
  val viewModel: DemoViewModel = viewModel()
  val postData = Demo(light = "asd", button = "asd")
  Button(onClick = {
    viewModel.sendPostRequest(postData)
  }) {
    Text(viewModel.resp)
  }
}