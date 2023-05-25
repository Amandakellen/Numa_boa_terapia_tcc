package com.example.numaboaterapia.appNavigation.pacient.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class PsiProfileInformationRepository {
    private val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    private val userLoggedMutableLiveData: MutableLiveData<Boolean>
    lateinit var listBiography: ArrayList<String>
    private val auth: FirebaseAuth
    private var psiUid : ArrayList<String> = arrayListOf()
    private lateinit var imageDefault : ByteArray
    val db = FirebaseFirestore.getInstance()

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun getRegiterCollection(userUUID : String): Flow<ArrayList<String>> = flow {
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

    fun getBiographyCollection(userUUID : String): Flow<ArrayList<String>> = flow {

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

    fun getSpecialtiesCollection(userUUID : String): Flow<ArrayList<String>> = flow {
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

    fun getFirebaseFileImage(userUUID: String): Flow<ByteArray> = flow {
        val filename = userUUID + ".jpg"
        val storageRef = Firebase.storage.reference.child("psi/$filename")
        val ONE_MEGABYTE: Long = 1024 * 1024

        val bytes = withContext(Dispatchers.IO) {
            storageRef.getBytes(ONE_MEGABYTE).await()
        }

        emit(bytes)
    }.catch { exception ->
        emit(null)
    }
}