package com.example.numaboaterapia.appNavigation.psychologist.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

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

    fun updatePsiRegister(register : HashMap<String, String>): Flow<Boolean> = flow{
        val userUUID = auth.currentUser!!.uid
        register.put("uId", auth.currentUser!!.uid)
        val docRef = db.collection("psi_users")
        val query = docRef.whereEqualTo("uId", userUUID)
        val documents = query.get().await()
            for (document in documents) {

                document.reference.update(register as Map<String, Any>).await()
                emit(true)
            }

    }.catch {
        emit(false)
    }

}