package com.example.numaboaterapia.Login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.numaboaterapia.Login.ViewModel.LoginViewModel
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var viewModel = LoginViewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setupViews()

        setContentView(binding.root)
    }

    private fun setUpChanges(){
        //TODO
    }

    private fun setupViews(){
        val email = binding.textInputEditEmail.text
        val senha = binding.textInputEditSenha.text

        binding.botaoLogin.setOnClickListener {
            viewModel.emailValue(email.toString())
            viewModel.senhaValue(senha.toString())
        }

        binding.toolBarLogin.getBackButton().setOnClickListener {
            finish()
        }

    }
}