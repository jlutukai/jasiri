package com.jasiri.data.sources.remote

import com.jasiri.data.models.response.auth.ForgotPasswordResponse
import com.jasiri.data.models.response.auth.LoginResponse
import com.jasiri.data.models.response.auth.ResetPasswordResponse
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.domain.models.reqests.auth.RefreshTokenRequest
import com.jasiri.domain.models.reqests.auth.ResetPasswordRequest
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: JasiriApiService
) : RemoteDataSource {
    override suspend fun loginUser(loginRequest: LoginRequest): LoginResponse = apiService.loginUser(loginRequest)
    override suspend fun requestForgotPasswordCode(request: ForgotPasswordRequest): ForgotPasswordResponse =  apiService.requestForgotPasswordCode(request)
    override suspend fun resetPassword(request: ResetPasswordRequest): ResetPasswordResponse = apiService.resetPassword(request)
    override suspend fun refreshToken(request: RefreshTokenRequest): LoginResponse = apiService.refreshToken(request)
}