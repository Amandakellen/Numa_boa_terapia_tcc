package com.example.numaboaterapia.register.pacient.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PatientResponseRepository {

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
}