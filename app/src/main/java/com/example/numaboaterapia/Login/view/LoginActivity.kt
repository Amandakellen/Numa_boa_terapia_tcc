package com.example.numaboaterapia.Login.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.numaboaterapia.Login.ViewModel.LoginViewModel
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.views.PacientApp
import com.example.numaboaterapia.appNavigation.psychologist.views.PsiHome
import com.example.numaboaterapia.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var toastLoginIsNull: Toast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        viewModel = LoginViewModel()

        setUpEditText()
        setupViews()

        setContentView(binding.root)
    }


    private fun setUp() {
        viewModel.userData!!.observe(this, object : Observer<FirebaseUser?> {
            override fun onChanged(firebaseUser: FirebaseUser?) {
                if (firebaseUser != null) {
                    //navController.navigate(R.id.action_signInFragment_to_signOutFragment)
                }
            }
        })

    }

    fun setUpToast(toastMessage: String) {
        toastLoginIsNull = Toast.makeText(
            applicationContext, toastMessage, Toast.LENGTH_LONG
        )
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
                viewModel.pass.value?.isNullOrEmpty() == null ||
                viewModel.email.value?.isNullOrEmpty() == true ||
                viewModel.pass.value?.isNullOrEmpty() == true)


    private fun setupViews() {
        binding.botaoLogin.setText(R.string.button_login)
        binding.botaoLogin.setOnClickListener {
            when (checkLoginIsEmpty()) {
                true -> {
                    setUpToast("Digite seu login e senha")
                    toastLoginIsNull.show()
                }

                else -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                    lifecycleScope.launch {
                        viewModel.verifyLogin().await().collect {
                            when (it) {
                                true -> {
                                    CoroutineScope(Dispatchers.IO).launch {
                                        val result = viewModel.verifyIfIsPacient()
                                        withContext(Dispatchers.Main) {
                                            if (result) {
                                                startActivity(
                                                    Intent(
                                                        applicationContext,
                                                        PacientApp::class.java
                                                    )
                                                )

                                            }else{
                                                CoroutineScope(Dispatchers.IO).launch {
                                                    val isPsi = viewModel.verifyIfIsPsi()
                                                    withContext(Dispatchers.Main) {
                                                        if(isPsi){
                                                            startActivity(
                                                                Intent(
                                                                    applicationContext,
                                                                    PsiHome::class.java
                                                                )
                                                            )
                                                        }else{
                                                            Toast.makeText(
                                                                applicationContext,
                                                                "Usuário não encontrado",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                else -> {
                                    Toast.makeText(
                                        applicationContext,
                                        viewModel.getErrorMessage(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }

                    binding.loginProgressBar.visibility = View.INVISIBLE
                }
            }
        }

        binding.toolBarLogin.getBackButton().setOnClickListener {
            finish()
        }

        binding.forgetPassword.setOnClickListener {
            val intent = Intent(this, ForgetPassword::class.java)
            startActivity(intent)
        }

    }
}