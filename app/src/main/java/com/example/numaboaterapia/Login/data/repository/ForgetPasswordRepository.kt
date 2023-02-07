package com.example.numaboaterapia.Login.data.repository

import android.app.Application
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class ForgetPasswordRepository {
    private val auth: FirebaseAuth

    init {
        auth = FirebaseAuth.getInstance()
    }

    suspend fun sendEmail(application: Application, email: String) {
        try {
            auth
                .sendPasswordResetEmail(email)
                .await()
        } catch (e: Exception) {
              val message = checkErrorMessage(e.message.toString())
              Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkErrorMessage(message : String) : String{
        when(message){
            else->{return "Ocorreu um erro durante a operação, tente novamente mais tarde"}
        }
    }
}