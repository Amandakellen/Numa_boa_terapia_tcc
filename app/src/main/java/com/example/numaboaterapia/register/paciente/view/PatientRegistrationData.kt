package com.example.numaboaterapia.register.paciente.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPatientRegistrationDataBinding

class PatientRegistrationData : AppCompatActivity() {

    private lateinit var binding : ActivityPatientRegistrationDataBinding
    private lateinit var toastRegistration: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityPatientRegistrationDataBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    private fun setUpEditText() {
//        binding.textInputEditEmail.doOnTextChanged { text, start, before, count ->
//            viewModel.emailValue(text.toString())
//        }
//
//        binding.textInputEditSenha.doOnTextChanged { text, start, before, count ->
//            viewModel.senhaValue(text.toString())
//        }
    }


    fun setUpToast(toastMessage: String) {
        toastRegistration= Toast.makeText(
            applicationContext, toastMessage, Toast.LENGTH_LONG
        )
    }
}