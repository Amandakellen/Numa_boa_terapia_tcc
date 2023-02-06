package com.example.numaboaterapia.Login.data.repository

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await


class LoginRepository {
    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }
    suspend fun login(application: Application, email:String, pass: String) {
         try{
                auth
                .signInWithEmailAndPassword(email,pass)
                .await()
             Toast.makeText(application, "Sucesso", Toast.LENGTH_SHORT).show();
        }catch (e : Exception){
            val message =  checkLoginResult(e.message.toString())
            Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkLoginResult(loginResult: String) : String{
        when(loginResult){
            "The email address is badly formatted."->{ return "O email digitado não é um email válido"}
            "There is no user record corresponding to this identifier. The user may have been deleted."->{return "Usuário não registrado"}
            else->{ return "Ocorreu um erro durante o Login, tente novamente"}
        }
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }
}

