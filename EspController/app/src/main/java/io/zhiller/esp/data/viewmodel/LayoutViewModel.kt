package io.zhiller.esp.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.zhiller.esp.data.state.LayoutState
import io.zhiller.esp.data.storage.LayoutPrefModel
import io.zhiller.esp.data.storage.PreferenceRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LayoutViewModel @Inject constructor(
  private val preferenceRepo: PreferenceRepo
) : ViewModel() {
  val state: LayoutState = LayoutState()

  init {
    initData()
  }

  fun update(fn: LayoutState.() -> Unit) = viewModelScope.launch {
    state.fn()
  }

  private fun initData() = viewModelScope.launch {
    val result = preferenceRepo.getLayoutSetting()
    if (result.isSuccess) {
      result.getOrNull()?.apply {
        state.espUrl = espUrl
        state.espPort = espPort
        state.backendUrl = backendUrl
        state.backendPort = backendPort
      }
    }
  }

  fun saveData() = viewModelScope.launch {
    val layoutPrefModel = LayoutPrefModel(
      espUrl = state.espUrl,
      espPort = state.espPort,
      backendUrl = state.backendUrl,
      backendPort = state.backendPort
    )
    preferenceRepo.saveLayoutSetting(layoutPrefModel)
  }
}