package com.example.numaboaterapia.register.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class FirebaseResponseRepository {

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    private val db = Firebase.firestore

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    suspend fun saveData(
        collectionPath: String,
        userData: HashMap<String, String>
    ): String {
        try {
            userData.put("uId", auth.currentUser!!.uid)
            db.collection(collectionPath).add(userData)
            return "Sucesso"
        } catch (e: Exception) {
            return "Ocorreu um erro, tente novamente!"
        }
    }

    fun checkValueInPacientList(patientId: String): Flow<Boolean> = callbackFlow {
        val userUUID = auth.currentUser!!.uid
        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("psi_patient_list")

        collectionRef
            .whereEqualTo("uId", userUUID)
            .whereEqualTo("patient_uid", patientId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val exists = !querySnapshot.isEmpty
                trySend(exists).isSuccess // Envia o resultado da verificação
                close()
            }
            .addOnFailureListener { exception ->
                // Ocorreu um erro ao acessar o Firebase Firestore
                println("Erro ao acessar o Firestore: $exception")
                close(exception)
            }

        awaitClose()
    }


}