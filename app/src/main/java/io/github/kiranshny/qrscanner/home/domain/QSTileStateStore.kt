package io.github.kiranshny.qrscanner.home.domain

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.github.kiranshny.qrscanner.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QSTileStateStore @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {
    private object PreferenceConstant {
        const val qsTileToken = "qs_tile_set"
    }

    private object PreferenceKeys {
        val qsTileToken = stringPreferencesKey(PreferenceConstant.qsTileToken)
    }

    suspend fun setQSSileState(state: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.qsTileToken] = state.toString()
        }
    }

    suspend fun getQSTileState(): Boolean? {
        return withContext(dispatcher) {
            return@withContext dataStore.data.firstOrNull()
                ?.get(PreferenceKeys.qsTileToken)?.toBooleanStrictOrNull()
        }
    }

    fun qsTileToken(): Flow<Boolean?> {
        return dataStore.data.map { preferences ->
            preferences[PreferenceKeys.qsTileToken]?.toBooleanStrictOrNull()
        }
    }

    suspend fun clearToken() {
        dataStore.edit {
            it.remove(PreferenceKeys.qsTileToken)
        }
    }
}
