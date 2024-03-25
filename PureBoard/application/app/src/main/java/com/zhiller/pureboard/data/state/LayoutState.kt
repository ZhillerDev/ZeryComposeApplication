package com.zhiller.pureboard.data.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class LayoutState {
  var showNavRail: Boolean by mutableStateOf(true)
  var expandNavRail: Boolean by mutableStateOf(false)
}