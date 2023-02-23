package com.example.numaboaterapia.register.paciente.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.numaboaterapia.register.paciente.data.repository.PatientRegistrationRepository
import kotlinx.coroutines.launch

class PatientRegistrationViewModel(val application: Application) :
    ViewModel() {

    private val patientRegistrationRepository: PatientRegistrationRepository =
        PatientRegistrationRepository()


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

    fun checkIfIsNullOrEmpty(): Boolean =
        (_pass.value?.isNullOrEmpty() == null ||
                _pass.value?.isNullOrEmpty() == true ||
                _confirmPass.value?.isNullOrEmpty() == null ||
                _confirmPass.value?.isNullOrEmpty() == true ||
                _name.value?.isNullOrEmpty() == null ||
                _name.value?.isNullOrEmpty() == true ||
                _phone.value?.isNullOrEmpty() == null ||
                _phone.value?.isNullOrEmpty() == true ||
                _cpf.value?.isNullOrEmpty() == null ||
                _cpf.value?.isNullOrEmpty() == true)

    fun checkPassLength(): Boolean {
        return _pass.value!!.length == 6
    }

    fun checkIfPassIsEqual(): Boolean = _pass.value.equals(_confirmPass.value)

    private fun hashMapData(): HashMap<String, String> {
         return hashMapOf(
            "name" to _name.value.toString(),
            "email" to _email.value.toString(),
            "phone" to _phone.value.toString(),
            "cpf" to _cpf.value.toString()
        )

    }

    fun crateUser() {

        viewModelScope.launch {
            patientRegistrationRepository.createUser(
                application,
                _email.value.toString(),
                _pass.value.toString(),
                hashMapData()
            )

        }

    }


}

