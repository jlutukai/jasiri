package com.jasiri.domain.utils

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(
        val code: Int? = null,
        val errorResponse: ErrorResponse? = null,
        val errorMessage: UiText
    ) : ResultWrapper<Nothing>()
    object Loading : ResultWrapper<Nothing>()
}