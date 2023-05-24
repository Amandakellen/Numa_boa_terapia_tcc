package com.example.numaboaterapia.appNavigation.pacient.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.numaboaterapia.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

class PsiListRepository {
    private val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    private val userLoggedMutableLiveData: MutableLiveData<Boolean>
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

    fun setImageDefault(image : ByteArray) {
        imageDefault = image
    }

    fun getPsiUsers(): Flow<ArrayList<HashMap<String, String>>> = flow {
        val docRef = db.collection("psi_users")
        val profileList = ArrayList<HashMap<String, String>>()
        val documents = docRef.get().await()
        for (document in documents) {
            val data = document.data

            val psiData = hashMapOf(
                "uuid" to data["uId"] as String,
                "name" to data["psi_name"] as String,
                "crp" to  data["psi_crp"] as String,
                "especializacao" to data["_psi_especializacao"] as String,
                "time" to data["psi_time"] as String,
                "link" to data["psi_linkwpp"] as String
            )

            psiUid.add(data["uId"] as String)
            profileList.add(psiData)

        }
        emit(profileList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<HashMap<String, String>>())
    }.flowOn(Dispatchers.IO)


    fun getPsiBiography(): Flow<ArrayList<HashMap<String, String>>> = flow {
        val profileList = ArrayList<HashMap<String, String>>()

        psiUid.forEach { userUUID ->
            val docRef = db.collection("psi_biography")
            val query = docRef.whereEqualTo("uId", userUUID)
            val documents = query.get().await()

            for (document in documents) {
                val data = document.data

                val psiData = hashMapOf(
                    "uuid" to data["uId"] as String,
                    "biography" to data["psi_biography"] as String,
                    "city" to data["psi_city"] as String,
                    "service" to data["psi_type_of_service"] as String,
                    "uf" to data["psi_uf"] as String
                )

                profileList.add(psiData)
            }
        }

        emit(profileList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<HashMap<String, String>>())
    }.flowOn(Dispatchers.IO)


    fun getPsiSpecialties(): Flow<ArrayList<HashMap<String, String>>> = flow {
        psiUid.forEach {
            val userUUID = it
            val docRef = db.collection("psi_specialties")
            val query = docRef.whereEqualTo("uId", userUUID)
            val profileList = ArrayList<HashMap<String, String>>()
            val documents = query.get().await()
            for (document in documents) {
                val data = document.data

                val psiData = hashMapOf(
                    "uuid" to data["uId"] as String,
                    "specialties" to data["psi_specialties"] as String
                )

                profileList.add(psiData)

                Log.i("repository", psiData.toString())

            }
            emit(profileList)
        }


    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<HashMap<String, String>>())
    }.flowOn(Dispatchers.IO)


    fun getImageFilesFromStorage(): Flow<List<ByteArray>> = flow {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        val imageByteArrays = mutableListOf<ByteArray>()

        for (fileName in psiUid) {
            var imageName = fileName + ".jpg"
            try{
            val fileRef = storageRef.child("psi/$imageName")
            val ONE_MEGABYTE: Long = 1024 * 1024
            val bytes = fileRef.getBytes(ONE_MEGABYTE).await()
            imageByteArrays.add(bytes)}
            catch(e: Exception){
                imageDefault?.let { imageByteArrays.add(it) }
            }
        }

        emit(imageByteArrays)
    }.flowOn(Dispatchers.IO)



}