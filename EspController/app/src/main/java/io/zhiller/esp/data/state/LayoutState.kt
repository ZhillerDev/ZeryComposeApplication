package io.zhiller.esp.data.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

// 全局配置状态
class LayoutState {
  // ESP提供的WIFI地址
  var espUrl: String by mutableStateOf("127.0.0.1")

  // ESP提供的WIFI端口（用于发送消息）
  var espPort: String by mutableStateOf("10086")

  // 后端地址
  var backendUrl: String by mutableStateOf("127.0.0.1")

  // 后端端口
  var backendPort: String by mutableStateOf("10085")
}