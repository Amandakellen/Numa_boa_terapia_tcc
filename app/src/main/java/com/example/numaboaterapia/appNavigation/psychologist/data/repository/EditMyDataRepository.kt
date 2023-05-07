package com.example.numaboaterapia.appNavigation.psychologist.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class EditMyDataRepository {
    private val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    private val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun updatePsiRegister(register : HashMap<String, String>): Flow<Boolean> = callbackFlow{
        val userUUID = auth.currentUser!!.uid
        register.put("uId", auth.currentUser!!.uid)
        val docRef = db.collection("psi_users")
        val query = docRef.whereEqualTo("uId", userUUID)
        query.get().addOnSuccessListener {documents->
            for (document in documents) {

                document.reference.update(register as Map<String, Any>).addOnCanceledListener {
                    trySend(true).isSuccess
                }.addOnFailureListener {
                    trySend(false).isSuccess
                }
            }
            close()
        }.addOnFailureListener {e->
            trySend(false).isSuccess
            close(e)
        }
        awaitClose()


    }

}