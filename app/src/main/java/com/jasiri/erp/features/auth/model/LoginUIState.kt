package com.jasiri.erp.features.auth.model

import com.jasiri.domain.models.data_models.auth.LoginData
import com.jasiri.domain.utils.UiText

data class LoginUIState(
    val session: LoginData? = null,
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null
)
