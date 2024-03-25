package com.zhiller.pureboard.data.preference

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
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
  name = "USER_SETTING"
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
    fun provideUserDataStore(
      @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
      return applicationContext.userDataStore
    }
  }
}