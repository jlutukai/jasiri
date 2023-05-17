package com.jasiri.erp.utils.dialogs

import android.content.Context
import android.view.LayoutInflater

interface DialogManager {
    fun Context.showErrorDialog(
        title: String? = null,
        desc: String,
        positiveButtonFunction: (() -> Unit)? = null
    )
    fun Context.showAlertDialog(
        title: String? = null,
        desc: String? = null,
        layoutInflater: LayoutInflater,
        positiveButtonFunction: (() -> Unit)? = null,
        negativeButtonFunction: (() -> Unit)? = null,
    )

    fun Context.showLoading()

    fun hideLoader()
    fun hideErrorDialog()
    fun hideAlertDialog()

    fun dismissAllDialogs()
}