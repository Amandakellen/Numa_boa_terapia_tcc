package com.example.numaboaterapia.appNavigation.psychologist.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProfileAccessesRepository {
    private val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    private val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth
    private var userData = ArrayList<HashMap<String, String>>()
    val db = FirebaseFirestore.getInstance()

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }

    fun getAccessUsers(): Flow<ArrayList<HashMap<String, String>>> = flow {

        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("patient_accesses")
            .whereEqualTo("psi_uid", userUUID)
            .orderBy("accesses_date", Query.Direction.DESCENDING)

        val profileList = ArrayList<HashMap<String, String>>()

        try {
            val documents = docRef.get().await()
            for (document in documents) {
                val data = document.data

                val psiData = hashMapOf(
                    "access_date" to data["accesses_date"] as String,
                    "psi_uuid" to data["psi_uid"] as String,
                    "Uid" to data["uId"] as String
                )

                profileList.add(psiData)
            }
            userData = profileList
            emit(profileList)
        } catch (exception: Exception) {
            Log.e("getCollection", "Error getting collection", exception)
            emit(ArrayList<HashMap<String, String>>())
        }
    }.flowOn(Dispatchers.IO)

    fun getUsersData(): Flow<ArrayList<HashMap<String, String>>> = flow {
        val profileList = ArrayList<HashMap<String, String>>()
        userData.forEach { userData ->
            val uId = userData["Uid"]
            val accessDate = userData["access_date"]
            val docRef = db.collection("patient_users")
            val query = docRef.whereEqualTo("uId", uId)
            val documents = query.get().await()

            for (document in documents) {
                val data = document.data
                val date = accessDate?.let { parseDate(it) }
                val psiData = hashMapOf(
                    "day" to date!![0],
                    "mounth" to date!![1],
                    "year"  to date!![2],
                    "email" to data["pu_email"] as String,
                    "name" to data["pu_name"] as String
                )

                profileList.add(psiData)
            }

        }
        emit(profileList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<HashMap<String, String>>())
    }.flowOn(Dispatchers.IO)

    fun getUsersAverageIncome(): Flow<ArrayList<HashMap<String, String>>> = flow {
        val profileList = ArrayList<HashMap<String, String>>()
        userData.forEach { userData ->
            val uId = userData["Uid"]
            val docRef = db.collection("patient_average_income")
            val query = docRef.whereEqualTo("uId", uId)
            val documents = query.get().await()

            for (document in documents) {
                val data = document.data

                val psiData = hashMapOf(
                    "average" to data["pri_average_income"] as String
                )

                profileList.add(psiData)
            }

        }
        emit(profileList)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(ArrayList<HashMap<String, String>>())
    }.flowOn(Dispatchers.IO)

    fun parseDate(dateString: String): List<String> {
        val dateParts = mutableListOf<String>()
        try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val calendar = Calendar.getInstance()
            calendar.time = sdf.parse(dateString)

            // Obter o dia
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            dateParts.add(day.toString())

            // Obter o nome do mês
            val month = calendar.get(Calendar.MONTH)
            val monthName = SimpleDateFormat("MMMM",
                Locale.getDefault()).format(calendar.time)
            dateParts.add(monthName)

            // Obter o ano
            val year = calendar.get(Calendar.YEAR)
            dateParts.add(year.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return dateParts
    }
}