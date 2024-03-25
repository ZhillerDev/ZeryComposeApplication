package com.zhiller.pureboard.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhiller.pureboard.data.preference.UserPrefModel
import com.zhiller.pureboard.data.preference.PreferenceRepo
import com.zhiller.pureboard.data.state.LayoutState
import com.zhiller.pureboard.domain.constant.DrawMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LayoutViewModel @Inject constructor(
  private val preferenceRepo: PreferenceRepo
) : ViewModel() {

  val state: LayoutState = LayoutState()
  var firstStart by mutableStateOf(true)


  init {
    initData()
  }

  fun update(fn: LayoutState.() -> Unit) = viewModelScope.launch {
    state.fn()
  }

  private fun initData() = viewModelScope.launch {
    val result = preferenceRepo.getUserSetting()
    if (result.isSuccess) {
      result.getOrNull()?.apply {
        state.showNavRail = showRail
      }
    }
  }

  fun savePref() = viewModelScope.launch {
    val userPrefModel = UserPrefModel(
      showRail = state.showNavRail,
      currentMode = DrawMode.NORMAL.name
    )
    preferenceRepo.saveUserSetting(userPrefModel)
  }
}