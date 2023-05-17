package com.jasiri.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import com.jasiri.domain.utils.Dispatcher
import com.jasiri.domain.utils.JasiriDispatchers.IO

@Module
@InstallIn(SingletonComponent::class)
object CoroutineScopeModule {
    @AppCoroutineScope
    @Provides
    fun provideApplicationLevelCoroutineScope(
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(ioDispatcher + SupervisorJob())
}