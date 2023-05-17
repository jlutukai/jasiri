package com.jasiri.data.sources.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jasiri.data.models.entities.CurrentUserEntity
import com.jasiri.data.sources.local.converters.CountryTypeConverter
import com.jasiri.data.sources.local.converters.SchoolTypeConverter
import com.jasiri.data.sources.local.daos.AuthDao


@Database(
    entities = [CurrentUserEntity::class],
    version = 1,
    exportSchema = true
)

@TypeConverters(
    CountryTypeConverter::class,
    SchoolTypeConverter::class
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao(): AuthDao
}