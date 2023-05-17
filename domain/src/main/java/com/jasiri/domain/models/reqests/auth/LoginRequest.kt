package com.jasiri.domain.models.reqests.auth

data class LoginRequest(
    val password: String,
    val username: String
)