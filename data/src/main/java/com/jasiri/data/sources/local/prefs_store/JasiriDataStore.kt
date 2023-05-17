package com.jasiri.data.sources.local.prefs_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.jasiri.domain.utils.Dispatcher
import com.jasiri.domain.utils.JasiriDispatchers
import java.io.IOException
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton
import com.jasiri.data.UserToken
import com.jasiri.data.copy

@Singleton
class JasiriDataStore @Inject constructor(
    @ApplicationContext context: Context,
    private val dataStore: DataStore<Preferences>,
    private val userTokenPreferences: DataStore<UserToken>,
    @Dispatcher(JasiriDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) {
    fun getString(keyName: String): Flow<String?> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                preferences[stringPreferencesKey(keyName)]
            }

    suspend fun putString(keyName: String, value: String) {
        dataStore.edit {
            it[stringPreferencesKey(keyName)] = value
        }
    }

    fun getBoolean(keyName: String): Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                preferences[booleanPreferencesKey(keyName)] ?: false
            }

    suspend fun putBoolean(keyName: String, value: Boolean) {
        dataStore.edit {
            it[booleanPreferencesKey(keyName)] = value
        }
    }

    fun getInt(keyName: String): Flow<Int> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                }
            }
            .map { preferences ->
                preferences[intPreferencesKey(keyName)] ?: -1
            }

    suspend fun putInt(keyName: String, value: Int) {
        dataStore.edit {
            it[intPreferencesKey(keyName)] = value
        }
    }
    suspend fun saveUserToken(accessToken: String, expiresInSec: Int) {
        val tokenExpirationTime = Instant.now().epochSecond.toInt() + expiresInSec
        userTokenPreferences.updateData {
            it.copy {
                token = accessToken
                expiresIn = tokenExpirationTime
            }
        }
    }

    fun getUserToken(): Flow<UserToken> = userTokenPreferences.data

    suspend fun clearData() {
        CoroutineScope(ioDispatcher).launch {
            dataStore.edit {
                it.clear()
            }
        }
    }
}