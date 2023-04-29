package com.example.numaboaterapia.appNavigation.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseDataRepository(val colectionName :String) {

    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private lateinit var dataHashMap: HashMap<Int,Any>
    val dataList = ArrayList<String>()
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



    fun getColection(){
        var i = 0
        val userUUID = auth.currentUser!!.uid
        val docRef = db.collection(colectionName)
        val query = docRef.whereEqualTo("uId", userUUID)
        query.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val hasMapFirebase = hashMapOf(
                        "diary_feeling" to document.data.getValue("diary_feeling"),
                        "diary_text" to document.data.getValue("diary_text"),
                        "diary_date" to document.data.getValue("diary_date")

                    )

                    dataHashMap.put(i, hasMapFirebase)
                    i++

                }
            }
            .addOnFailureListener { exception ->
                dataHashMap.put(i, "erro")
            }

    }
}