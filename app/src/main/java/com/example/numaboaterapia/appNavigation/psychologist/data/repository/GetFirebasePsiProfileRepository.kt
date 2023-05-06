package com.example.numaboaterapia.appNavigation.psychologist.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class GetFirebasePsiProfileRepository {

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

    fun getCollection(): Flow<ArrayList<String>> = flow {
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("patient_users")
        val query = docRef.whereEqualTo("uId", userUUID)
        val profileList = ArrayList<String>()
        val documents = query.get().await()
        for (document in documents) {

            profileList.add(document.data.getValue("pu_name").toString())
            profileList.add(document.data.getValue("pu_email").toString())
            profileList.add(document.data.getValue("pu_phone").toString())

        }
        emit(profileList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<String>())
    }.flowOn(Dispatchers.IO)
}