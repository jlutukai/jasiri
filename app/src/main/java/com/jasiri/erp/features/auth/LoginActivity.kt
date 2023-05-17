package com.jasiri.erp.features.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jasiri.erp.databinding.ActivityLoginBinding
import com.jasiri.erp.utils.dialogs.DialogManager
import com.jasiri.erp.utils.dialogs.DialogManagerImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), DialogManager by DialogManagerImpl() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
    }

    private fun setUpUI() {
        observeViewState()
    }

    private fun observeViewState() {

    }
}