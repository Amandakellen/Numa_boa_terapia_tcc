package com.example.numaboaterapia.register.paciente.viewModel

import androidx.lifecycle.*
import com.example.numaboaterapia.register.paciente.data.PatientData
import com.example.numaboaterapia.register.paciente.data.repository.PatientRegistrationRepository

class PatientRegistrationViewModel() :
    ViewModel() {

    private val repository: PatientRegistrationRepository = PatientRegistrationRepository()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _cpf = MutableLiveData<String>()
    val cpf: LiveData<String> = _cpf

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private val _confirmPass = MutableLiveData<String>()
    val confirmPass: LiveData<String> = _confirmPass


    fun nameValue(name: String) {
        _name.value = name
    }

    fun emailValue(email: String) {
        _email.value = email
    }

    fun phoneValue(phone: String) {
        _phone.value = phone
    }

    fun cpfValue(cpf: String) {
        _cpf.value = cpf
    }

    fun passValue(pass: String) {
        _pass.value = pass
    }

    fun passConfirmValue(senha: String) {
        _confirmPass.value = senha
    }

    fun isPassEqual(): Boolean = _pass.value.equals(_confirmPass.value)

    fun isValidPassword(): Boolean = if (_pass.value!!.length == 6) true else false




}

