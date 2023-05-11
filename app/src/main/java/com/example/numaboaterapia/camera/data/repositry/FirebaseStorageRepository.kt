package com.example.numaboaterapia.camera.data.repositry

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.io.File

class FirebaseStorageRepository {
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

    fun uploadImageToFirebaseStorage(imagePath: String, type: String): Flow<Result<Unit>> =
        callbackFlow {
            val storageRef = Firebase.storage.reference
            val imageFileName = FirebaseAuth.getInstance().currentUser?.uid + ".jpg"
            val storageReference =
                FirebaseStorage.getInstance().reference.child("$type/$imageFileName")
            val imageFile = Uri.fromFile(File(imagePath))
            val uploadTask = storageReference.putFile(imageFile)

            uploadTask.addOnSuccessListener {
                // A imagem foi carregada com sucesso
                Log.d("TAG", "Imagem carregada com sucesso")
                trySend(Result.success(Unit)).isSuccess
                close()
            }.addOnFailureListener { exception ->
                // Ocorreu um erro ao carregar a imagem
                Log.e("TAG", "Erro ao carregar a imagem", exception)
                trySend(Result.failure(exception)).isSuccess
                close(exception)
            }

            awaitClose()
        }

}