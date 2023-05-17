package com.jasiri.data.di

import android.content.Context
import android.content.SharedPreferences
import com.jasiri.data.sources.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.jasiri.data.sources.remote.interceptors.AuthInterceptor
import com.jasiri.data.utils.DataConstants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModules {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(DataConstants.PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideAuthInterceptor(prefs: SharedPreferences): AuthInterceptor =
        AuthInterceptor(prefs)


    @Provides
    @Singleton
    fun providesProductDao(appDatabase: AppDatabase) =
        appDatabase.authDao()


}