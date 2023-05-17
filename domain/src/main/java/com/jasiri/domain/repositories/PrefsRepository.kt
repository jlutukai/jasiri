package com.jasiri.domain.repositories

import kotlinx.coroutines.flow.Flow

interface PrefsRepository {
    fun getString(keyName: String): Flow<String?>

    suspend fun putString(keyName: String, value: String)

    fun getBoolean(keyName: String): Flow<Boolean>

    suspend fun putBoolean(keyName: String, value: Boolean)

    fun getInt(keyName: String): Flow<Int>

    suspend fun putInt(keyName: String, value: Int)

    suspend fun clearData()
}