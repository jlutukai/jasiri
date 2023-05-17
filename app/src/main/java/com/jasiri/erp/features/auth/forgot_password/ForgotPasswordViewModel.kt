package com.jasiri.erp.features.auth.forgot_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.domain.repositories.AuthRepository
import com.jasiri.domain.utils.ResultWrapper
import com.jasiri.erp.features.auth.forgot_password.models.ForgotPasswordUIState
import com.jasiri.erp.features.auth.model.LoginUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _forgotPasswordState: MutableStateFlow<ForgotPasswordUIState>
    = MutableStateFlow(ForgotPasswordUIState())
    val forgotPasswordState: StateFlow<ForgotPasswordUIState>
        get() = _forgotPasswordState.asStateFlow()

    fun onForgotPassword(request: ForgotPasswordRequest) {
        viewModelScope.launch {
            authRepository.onForgotPassword(request).onStart {
                _forgotPasswordState.update {
                    ForgotPasswordUIState(isLoading = true)
                }
            }.collect { result ->
                when (result) {
                    is ResultWrapper.Loading -> {
                        _forgotPasswordState.update {
                            ForgotPasswordUIState(isLoading = true)
                        }
                    }

                    is ResultWrapper.GenericError -> {
                        _forgotPasswordState.update {
                            ForgotPasswordUIState(isLoading = false, errorMessage = result.errorMessage)
                        }
                    }

                    is ResultWrapper.Success -> {
                        _forgotPasswordState.update {
                            ForgotPasswordUIState(isLoading = false, userId = result.value)
                        }
                    }
                }
            }
        }
    }
}