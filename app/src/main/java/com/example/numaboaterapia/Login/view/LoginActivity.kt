package com.example.numaboaterapia.Login.view

import android.R
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.numaboaterapia.Login.ViewModel.LoginViewModel
import com.example.numaboaterapia.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {

    private var viewModel = LoginViewModel()
    private lateinit var binding: ActivityLoginBinding
    private lateinit var toastLoginIsNull: Toast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setUpEditText()
        setupViews()

        setContentView(binding.root)
    }

    fun setUp() {
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


//    private fun checkError(){
//        if(viewModel.requestResult!=null){
//            setUpToast(viewModel.requestResult!!)
//            toastLoginIsNull.show()
//            binding.loginProgressBar.visibility = View.INVISIBLE
//        }
//    }

    private fun setupViews() {

        binding.botaoLogin.setOnClickListener {
            when (checkLoginIsEmpty()) {
                true -> {
                    setUpToast("Digite seu login e senha")
                    toastLoginIsNull.show()
                }
                else -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                    viewModel.verifyLogin()
                    //checkError()
                }
            }
        }

        binding.toolBarLogin.getBackButton().setOnClickListener {
            finish()
        }

    }
}