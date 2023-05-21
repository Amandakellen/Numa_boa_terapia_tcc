package com.example.numaboaterapia.Login.data.repository

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class LoginRepository {
    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    private var errorMessage = " "

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun getErrorMessage(): String = errorMessage

    fun login(email: String, pass: String): Flow<Boolean> = callbackFlow {
        try {
            auth.signInWithEmailAndPassword(email, pass).await()
            trySend(true).isSuccess// Login bem-sucedido
        } catch (e: Exception) {
            errorMessage = checkLoginResult(e.message.toString())
            trySend(false).isSuccess // Login falhou
        }

        awaitClose()
    }

    fun checkValueInCollections(): Flow<Pair<String, Boolean>> = flow {
        val userUUID = auth.currentUser!!.uid
        val firestore = FirebaseFirestore.getInstance()

        // Lista de nomes das coleções para verificar
        val collectionNames = mapOf(
            "psi" to "psi_users",
            "pacient" to "patient_users"
        )

        for ((collectionId, collectionName) in collectionNames) {
            val query = firestore.collection(collectionId).whereEqualTo("valueField", userUUID)
            val querySnapshot = query.get().await()

            if (!querySnapshot.isEmpty) {
                emit(collectionName to true) // Valor encontrado na coleção correspondente
                return@flow
            }
        }

        emit("" to false) // Valor não encontrado em nenhuma das coleções
    }

    private fun checkLoginResult(loginResult: String): String {
        when (loginResult) {
            "The email address is badly formatted." -> {
                return "O email digitado não é um email válido"
            }

            "There is no user record corresponding to this identifier. The user may have been deleted." -> {
                return "Usuário não cadastrado"
            }

            "The password is invalid or the user does not have a password." -> {
                return "Senha incorreta, digite novamente"
            }

            else -> {
                return "Ocorreu um erro durante o Login, tente novamente"
            }
        }
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }

    fun deleteUser() {
        val user = auth.currentUser
        user?.delete()
    }
}

