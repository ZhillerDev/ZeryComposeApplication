package io.zhiller.esp.data.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.zhiller.esp.data.service.DemoService
import io.zhiller.esp.domain.Demo
import io.zhiller.esp.utils.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DemoViewModel : ViewModel() {
  var resp by mutableStateOf("")
  private val demoService = RetrofitClient.retrofit.create(DemoService::class.java)

  fun sendPostRequest(demo: Demo) = viewModelScope.launch {
    val response = withContext(Dispatchers.IO) {
      demoService.createPost(demo).execute()
    }
    when {
      response.isSuccessful -> {
        resp = response.body()?.msg ?: "null"
      }

      else -> {
        // 处理请求失败的情况
      }
    }
  }
}
