package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditDataViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()

    private val _name = MutableLiveData<String>()

    private val _phone = MutableLiveData<String>()

    private val _wppLink = MutableLiveData<String>()

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

    private fun registerHashMapData(): HashMap<String, String> {
        return hashMapOf(
            "psi_name" to _name.value.toString(),
            "psi_email" to _email.value.toString(),
            "psi_phone" to _phone.value.toString(),
            "psi_linkwpp" to _wppLink.value.toString(),
            "_psi_especializacao" to _especializacao.value.toString(),
            "psi_time" to _time.value.toString()
        )

    }


}