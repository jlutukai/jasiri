package com.jasiri.domain.utils

sealed class JasiriLocalResult<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : JasiriLocalResult<T>()
    data class Error(val message: String?, val uiTextError: UiText? = null) : JasiriLocalResult<Nothing>()
    object Loading : JasiriLocalResult<Nothing>()
}