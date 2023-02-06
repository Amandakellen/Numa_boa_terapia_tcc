package com.example.numaboaterapia.Login.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await


class LoginRepository {
    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private val auth: FirebaseAuth

    init {
        firebaseUserMutableLiveData = MutableLiveData()
        userLoggedMutableLiveData = MutableLiveData()

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }
    suspend fun login(email:String, pass: String): String {
        return try{
                auth
                .signInWithEmailAndPassword(email,pass)
                .await()
            return "Sucesso"
        }catch (e : Exception){
            return e.message.toString()
        }
    }

//    fun login(email: String?, pass: String?) {
//        auth.signInWithEmailAndPassword(email!!, pass!!).addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                firebaseUserMutableLiveData?.postValue(auth.currentUser)
//            }
//
//            else {
//                requestResult = task.exception?.message.toString()
//
//            }
//        }
//    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }
}

