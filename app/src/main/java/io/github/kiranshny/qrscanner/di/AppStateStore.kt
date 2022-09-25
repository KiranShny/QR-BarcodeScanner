package io.github.kiranshny.qrscanner.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

private const val AppStateDataStore = "app_state_storage"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = AppStateDataStore)

@Module
@InstallIn(SingletonComponent::class)
object UserModule {

    @Provides
    fun provideUserModule(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

}
