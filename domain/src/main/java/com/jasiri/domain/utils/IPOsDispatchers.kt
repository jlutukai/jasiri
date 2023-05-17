package com.jasiri.domain.utils

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class Dispatcher(val jasiriDispatchers: JasiriDispatchers)

enum class JasiriDispatchers {
    IO, MAIN, DEFAULT, UNCONFINED
}