package com.example.numaboaterapia.register.paciente.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.numaboaterapia.register.paciente.data.repository.PatientRegistrationRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PatientRegistrationViewModel() :
    ViewModel() {

    private val patientRegistrationRepository: PatientRegistrationRepository =
        PatientRegistrationRepository()

    private val _email = MutableLiveData<String>()

    private val _name = MutableLiveData<String>()

    private val _phone = MutableLiveData<String>()

    private val _cpf = MutableLiveData<String>()

    private val _pass = MutableLiveData<String>()

    private val _confirmPass = MutableLiveData<String>()

    lateinit var message: String


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
            "pu_name" to _name.value.toString(),
            "pu_email" to _email.value.toString(),
            "pu_phone" to _phone.value.toString(),
            "pu_cpf" to _cpf.value.toString()
        )

    }

    fun crateUser(): Deferred<String> {
        val result = viewModelScope.async {
            patientRegistrationRepository.createUser(
                _email.value.toString(),
                _pass.value.toString(),
                hashMapData()
            )
        }
        return result

//        result.invokeOnCompletion {
//            message = result.getCompleted()
//            return@invokeOnCompletion result.getCompleted()
//        }


    }


}

