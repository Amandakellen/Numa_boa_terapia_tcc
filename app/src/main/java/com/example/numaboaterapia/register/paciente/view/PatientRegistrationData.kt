package com.example.numaboaterapia.register.paciente.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPatientRegistrationDataBinding
import com.example.numaboaterapia.register.paciente.viewModel.PatientRegistrationViewModel

class PatientRegistrationData : AppCompatActivity() {

    private lateinit var binding: ActivityPatientRegistrationDataBinding
    private lateinit var viewModel: PatientRegistrationViewModel
    private lateinit var toastRegistration: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientRegistrationDataBinding.inflate(layoutInflater)

        viewModel = PatientRegistrationViewModel(this.application)
        setUpEditText()
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpEditText() {
        binding.textInputEditRegisterEmail.doOnTextChanged { text, start, before, count ->
            viewModel.emailValue(text.toString())
        }

        binding.textInputEditRegisterName.doOnTextChanged { text, start, before, count ->
            viewModel.nameValue(text.toString())
        }

        binding.textInputEditRegisterPhone.doOnTextChanged { text, start, before, count ->
            viewModel.phoneValue(text.toString())
        }

        binding.textInputEditRegisterCpf.doOnTextChanged { text, start, before, count ->
            viewModel.cpfValue(text.toString())
        }

        binding.textInputPacientRegisterPassword.doOnTextChanged { text, start, before, count ->
            viewModel.passValue(text.toString())
        }

        binding.textInputPacientRegisterConfirmPassword.doOnTextChanged { text, start, before, count ->
            viewModel.passConfirmValue(text.toString())
        }
    }


    private fun setUpToast(toastMessage: String) {
        toastRegistration = Toast.makeText(
            applicationContext, toastMessage, Toast.LENGTH_LONG
        )
        toastRegistration.show()
    }

    private fun checkRegister() : Boolean{

        if(viewModel.checkIfIsNullOrEmpty()){
            setUpToast("Digite os dados para cadastro!")
            return false
        }else if (viewModel.checkPassLength() == false){
            setUpToast("A senha deve ter no mínimo 6 digitos, digite novamente!")
            return false
        }else if(viewModel.checkIfPassIsEqual()== false){
            setUpToast("As senhas não correspondem, digite novamente!")
            return false
        }else{
            return true
        }


    }


    private fun setUpViews() {

        binding.labelButtonLogin.setOnClickListener {
            val result = checkRegister()
            if (result){
                viewModel.crateUser()
            }
        }

        binding.toolBarPacientRegister.getBackButton().setOnClickListener {
            finish()
        }

    }
}