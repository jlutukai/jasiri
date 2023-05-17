package com.jasiri.erp.utils.dialogs

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.jasiri.erp.R
import com.jasiri.erp.databinding.DialogConfirmDeletionBinding
import timber.log.Timber

class DialogManagerImpl : DialogManager {
    private var errorDialog: Dialog? = null
    private var alertDialog: Dialog? = null
    private var loadingDialog: Dialog? = null
    private var isLoadingShown = false

    override fun Context.showErrorDialog(
        title: String?,
        desc: String,
        positiveButtonFunction: (() -> Unit)?
    ) { // function -- context(parent (reference))
        errorDialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.dialog_error)
            title?.let { t ->
                val titleTV = findViewById<TextView>(R.id.title_error_dialog)
                titleTV.text = t
            }
            val descTV = findViewById<TextView>(R.id.description_error_dialog)
            descTV.text = desc
            val dismiss = findViewById<MaterialButton>(R.id.dismiss_error_dialog)
            dismiss.setOnClickListener {
                positiveButtonFunction?.invoke()
                dismiss()
            }
            window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
            val parentActivity = this@showErrorDialog as? AppCompatActivity
            val isParentFinishing = parentActivity?.isFinishing == true ||
                parentActivity?.isDestroyed == true
            this.takeIf { !isParentFinishing }?.show()
        }
    }

    override fun Context.showAlertDialog(
        title: String?,
        desc: String?,
        layoutInflater: LayoutInflater,
        positiveButtonFunction: (() -> Unit)?,
        negativeButtonFunction: (() -> Unit)?,
    ) { // function -- context(parent (reference))
        if (isLoadingShown) return
        isLoadingShown = true
        alertDialog = Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            val dialogBinding = DialogConfirmDeletionBinding.inflate(layoutInflater)
            setContentView(dialogBinding.root)
            title?.let { t ->
                dialogBinding.title.text = t
            }

            desc?.let {
                dialogBinding.desc.text = it
            }
            dialogBinding.cancel.setOnClickListener {
                negativeButtonFunction?.invoke()
                dismiss()
            }
            dialogBinding.ok.setOnClickListener {

                positiveButtonFunction?.invoke()
                dismiss()
            }
            window?.apply {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            }
            val parentActivity = this@showAlertDialog as? AppCompatActivity
            val isParentFinishing = parentActivity?.isFinishing == true ||
                parentActivity?.isDestroyed == true

            this.takeIf { !isParentFinishing }?.show()
        }
    }

    override fun hideErrorDialog() {
        try {
            if (errorDialog != null) {
                errorDialog?.dismiss()
                errorDialog = null
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun hideAlertDialog() {
        try {
            if (alertDialog != null) {
                alertDialog?.dismiss()
                alertDialog = null
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun dismissAllDialogs() {
        try {
            if (isLoadingShown) {
                isLoadingShown = false
                if (errorDialog != null) {
                    errorDialog?.dismiss()
                    errorDialog = null
                }
                if (alertDialog != null) {
                    alertDialog?.dismiss()
                    alertDialog = null
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun Context.showLoading() {
        try {
            val parentActivity = this as? AppCompatActivity
            val isParentFinishing = parentActivity?.isFinishing == true ||
                parentActivity?.isDestroyed == true
            if (loadingDialog != null) {
                loadingDialog?.dismiss()
                loadingDialog = null
            }

            loadingDialog = Dialog(this)
            loadingDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            loadingDialog?.setCancelable(false)
            loadingDialog?.setContentView(R.layout.dialog_loading)
            loadingDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            loadingDialog?.window!!
                .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            loadingDialog.takeIf { !isParentFinishing }?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun hideLoader() {
        try {
            if (loadingDialog != null) {
                loadingDialog!!.dismiss()
                loadingDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}