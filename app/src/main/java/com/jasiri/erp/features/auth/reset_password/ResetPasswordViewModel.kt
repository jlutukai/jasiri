package com.jasiri.erp.features.auth.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.domain.models.reqests.auth.ResetPasswordRequest
import com.jasiri.domain.repositories.AuthRepository
import com.jasiri.domain.utils.ResultWrapper
import com.jasiri.erp.features.auth.forgot_password.models.ForgotPasswordUIState
import com.jasiri.erp.features.auth.reset_password.models.ResetPasswordUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _resetPasswordState: MutableStateFlow<ResetPasswordUIState>
            = MutableStateFlow(ResetPasswordUIState())
    val resetPasswordState: StateFlow<ResetPasswordUIState>
        get() = _resetPasswordState.asStateFlow()

    fun onForgotPassword(request: ResetPasswordRequest) {
        viewModelScope.launch {
            authRepository.onResetPassword(request).onStart {
                _resetPasswordState.update {
                    ResetPasswordUIState(isLoading = true)
                }
            }.collect { result ->
                when (result) {
                    is ResultWrapper.Loading -> {
                        _resetPasswordState.update {
                            ResetPasswordUIState(isLoading = true)
                        }
                    }

                    is ResultWrapper.GenericError -> {
                        _resetPasswordState.update {
                            ResetPasswordUIState(isLoading = false, errorMessage = result.errorMessage)
                        }
                    }

                    is ResultWrapper.Success -> {
                        _resetPasswordState.update {
                            ResetPasswordUIState(isLoading = false, userName = result.value)
                        }
                    }
                }
            }
        }
    }
}