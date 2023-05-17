package com.jasiri.erp.features.auth.forgot_password.models

import com.jasiri.domain.models.data_models.auth.LoginData
import com.jasiri.domain.utils.UiText

data class ForgotPasswordUIState (
    val userId: Int? = null,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
        )