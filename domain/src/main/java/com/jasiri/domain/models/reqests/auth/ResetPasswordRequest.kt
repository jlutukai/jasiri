package com.jasiri.domain.models.reqests.auth

data class ResetPasswordRequest(
    val code: String,
    val newPassword: String
)