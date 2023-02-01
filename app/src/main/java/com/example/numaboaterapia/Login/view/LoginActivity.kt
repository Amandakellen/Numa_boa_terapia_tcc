package com.example.numaboaterapia.Login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.Login.ViewModel.LoginViewModel
import com.example.numaboaterapia.data.RequestResult
import com.example.numaboaterapia.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private var viewModel = LoginViewModel()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var toastLoginIsNull: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)


        setUpFirebase()
        setUpEditText()
        setupViews()

        setContentView(binding.root)
    }

    fun setUpToast(toastMessage :String){
        toastLoginIsNull = Toast.makeText(
            applicationContext, toastMessage, Toast.LENGTH_LONG
        )
    }

    fun setUpFirebase() = viewModel.setAuhthentication(FirebaseAuth.getInstance())

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = viewModel.getAuthentication().currentUser
        if(currentUser != null){
            //todo
        }
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
                true -> {
                    setUpToast("Digite seu login e senha")
                    toastLoginIsNull.show()
                }
                else -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                    val loginResult = viewModel.verifyLogin()
                }
            }
        }

        binding.toolBarLogin.getBackButton().setOnClickListener {
            finish()
        }

    }
}