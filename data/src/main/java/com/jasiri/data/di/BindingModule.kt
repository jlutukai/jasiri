package com.jasiri.data.di

import com.jasiri.data.repositories.AuthRepositoryImp
import com.jasiri.data.sources.local.LocalDataSource
import com.jasiri.data.sources.local.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.jasiri.data.sources.local.prefs_store.PrefsRepositoryImp
import com.jasiri.data.sources.remote.RemoteDataSource
import com.jasiri.data.sources.remote.RemoteDataSourceImpl
import com.jasiri.domain.repositories.AuthRepository
import com.jasiri.domain.repositories.PrefsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingModule {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(
        remoteDataSourceImpl: RemoteDataSourceImpl
    ): RemoteDataSource

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(
        localDataSourceImpl: LocalDataSourceImpl
    ): LocalDataSource

    @Singleton
    @Binds
    abstract fun bindPreferencesStoreManager(
        prefsRepositoryImpl: PrefsRepositoryImp
    ): PrefsRepository

    @Singleton
    @Binds
    abstract fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImp
    ): AuthRepository


}