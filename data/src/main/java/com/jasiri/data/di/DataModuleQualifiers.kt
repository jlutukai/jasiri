package com.jasiri.data.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ViewModelScopedUseCase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppScopedUseCase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppCoroutineScope