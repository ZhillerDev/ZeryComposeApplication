package com.zhiller.pureboard.data.preference

import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.zhiller.pureboard.domain.constant.DrawMode
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

interface PreferenceRepo {
  suspend fun saveUserSetting(userPrefModel: UserPrefModel)
  suspend fun getUserSetting(): Result<UserPrefModel>
  suspend fun saveCanvasSetting(canvasPrefModel: CanvasPrefModel)
  suspend fun getCanvasSetting(): Result<CanvasPrefModel>
}

class PreferenceRepoImpl @Inject constructor(
  private val preferenceStore: DataStore<Preferences>
) : PreferenceRepo {
  override suspend fun saveUserSetting(userPrefModel: UserPrefModel) {
    Result.runCatching {
      preferenceStore.edit {
        it[SHOW_RAIL] = userPrefModel.showRail
        it[CURRENT_MODE] = userPrefModel.currentMode
      }
    }
  }

  override suspend fun getUserSetting(): Result<UserPrefModel> {
    return Result.runCatching {
      val flow = preferenceStore.data.catch { exp ->
        if (exp is IOException) emit(emptyPreferences())
        else throw exp
      }
        .map { item ->
          UserPrefModel(
            showRail = item[SHOW_RAIL] ?: true,
            currentMode = item[CURRENT_MODE] ?: DrawMode.NORMAL.name,
          )
        }
      val value = flow.firstOrNull() ?: UserPrefModel()
      value
    }
  }

  override suspend fun saveCanvasSetting(canvasPrefModel: CanvasPrefModel) {
    Result.runCatching {
      preferenceStore.edit {
        it[AUTO_REVERSE_COLOR] = canvasPrefModel.autoReverseColor
        it[REMEMBER_ALL_SETTINGS] = canvasPrefModel.rememberAllSettings
        it[STROKE_WIDTH] = canvasPrefModel.strokeWidth
        it[PEN_COLOR] = canvasPrefModel.penColor
      }
    }
  }

  override suspend fun getCanvasSetting(): Result<CanvasPrefModel> {
    return Result.runCatching {
      val flow = preferenceStore.data.catch { exp ->
        if (exp is IOException) emit(emptyPreferences())
        else throw exp
      }
        .map { item ->
          CanvasPrefModel(
            autoReverseColor = item[AUTO_REVERSE_COLOR] ?: false,
            rememberAllSettings = item[REMEMBER_ALL_SETTINGS] ?: false,
            strokeWidth = item[STROKE_WIDTH] ?: 10f,
            penColor = item[PEN_COLOR] ?: Color.Blue.value.toLong()
          )
        }
      val value = flow.firstOrNull() ?: CanvasPrefModel()
      value
    }
  }

  private companion object {
    // 用户设置
    val SHOW_RAIL = booleanPreferencesKey("SHOW_RAIL")
    val CURRENT_MODE = stringPreferencesKey("CURRENT_MODE")

    // 画板设置
    val AUTO_REVERSE_COLOR = booleanPreferencesKey("AUTO_REVERSE_COLOR")
    val REMEMBER_ALL_SETTINGS = booleanPreferencesKey("REMEMBER_ALL_SETTINGS")
    val STROKE_WIDTH = floatPreferencesKey("STROKE_WIDTH")
    val PEN_COLOR = longPreferencesKey("PEN_COLOR")
  }

}

