package com.example.numaboaterapia.Login.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.data.RequestResult
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth


class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _senha = MutableLiveData<String>()
    val senha: LiveData<String> = _senha

    private lateinit var _mAuth: FirebaseAuth


    fun emailValue(email: String) {
        _email.value = email
    }

    fun senhaValue(senha: String) {
        _senha.value = senha
    }

    fun setAuhthentication(mAuth: FirebaseAuth) {
        _mAuth = mAuth
    }

    fun getAuthentication(): FirebaseAuth{
        val mAuth = _mAuth
        return mAuth
    }


    fun verifyLogin() {
        _mAuth.signInWithEmailAndPassword(_email.value.toString(), _senha.value.toString())
            .addOnCompleteListener { task ->
                onComplete(task)

            }
    }

    private fun onComplete(task: Task<AuthResult>): RequestResult
       = if (task.isSuccessful)  RequestResult.SUCCESSS else  RequestResult.FAILURE




}