package com.zhiller.pureboard.data.preference

import com.zhiller.pureboard.domain.constant.DrawMode

data class UserPrefModel(
  var showRail: Boolean = true,
  var currentMode: String = DrawMode.NORMAL.name,

)