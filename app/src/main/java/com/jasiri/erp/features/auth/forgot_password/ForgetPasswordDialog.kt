package com.jasiri.erp.features.auth.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jasiri.domain.models.reqests.auth.ForgotPasswordRequest
import com.jasiri.erp.R
import com.jasiri.erp.databinding.DialogForgetPasswordBinding
import com.jasiri.erp.features.auth.LoginViewModel
import com.jasiri.erp.features.auth.forgot_password.models.ForgotPasswordUIState
import com.jasiri.erp.utils.dialogs.DialogManager
import com.jasiri.erp.utils.dialogs.DialogManagerImpl
import com.jasiri.erp.utils.flows.collectIn
import com.jasiri.erp.utils.uiText.asString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgetPasswordDialog : BottomSheetDialogFragment() , DialogManager by DialogManagerImpl() {
    private var _binding: DialogForgetPasswordBinding? = null
    private val binding: DialogForgetPasswordBinding get() = _binding!!
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogForgetPasswordBinding.inflate(
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
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.forgotPasswordState.collectIn(viewLifecycleOwner){
            handleForgotPasswordState(state= it)
        }
    }

    private fun handleForgotPasswordState(state: ForgotPasswordUIState) {
        if (state.isLoading){
            requireContext().showLoading()
        }else{
            hideLoader()
        }

        state.errorMessage?.let {
            requireContext().showErrorDialog(
                desc = it.asString(requireContext())
            )
        }

        state.userId?.let {
            findNavController().navigate(
                ForgetPasswordDialogDirections.
                actionForgetPasswordDialogToResetPassword()
            )
        }
    }

    private fun initListeners() {
        with(binding) {
            btnRequestLink.setOnClickListener {
                if (checkIfValid()) {
                    viewModel.onForgotPassword(ForgotPasswordRequest(
                        username = binding.userName.text.toString().trim()
                    ))

                }
            }
        }
    }

    private fun checkIfValid(): Boolean {
        binding.apply {
            if (userName.text.isNullOrEmpty()) {
                userName.error = getString(R.string.required)
                userName.requestFocus()
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