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
     lateinit var listBiography: ArrayList<String>
    val db = FirebaseFirestore.getInstance()

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun getRegiterCollection(): Flow<ArrayList<String>> = flow {
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("psi_users")
        val query = docRef.whereEqualTo("uId", userUUID)
        val registerList = ArrayList<String>()
        val documents = query.get().await()
        for (document in documents) {

            registerList.add(document.data.getValue("psi_name").toString())
            registerList.add(document.data.getValue("psi_email").toString())
            registerList.add(document.data.getValue("psi_phone").toString())
            registerList.add(document.data.getValue("psi_linkwpp").toString())
            registerList.add(document.data.getValue("psi_time").toString())
            registerList.add(document.data.getValue("psi_crp").toString())
            registerList.add(document.data.getValue("_psi_especializacao").toString())


        }
        emit(registerList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<String>())
    }.flowOn(Dispatchers.IO)

    fun getBiographyCollection(): Flow<ArrayList<String>> = flow {
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("psi_biography")
        val query = docRef.whereEqualTo("uId", userUUID)
        val biographyList = ArrayList<String>()
        val documents = query.get().await()
        for (document in documents) {

            biographyList.add(document.data.getValue("psi_biography").toString())
            biographyList.add(document.data.getValue("psi_city").toString())
            biographyList.add(document.data.getValue("psi_uf").toString())
            biographyList.add(document.data.getValue("psi_type_of_service").toString())
            listBiography = biographyList
        }
        emit(biographyList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<String>())
    }.flowOn(Dispatchers.IO)

    fun getSpecialtiesCollection(): Flow<ArrayList<String>> = flow {
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("psi_specialties")
        val query = docRef.whereEqualTo("uId", userUUID)
        var specialtiesList = ArrayList<String>()
        val documents = query.get().await()
        for (document in documents) {

            specialtiesList =
                document.data.getValue("psi_specialties").
                toString().split(",") as ArrayList<String>


        }
        emit(specialtiesList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<String>())
    }.flowOn(Dispatchers.IO)
}