package com.example.numaboaterapia.register.psychologist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.repository.UserFirebaseRegistrationRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PsiRegistrationViewModel: ViewModel() {
    private val repository: UserFirebaseRegistrationRepository =
        UserFirebaseRegistrationRepository()

    private val _email = MutableLiveData<String>()

    private val _name = MutableLiveData<String>()

    private val _phone = MutableLiveData<String>()

    private val _wppLink = MutableLiveData<String>()

    private val _crp = MutableLiveData<String>()

    private val _pass = MutableLiveData<String>()

    private val _confirmPass = MutableLiveData<String>()

    private val _especializacao = MutableLiveData<String>()

    private val _time = MutableLiveData<String>()

    fun especializacaoValue(especializacao: String){
        _especializacao.value = especializacao
    }

    fun timeValue(time : String){
        _time.value = time
    }

    fun nameValue(name: String) {
        _name.value = name
    }

    fun emailValue(email: String) {
        _email.value = email
    }

    fun phoneValue(phone: String) {
        _phone.value = phone
    }

    fun linkWppValue(link : String){
        _wppLink.value = link
    }

    fun crpValue(cpf: String) {
        _crp.value = cpf
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
                _crp.value?.isNullOrEmpty() == null ||
                _crp.value?.isNullOrEmpty() == true||
                _wppLink.value?.isNullOrEmpty() == null ||
                _wppLink.value?.isNullOrEmpty() == true||
                _especializacao.value?.isNullOrEmpty() == null||
                _especializacao.value?.isNullOrEmpty() == true||
                _time.value?.isNullOrEmpty() == null ||
                _time.value?.isNullOrEmpty() == true)

    fun checkPassLength(): Boolean {
        return _pass.value!!.length == 6
    }

    fun checkIfPassIsEqual(): Boolean = _pass.value.equals(_confirmPass.value)

    private fun hashMapData(): HashMap<String, String> {
        return hashMapOf(
            "psi_name" to _name.value.toString(),
            "psi_email" to _email.value.toString(),
            "psi_phone" to _phone.value.toString(),
            "psi_linkwpp" to _wppLink.value.toString(),
            "psi_crp" to _crp.value.toString(),
            "_psi_especializacao" to _especializacao.value.toString(),
            "psi_time" to _time.value.toString()
        )

    }

    fun crateUser(): Deferred<String> {
        val result = viewModelScope.async {
            repository.createUser(
                _email.value.toString(),
                _pass.value.toString(),
                hashMapData(),
                "psi_users"
            )
        }
        return result


    }

}