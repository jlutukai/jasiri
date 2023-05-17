package com.jasiri.erp.features.auth.reset_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jasiri.erp.R
import com.jasiri.erp.databinding.DialogForgetPasswordBinding
import com.jasiri.erp.databinding.DialogResetPasswordBinding
import com.jasiri.erp.features.auth.forgot_password.ForgotPasswordViewModel
import com.jasiri.erp.utils.dialogs.DialogManager
import com.jasiri.erp.utils.dialogs.DialogManagerImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPassword : BottomSheetDialogFragment() , DialogManager by DialogManagerImpl() {
    private var _binding: DialogResetPasswordBinding? = null
    private val binding: DialogResetPasswordBinding get() = _binding!!

    private val viewModel: ResetPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogResetPasswordBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initListeners()
    }

    private fun initListeners() {
        with(binding){
            btnReset.setOnClickListener {
                if (checkIfValid()){

                }
            }
        }
    }

    private fun checkIfValid(): Boolean {
        binding.apply {
            if (code.text.isNullOrEmpty()) {
                code.error = getString(R.string.required)
                code.requestFocus()
                return@apply
            }
            if (newPassword.text.isNullOrEmpty()) {
                newPassword.error = getString(R.string.required)
                newPassword.requestFocus()
                return@apply
            }
            if (confirmPassword.text.isNullOrEmpty()) {
                confirmPassword.error = getString(R.string.required)
                confirmPassword.requestFocus()
                return@apply
            }
            if (confirmPassword.text.toString() != newPassword.text.toString()) {
                confirmPassword.error = getString(R.string.required)
                newPassword.error = getString(R.string.required)
                confirmPassword.requestFocus()
                return@apply
            }

            return true
        }
        return false
    }


    override fun getTheme(): Int = R.style.BottomSheetTheme

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}