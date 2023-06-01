package com.example.numaboaterapia.appNavigation.psychologist.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class PsiPatientListRepository {

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    private val db = Firebase.firestore
    private var listPatientUid =  ArrayList<String>()
    private lateinit var imageDefault : ByteArray

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {

            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun getPatientInformation(patientId: String): Flow<ArrayList<HashMap<String, String>>> = flow {
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("psi_patient_list")
        val query = docRef.
        whereEqualTo("uId", userUUID).
        whereEqualTo("patient_uid", patientId)

        val list = ArrayList<HashMap<String, String>>()
        val documents = query.get().await()
        for (document in documents) {
            val psiData = hashMapOf(
                "patient_name" to document.data.getValue("patient_name").toString(),
                "patient_email" to document.data.getValue("patient_email").toString(),
                "patient_wpp" to document.data.getValue("patient_wpp").toString(),
                "patient_cpf" to document.data.getValue("patient_cpf").toString(),
                "patient_gender" to document.data.getValue("patient_gender").toString(),
                "patient_birthday" to document.data.getValue("patient_birthday").toString(),
                "patient_profession" to document.data.getValue("patient_profession").toString(),
                "patient_schooling" to document.data.getValue("patient_schooling").toString(),
                "patient_country" to document.data.getValue("patient_country").toString(),
                "patient_payment" to document.data.getValue("patient_payment").toString(),
                "patient_observation" to document.data.getValue("patient_observation").toString(),
                "patient_address" to document.data.getValue("patient_address").toString(),
                "patient_number" to document.data.getValue("patient_number").toString(),
                "patient_neighborhood" to document.data.getValue("patient_neighborhood").toString(),
                "patient_city" to document.data.getValue("patient_city").toString(),
                "patient_uf" to document.data.getValue("patient_uf").toString(),
                "patient_contact_name" to document.data.getValue("patient_contact_name").toString(),
                "patient_contact_wpp" to document.data.getValue("patient_contact_wpp").toString(),
                "patient_uid" to document.data.getValue("patient_uid").toString()


            )

            list.add(psiData)

            listPatientUid.add(document.data.getValue("patient_uid").toString())
        }
        emit(list)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<HashMap<String, String>>())
    }.flowOn(Dispatchers.IO)

    fun getPatientCollection(): Flow<ArrayList<HashMap<String, String>>> = flow {
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("psi_patient_list")
        val query = docRef.whereEqualTo("uId", userUUID).orderBy("patient_name")
        val list = ArrayList<HashMap<String, String>>()
        val documents = query.get().await()
        for (document in documents) {
            val psiData = hashMapOf(
                "patient_name" to document.data.getValue("patient_name").toString(),
                "patient_email" to document.data.getValue("patient_email").toString(),
                "patient_wpp" to document.data.getValue("patient_wpp").toString(),
                "patient_cpf" to document.data.getValue("patient_cpf").toString(),
                "patient_gender" to document.data.getValue("patient_gender").toString(),
                "patient_birthday" to document.data.getValue("patient_birthday").toString(),
                "patient_profession" to document.data.getValue("patient_profession").toString(),
                "patient_schooling" to document.data.getValue("patient_schooling").toString(),
                "patient_country" to document.data.getValue("patient_country").toString(),
                "patient_payment" to document.data.getValue("patient_payment").toString(),
                "patient_observation" to document.data.getValue("patient_observation").toString(),
                "patient_address" to document.data.getValue("patient_address").toString(),
                "patient_number" to document.data.getValue("patient_number").toString(),
                "patient_neighborhood" to document.data.getValue("patient_neighborhood").toString(),
                "patient_city" to document.data.getValue("patient_city").toString(),
                "patient_uf" to document.data.getValue("patient_uf").toString(),
                "patient_contact_name" to document.data.getValue("patient_contact_name").toString(),
                "patient_contact_wpp" to document.data.getValue("patient_contact_wpp").toString(),
                "patient_uid" to document.data.getValue("patient_uid").toString()


            )

            list.add(psiData)

            listPatientUid.add(document.data.getValue("patient_uid").toString())
        }
        emit(list)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<HashMap<String, String>>())
    }.flowOn(Dispatchers.IO)

    fun setImageDefault(image : ByteArray) {
        imageDefault = image
    }

    fun getImageFilesFromStorage(): Flow<List<ByteArray?>> = flow {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference

        val imageByteArrays = mutableListOf<ByteArray?>()

        for (fileName in listPatientUid) {
            var imageName = fileName + ".jpg"
            try{
                val fileRef = storageRef.child("pacient/$imageName")
                val ONE_MEGABYTE: Long = 1024 * 1024
                val bytes = fileRef.getBytes(ONE_MEGABYTE).await()
                imageByteArrays.add(bytes)}
            catch(e: Exception){
                imageByteArrays.add(null)
            }
        }

        emit(imageByteArrays)
    }.flowOn(Dispatchers.IO)
}