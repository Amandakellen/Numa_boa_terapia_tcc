package com.example.numaboaterapia.Login.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel :ViewModel(){
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _senha = MutableLiveData<String>()
    val senha : LiveData<String> = _senha

    fun emailValue(email:String){
        _email.value = email
    }

    fun senhaValue(senha:String){
        _senha.value = senha
    }
}