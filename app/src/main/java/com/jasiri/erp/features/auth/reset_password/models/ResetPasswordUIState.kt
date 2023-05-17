package com.jasiri.erp.features.auth.reset_password.models

import com.jasiri.domain.utils.UiText

data class ResetPasswordUIState(
    val userName: String? = null,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)
