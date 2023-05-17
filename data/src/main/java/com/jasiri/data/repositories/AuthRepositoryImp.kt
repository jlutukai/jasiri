package com.jasiri.data.repositories

import android.content.SharedPreferences
import com.jasiri.data.mappers.auth.toDomain
import com.jasiri.data.sources.local.LocalDataSource
import com.jasiri.domain.repositories.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import com.jasiri.data.sources.remote.RemoteDataSource
import com.jasiri.data.utils.EncryptUtils
import com.jasiri.data.utils.flowSafeCall
import com.jasiri.domain.models.data_models.auth.LoginData
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.domain.models.reqests.auth.ResetPasswordRequest
import com.jasiri.domain.utils.Dispatcher
import com.jasiri.domain.utils.JasiriDispatchers
import com.jasiri.domain.utils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import re.iprocu.domain.utils.*
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val prefs: SharedPreferences,
    private val encryptUtils: EncryptUtils,
    @Dispatcher(JasiriDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {
    override suspend fun loginUser(loginRequest: LoginRequest): Flow<ResultWrapper<LoginData>> =
        flowSafeCall(ioDispatcher){
            remoteDataSource.loginUser(loginRequest).data.toDomain()
        }

    override suspend fun onForgotPassword(request: ForgotPasswordRequest): Flow<ResultWrapper<Int>>
    = flowSafeCall(ioDispatcher){
        remoteDataSource.requestForgotPasswordCode(request).data.userId
    }

    override suspend fun onResetPassword(request: ResetPasswordRequest): Flow<ResultWrapper<String>> = flowSafeCall(ioDispatcher){
        remoteDataSource.resetPassword(request).data.username
    }
}