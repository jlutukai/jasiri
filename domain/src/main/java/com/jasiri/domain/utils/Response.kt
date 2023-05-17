package com.jasiri.domain.utils

data class Response<T>(
    val isSuccessful: Boolean,
    val data: T
)