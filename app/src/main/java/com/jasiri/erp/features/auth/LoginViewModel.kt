package com.jasiri.erp.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.domain.repositories.AuthRepository
import com.jasiri.domain.utils.ResultWrapper
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {
    private val _loginState: MutableStateFlow<LoginUIState> = MutableStateFlow(LoginUIState())
    val loginState: StateFlow<LoginUIState> get() = _loginState.asStateFlow()

    fun onLogin(loginRequest: LoginRequest) {
        viewModelScope.launch {
            authRepository.loginUser(loginRequest).onStart {
                _loginState.update {
                    LoginUIState(isLoading = true)
                }
            }.collect { result ->
                when (result) {
                    is ResultWrapper.Loading -> {
                        _loginState.update {
                            LoginUIState(isLoading = true)
                        }
                    }

                    is ResultWrapper.GenericError -> {
                        _loginState.update {
                            LoginUIState(isLoading = false, errorMessage = result.errorMessage)
                        }
                    }

                    is ResultWrapper.Success -> {
                        _loginState.update {
                            LoginUIState(isLoading = false, session = result.value)
                        }
                    }
                }
            }
        }
    }
}