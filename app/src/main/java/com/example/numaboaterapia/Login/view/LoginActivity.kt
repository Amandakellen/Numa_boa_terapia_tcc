package com.example.numaboaterapia.Login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.Login.ViewModel.LoginViewModel
import com.example.numaboaterapia.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private var viewModel = LoginViewModel()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var toastLoginIsNull: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        toastLoginIsNull = Toast.makeText(
            applicationContext, "Digite o seu login e senha para entrar no app", Toast.LENGTH_LONG
        )

        setUpEditText()
        setupViews()

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    }

    private fun setUpEditText() {
        binding.textInputEditEmail.doOnTextChanged { text, start, before, count ->
            viewModel.emailValue(text.toString())
        }

        binding.textInputEditSenha.doOnTextChanged { text, start, before, count ->
            viewModel.senhaValue(text.toString())
        }
    }

    private fun checkLoginIsEmpty(): Boolean =
        (viewModel.email.value?.isNullOrEmpty() == null ||
                viewModel.senha.value?.isNullOrEmpty() == null ||
                viewModel.email.value?.isNullOrEmpty() == true ||
                viewModel.senha.value?.isNullOrEmpty() == true)


    private fun setupViews() {

        binding.botaoLogin.setOnClickListener {
            when (checkLoginIsEmpty()) {
                true -> toastLoginIsNull.show()
                else -> {}
            }
        }

        binding.toolBarLogin.getBackButton().setOnClickListener {
            finish()
        }

    }
}