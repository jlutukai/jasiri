package com.jasiri.domain.repositories

import com.jasiri.domain.models.data_models.auth.LoginData
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.domain.models.reqests.auth.ResetPasswordRequest
import com.jasiri.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun loginUser(loginRequest: LoginRequest) : Flow<ResultWrapper<LoginData>>
    suspend fun onForgotPassword(request: ForgotPasswordRequest): Flow<ResultWrapper<Int>>
    suspend fun onResetPassword(request: ResetPasswordRequest): Flow<ResultWrapper<String>>
}