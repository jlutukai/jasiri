package com.jasiri.data.sources.remote

import com.jasiri.data.models.response.auth.ForgotPasswordResponse
import com.jasiri.data.models.response.auth.LoginResponse
import com.jasiri.data.models.response.auth.ResetPasswordResponse
import com.jasiri.data.sources.remote.utils.ApiParameters
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.domain.models.reqests.auth.RefreshTokenRequest
import com.jasiri.domain.models.reqests.auth.ResetPasswordRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface JasiriApiService {
    @Headers(ApiParameters.NO_AUTH_HEADER)
    @POST("/api/v1/identity/auth/login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @Headers(ApiParameters.NO_AUTH_HEADER)
    @POST("/api/v1/identity/auth/password/forgot")
    suspend fun requestForgotPasswordCode(@Body request: ForgotPasswordRequest): ForgotPasswordResponse

    @Headers(ApiParameters.NO_AUTH_HEADER)
    @POST("/api/v1/identity/auth/password/reset")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): ResetPasswordResponse

    @Headers(ApiParameters.NO_AUTH_HEADER)
    @POST("/api/v1/identity/auth/refresh")
    suspend fun refreshToken(@Body request: RefreshTokenRequest): LoginResponse
}