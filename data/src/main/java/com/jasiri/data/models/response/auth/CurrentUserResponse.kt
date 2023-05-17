package com.jasiri.data.models.response.auth

import com.jasiri.data.models.dtos.auth.CurrentUserDto

data class CurrentUserResponse(
    val `data`: CurrentUserDto
)