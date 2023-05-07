package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.EditMyDataRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class EditDataViewModel : ViewModel() {

    private val repository = EditMyDataRepository()

    private val _email = MutableLiveData<String>()

    private val _name = MutableLiveData<String>()

    private val _phone = MutableLiveData<String>()

    private val _wppLink = MutableLiveData<String>()

    private val _especializacao = MutableLiveData<String>()

    private val _time = MutableLiveData<String>()

    private val _crp = MutableLiveData<String>()

    private val _register = MutableLiveData<ArrayList<String>>()

    fun crpValue(crp: String){
        _crp.value = crp
    }
    fun registerValue(register: ArrayList<String>){
        _register.value = register
    }
    fun especializacaoValue(especializacao: String) {
        _especializacao.value = especializacao
    }

    fun timeValue(time: String) {
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

    fun linkWppValue(link: String) {
        _wppLink.value = link
    }


    private fun registerHashMapData(): HashMap<String, String> {

        return hashMapOf(
            "psi_name" to (_name.value ?: _register.value?.get(0).toString()),
            "psi_email" to (_email.value ?: _register.value?.get(1).toString()),
            "psi_phone" to (_phone.value ?: _register.value?.get(2).toString()),
            "psi_linkwpp" to (_wppLink.value ?: _register.value?.get(3).toString()),
            "psi_crp" to (_crp.value ?: _register.value?.get(5).toString()),
            "_psi_especializacao" to (_especializacao.value ?: _register.value?.get(6).toString()),
            "psi_time" to (_time.value ?: _register.value?.get(4).toString()) )

    }

    fun updateRegisterCollection(): Deferred<Unit> {

        val result = viewModelScope.async {
            repository.updatePsiRegister(registerHashMapData()).collect({})

        }

        return result

    }
}






