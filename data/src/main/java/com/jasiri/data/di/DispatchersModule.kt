package com.jasiri.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.jasiri.domain.utils.Dispatcher
import com.jasiri.domain.utils.JasiriDispatchers.*

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(MAIN)
    fun providesMAINDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Dispatcher(DEFAULT)
    fun providesDEFAULTDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Dispatcher(UNCONFINED)
    fun providesUNCONFINEDDispatcher(): CoroutineDispatcher = Dispatchers.Unconfined
}