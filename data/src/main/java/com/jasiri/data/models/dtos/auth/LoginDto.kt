package com.jasiri.data.models.dtos.auth

data class LoginDto(
    val refreshToken: String,
    val token: String,
    val user: UserDto
)