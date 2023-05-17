package com.jasiri.data.sources.local.prefs_store

import com.jasiri.domain.repositories.PrefsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PrefsRepositoryImp @Inject constructor(
    private val dataStoreManager: JasiriDataStore
) : PrefsRepository {
    override fun getString(keyName: String): Flow<String?> =
        dataStoreManager.getString(keyName)

    override suspend fun putString(keyName: String, value: String) =
        dataStoreManager.putString(keyName, value)

    override fun getBoolean(keyName: String): Flow<Boolean> =
        dataStoreManager.getBoolean(keyName)

    override suspend fun putBoolean(keyName: String, value: Boolean) =
        dataStoreManager.putBoolean(keyName, value)

    override fun getInt(keyName: String): Flow<Int> =
        dataStoreManager.getInt(keyName)

    override suspend fun putInt(keyName: String, value: Int) =
        dataStoreManager.putInt(keyName, value)

    override suspend fun clearData() = dataStoreManager.clearData()
}
