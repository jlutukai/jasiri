package com.jasiri.data.models.response.auth

import com.jasiri.data.models.dtos.auth.LoginDto
import com.jasiri.data.models.dtos.auth.UserDto

data class ResetPasswordResponse(
    val `data`: UserDto
)
