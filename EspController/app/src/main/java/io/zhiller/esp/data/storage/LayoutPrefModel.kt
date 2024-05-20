package io.zhiller.esp.data.storage


data class LayoutPrefModel(
  var espUrl: String = "127.0.0.1",
  var espPort: String = "10086",
  var backendUrl: String = "127.0.0.1",
  var backendPort: String = "10085"
)