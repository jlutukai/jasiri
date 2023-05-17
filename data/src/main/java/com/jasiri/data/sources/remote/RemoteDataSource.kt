package com.jasiri.data.sources.remote

import com.jasiri.data.models.response.auth.ForgotPasswordResponse
import com.jasiri.data.models.response.auth.LoginResponse
import com.jasiri.data.models.response.auth.ResetPasswordResponse
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.domain.models.reqests.auth.RefreshTokenRequest
import com.jasiri.domain.models.reqests.auth.ResetPasswordRequest

interface RemoteDataSource {
    suspend fun loginUser(loginRequest: LoginRequest): LoginResponse
    suspend fun requestForgotPasswordCode(request: ForgotPasswordRequest): ForgotPasswordResponse
    suspend fun resetPassword(request: ResetPasswordRequest): ResetPasswordResponse

    suspend fun refreshToken(request: RefreshTokenRequest): LoginResponse
    // login user

}