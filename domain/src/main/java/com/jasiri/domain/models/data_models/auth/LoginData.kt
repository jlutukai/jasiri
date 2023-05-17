package com.jasiri.domain.models.data_models.auth

data class LoginData(
    val refreshToken: String,
    val token: String,
    val user: UserData
)
