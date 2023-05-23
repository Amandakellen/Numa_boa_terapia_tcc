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
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class LoginViewModel() : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _pass = MutableLiveData<String>()
    val pass: LiveData<String> = _pass

    private var _userData: MutableLiveData<FirebaseUser>? = null
    var userData: LiveData<FirebaseUser>? = _userData

    private var _loggedStatus: MutableLiveData<Boolean>? = null
    var loggedStatus: LiveData<Boolean>? = _loggedStatus

    private var repository: LoginRepository = LoginRepository()

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

    fun getErrorMessage() : String =  repository.getErrorMessage()

    fun verifyLogin() : Deferred<Flow<Boolean>> {
        val result = viewModelScope.async {
            repository.login(_email.value.toString(), _pass.value.toString())
        }

        return result

    }

    suspend fun verifyIfIsPacient():Boolean{
        var result =viewModelScope.async{
            repository.checkValueInPacientTable()
        }

        var retorno = false
        result.await().collect{
            retorno = it== true
        }
        return retorno
    }

    suspend fun verifyIfIsPsi():Boolean{
        var result =viewModelScope.async{
            repository.checkValueInPsiTable()
        }

        var retorno = false
        result.await().collect{
            retorno = it== true
        }
        return retorno
    }






}