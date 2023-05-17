package com.jasiri.data.sources.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jasiri.data.models.entities.CurrentUserEntity
import com.jasiri.data.models.entities.CurrentUserEntity.Companion.CURRENT_USER_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthDao {

    @Upsert
    suspend fun upsertCurrentUser(userEntity: CurrentUserEntity)

    @Delete
    suspend fun deleteCurrentUser(userEntity: CurrentUserEntity)

    @Query("SELECT * FROM $CURRENT_USER_TABLE")
    suspend fun getCurrentUser(): CurrentUserEntity?

    @Query("SELECT * FROM $CURRENT_USER_TABLE")
    suspend fun getCurrentUserFlow(): Flow<CurrentUserEntity?>
}