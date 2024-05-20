package io.zhiller.esp.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.zhiller.esp.data.storage.PreferenceRepo
import io.zhiller.esp.data.storage.PreferenceRepoImpl
import javax.inject.Singleton

val Context.layoutDataStore: DataStore<Preferences> by preferencesDataStore(
  name = "LAYOUT_SETTING"
)

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
  @Binds
  @Singleton
  abstract fun bindPreferenceRepo(
    preferenceRepo: PreferenceRepoImpl
  ): PreferenceRepo

  companion object {
    @Provides
    @Singleton
    fun provideLayoutDataStore(
      @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
      return applicationContext.layoutDataStore
    }
  }
}