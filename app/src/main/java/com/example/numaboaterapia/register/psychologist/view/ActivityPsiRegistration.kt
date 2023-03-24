package com.example.numaboaterapia.register.psychologist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiRegistrationBinding
import com.example.numaboaterapia.register.pacient.view.ActivityPatientConsultationReason
import com.example.numaboaterapia.register.psychologist.viewModel.PsiRegistrationViewModel

class ActivityPsiRegistration : AppCompatActivity() {
    private lateinit var binding : ActivityPsiRegistrationBinding
    private lateinit var viewModel : PsiRegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiRegistrationBinding.inflate(layoutInflater)
        viewModel = PsiRegistrationViewModel()

        setUpEditText()
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpEditText() {
        binding.textInputEditPsiRegisterEmail.doOnTextChanged { text, start, before, count ->
            viewModel.emailValue(text.toString())
        }

        binding.textInputEditPsiRegisterName.doOnTextChanged { text, start, before, count ->
            viewModel.nameValue(text.toString())
        }

        binding.textInputEditPsiRegisterPhone.doOnTextChanged { text, start, before, count ->
            viewModel.phoneValue(text.toString())
        }

        binding.textInputEditPsiRegisterWpp.doOnTextChanged { text, start, before, count ->
            viewModel.linkWppValue(text.toString())
        }

        binding.textInputEditPsiRegisterCrp.doOnTextChanged { text, start, before, count ->
            viewModel.crpValue(text.toString())
        }

        binding.textInputPsiRegisterPassword.doOnTextChanged { text, start, before, count ->
            viewModel.passValue(text.toString())
        }

        binding.textInputPsiRegisterConfirmPassword.doOnTextChanged { text, start, before, count ->
            viewModel.passConfirmValue(text.toString())
        }
    }

    private fun setUpToast(toastMessage: String) {
         Toast.makeText(
            applicationContext,
             toastMessage, Toast.LENGTH_LONG
        ).show()
    }

    private fun checkRegister(): Boolean {

        if (viewModel.checkIfIsNullOrEmpty()) {
            setUpToast("Digite os dados para cadastro!")
            return false
        } else if (viewModel.checkPassLength() == false) {
            setUpToast("A senha deve ter no mínimo 6 digitos, digite novamente!")
            return false
        } else if (viewModel.checkIfPassIsEqual() == false) {
            setUpToast("As senhas não correspondem, digite novamente!")
            return false
        } else {
            return true
        }


    }

    private fun setUpViews(){
        binding.psiRegisterButton.setText(R.string.registration_button)
        binding.toolBarPsiRegister.getBackButton().setOnClickListener {
            finish()
        }

        binding.labelButtonRegisterPsi.setOnClickListener {
            val result = checkRegister()
            if (result) {
                val message = viewModel.crateUser()
                message.invokeOnCompletion {
                    if (message.getCompleted() != "Sucesso") {
                        setUpToast(message.getCompleted())
                    } else {
                       //todo
                    }

                }

            }
        }
    }

}