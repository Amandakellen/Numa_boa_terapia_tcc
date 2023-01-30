package com.example.numaboaterapia.Login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.Login.ViewModel.LoginViewModel
import com.example.numaboaterapia.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private var viewModel = LoginViewModel()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setUpEditText()
        setupViews()

        setContentView(binding.root)
    }

    private fun setUpEditText() {
       binding.textInputEditEmail.doOnTextChanged { text, start, before, count ->
           viewModel.emailValue(text.toString())
       }

       binding.textInputEditSenha.doOnTextChanged { text, start, before, count ->
           viewModel.senhaValue(text.toString())
       }
    }

    private fun setupViews() {

//        binding.botaoLogin.setOnClickListener {
//
//        }

        binding.toolBarLogin.getBackButton().setOnClickListener {
            finish()
        }

    }
}