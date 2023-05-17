package com.jasiri.erp.utils.uiText

import android.content.Context
import com.jasiri.domain.utils.UiText

fun UiText.asString(context: Context): String {
    return when (this) {
        is UiText.DynamicString -> value
        is UiText.StringResource -> context.getString(id, args)
    }
}