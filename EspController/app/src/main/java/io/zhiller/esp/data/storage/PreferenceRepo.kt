package io.zhiller.esp.data.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


interface PreferenceRepo {
  suspend fun saveLayoutSetting(layoutPrefModel: LayoutPrefModel)
  suspend fun getLayoutSetting(): Result<LayoutPrefModel>
}

class PreferenceRepoImpl @Inject constructor(
  private val preferenceStore: DataStore<Preferences>
) : PreferenceRepo {
  private companion object {
    // 全局设置，包括ESP以及后端的所有数据
    val ESP_URL = stringPreferencesKey("ESP_URL")
    val ESP_PORT = stringPreferencesKey("ESP_PORT")
    val BACKEND_URL = stringPreferencesKey("BACKEND_URL")
    val BACKEND_PORT = stringPreferencesKey("BACKEND_PORT")
  }

  override suspend fun saveLayoutSetting(layoutPrefModel: LayoutPrefModel) {
    Result.runCatching {
      preferenceStore.edit {
        it[ESP_URL] = layoutPrefModel.espUrl
        it[ESP_PORT] = layoutPrefModel.espPort
        it[BACKEND_URL] = layoutPrefModel.backendUrl
        it[BACKEND_PORT] = layoutPrefModel.backendPort
      }
    }
  }

  override suspend fun getLayoutSetting(): Result<LayoutPrefModel> {
    return Result.runCatching {
      val flow = preferenceStore.data.catch { exp ->
        if (exp is IOException) emit(emptyPreferences())
        else throw exp
      }
        .map { it ->
          LayoutPrefModel(
            espUrl = it[ESP_URL] ?: "127.0.0.1",
            espPort = it[ESP_PORT] ?: "10086",
            backendUrl = it[BACKEND_URL] ?: "127.0.0.1",
            backendPort = it[BACKEND_PORT] ?: "10085"
          )
        }
      val value = flow.firstOrNull() ?: LayoutPrefModel()
      value
    }
  }

}