package com.example.numaboaterapia.Login.ViewModel

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


class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private var _userData: MutableLiveData<FirebaseUser>? = null
    var userData: LiveData<FirebaseUser>? = _userData

    private var _loggedStatus: MutableLiveData<Boolean>? = null
    var loggedStatus: LiveData<Boolean>? = _loggedStatus

    lateinit var result: String
    private var repository:LoginRepository = LoginRepository()

    init {
        _userData = repository.firebaseUserMutableLiveData
        _loggedStatus = repository.userLoggedMutableLiveData
        result = ""

    }

    fun emailValue(email: String) {
        _email.value = email
    }

    fun senhaValue(senha: String) {
        _pass.value = senha
    }


    fun verifyLogin() {
        viewModelScope.launch {
            val checkLogin = repository.login(_email.value.toString(), _pass.value.toString())
            checkLoginResult(checkLogin)

        }

    }

    private fun checkLoginResult(loginResult: String){
        when(loginResult){
            "The email address is badly formatted."->{ result = "O email digitado não é um email válido"}
            "There is no user record corresponding to this identifier. The user may have been deleted."->{ result = "Usuário não registrado"}
            else->{ result ="Ocorreu um erro durante o Login, tente novamente"}
        }
    }







}