package com.example.numaboaterapia.appNavigation.pacient.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.tasks.await
import java.util.Locale


class PatientDiaryRepository(val colectionName: String) {

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>

    var textList = ArrayList<String>()
    var dateList = ArrayList<String>()
    var feelingList = ArrayList<String>()
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


    fun getCollection(): Flow<HashMap<Int, Any>> = flow {
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection("patient_diary")
        val query =
            docRef.whereEqualTo("uId", userUUID).orderBy("diary_date", Query.Direction.DESCENDING)
        val dataHashMap = HashMap<Int, Any>()
        val documents = query.get().await()
        var i = 0
        for (document in documents) {
            val feeling = document.getString("diary_feeling") ?: ""
            val text = document.getString("diary_text") ?: ""
            val timestamp = document.getTimestamp("diary_date")

            val diaryDate = java.text.SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault())
                .format(timestamp?.toDate())

            val hasMapFirebase = hashMapOf(
                "diary_feeling" to feeling,
                "diary_text" to text,
                "diary_date" to diaryDate
            )

            textList.add(text)
            dateList.add(diaryDate)
            feelingList.add(feeling)
            dataHashMap[i] = hasMapFirebase
            i++
        }
        emit(dataHashMap)
    }.catch { exception ->
        Log.e("getCollection", "Error getting collection", exception)
        emit(HashMap<Int, Any>())
    }.flowOn(Dispatchers.IO)

}