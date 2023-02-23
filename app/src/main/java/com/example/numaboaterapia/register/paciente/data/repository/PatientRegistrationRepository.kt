package com.example.numaboaterapia.register.paciente.data.repository

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class PatientRegistrationRepository{
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

    suspend fun createUser(application: Application, email: String, pass: String) {
        try {
            auth
                .createUserWithEmailAndPassword(email, pass)
                .await()
            Toast.makeText(application, "Sucesso", Toast.LENGTH_SHORT).show();
        } catch (e: Exception) {
            val message = checkRequestResult(e.message.toString())
            Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkRequestResult(message: String): String {
        when (message) {
            "The email address is badly formatted." -> {
                return "O email digitado não é um email válido"
            }
            "The email address is already in use by another account."->{
                return "O email digitado já está cadastrado"
            }

            else -> {
                return "Ocorreu um erro durante o Login, tente novamente"
            }
        }
    }


}