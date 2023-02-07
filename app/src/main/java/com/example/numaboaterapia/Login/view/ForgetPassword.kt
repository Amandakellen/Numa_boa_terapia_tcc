package com.example.numaboaterapia.Login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.Login.ViewModel.ForgetPasswordViewModel
import com.example.numaboaterapia.databinding.ActivityForgetPasswordBinding

class ForgetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding
    private lateinit var viewModel: ForgetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        viewModel = ForgetPasswordViewModel(this.application)

        setUpEditText()
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpEditText() {
        binding.textInputEditEmailForgetPassword.doOnTextChanged { text, start, before, count ->
            viewModel.emailValue(text.toString())
        }

    }

    private fun checkEmailIsEmpty(): Boolean =
        (viewModel.email.value?.isNullOrEmpty() == null ||
                viewModel.email.value?.isNullOrEmpty() == true)

    private fun setUpViews() {
        binding.forgetPasswordButton.setOnClickListener {
            when (checkEmailIsEmpty()) {
                true -> {
                    Toast.makeText(application, "Digite o seu email", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    viewModel.verifyPassword()
                }
            }
        }
        binding.toolBarForgetPassword.getBackButton().setOnClickListener {
            finish()
        }
    }
}