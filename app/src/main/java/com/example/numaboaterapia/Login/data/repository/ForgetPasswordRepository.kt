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

    suspend fun sendEmail(application: Application, email: String) :Boolean {
        try {
            auth
                .sendPasswordResetEmail(email)
                .await()
            return true
        } catch (e: Exception) {
            val message = checkErrorMessage(e.message.toString())
            Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
            return false
        }
    }

    private fun checkErrorMessage(message: String): String {
        when (message) {
            "There is no user record corresponding to this identifier. The user may have been deleted." -> {
                return "Usuário não cadastrado"
            }
            else -> {
                return "Ocorreu um erro durante a operação, tente novamente mais tarde"
            }
        }
    }
}