package com.jasiri.erp.features.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jasiri.domain.models.reqests.auth.LoginRequest
import com.jasiri.erp.R
import com.jasiri.erp.databinding.FragmentLoginBinding
import com.jasiri.erp.features.auth.LoginViewModel
import com.jasiri.erp.features.auth.model.LoginUIState
import com.jasiri.erp.features.main.MainActivity
import com.jasiri.erp.utils.dialogs.DialogManager
import com.jasiri.erp.utils.dialogs.DialogManagerImpl
import com.jasiri.erp.utils.flows.collectIn
import com.jasiri.erp.utils.uiText.asString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login), DialogManager by DialogManagerImpl() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLoginBinding.bind(view)
        setUpUI()
    }

    private fun setUpUI() {
        initListeners()
        observeViewState()
    }

    private fun observeViewState() {
        viewModel.loginState.collectIn(this) {
            handleLoginState(state = it)
        }
    }

    private fun handleLoginState(state: LoginUIState) {
        if (state.isLoading) {
            requireContext().showLoading()
        } else {
            hideLoader()
        }

        state.errorMessage?.let {
            requireContext().showErrorDialog(
                desc = it.asString(requireContext())
            )
        }

        state.session?.let {
            if (it.token.isNotEmpty()) {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            forgotPassword.setOnClickListener {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgetPasswordDialog())
            }
            btnLogin.setOnClickListener {
                if (checkIfValid()) {
                    viewModel.onLogin(
                        LoginRequest(
                            password = binding.password.text.toString().trim(),
                            username = binding.userName.text.toString().trim()
                        )
                    )
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
            if (password.text.isNullOrEmpty()) {
                password.error = getString(R.string.required)
                password.requestFocus()
                return@apply
            }
            return true
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}