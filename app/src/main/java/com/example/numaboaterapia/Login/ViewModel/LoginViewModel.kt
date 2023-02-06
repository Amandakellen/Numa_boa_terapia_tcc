package com.example.numaboaterapia.Login.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.Login.data.repository.LoginRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


class LoginViewModel(val application: Application) : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private var _userData: MutableLiveData<FirebaseUser>? = null
    var userData: LiveData<FirebaseUser>? = _userData

    private var _loggedStatus: MutableLiveData<Boolean>? = null
    var loggedStatus: LiveData<Boolean>? = _loggedStatus

    private var repository:LoginRepository = LoginRepository()

    init {
        _userData = repository.firebaseUserMutableLiveData
        _loggedStatus = repository.userLoggedMutableLiveData

    }

    fun emailValue(email: String) {
        _email.value = email
    }

    fun senhaValue(senha: String) {
        _pass.value = senha
    }


    fun verifyLogin() {
        viewModelScope.launch {
            repository.login(application,_email.value.toString(), _pass.value.toString())
        }

    }


}