package com.example.numaboaterapia.camera.data.repositry

import android.graphics.BitmapFactory
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
import java.io.ByteArrayOutputStream
import java.io.File

class FirebaseStorageRepository {
    private val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>?
    private val userLoggedMutableLiveData: MutableLiveData<Boolean>
    private  var firebaseImage = ByteArray(2)
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
    fun getImageByteArray():ByteArray =firebaseImage

    fun getFirebaseFileImage(type : String): Flow<String> = callbackFlow {
        val filename = FirebaseAuth.getInstance().currentUser?.uid + ".jpg"
        val storageRef = Firebase.storage.reference.child("$type/$filename")
        val ONE_MEGABYTE: Long = 1024 * 1024
        storageRef.getBytes(ONE_MEGABYTE)
            .addOnSuccessListener { bytes ->
                firebaseImage = bytes
                trySend("success").isSuccess
            }
            .addOnFailureListener { exception ->
                trySend("erro").isSuccess
            }
        awaitClose()
    }


    fun uploadImageToFirebaseStorage(type: String, dataImage: ByteArray): Flow<String> = callbackFlow {
        // Definir o nome do arquivo que será salvo no Firebase Storage
        val filename = FirebaseAuth.getInstance().currentUser?.uid + ".jpg"

        // Obter uma referência ao nó do Firebase Storage onde o arquivo será armazenado
        val storageRef = Firebase.storage.reference.child("$type/$filename")

        // Enviar o arquivo para o Firebase Storage
        val uploadTask = storageRef.putBytes(dataImage)

        // Lidar com o resultado do upload (opcional)
        uploadTask.addOnSuccessListener {
            // O upload foi bem-sucedido
            trySend("sucesso").isSuccess
        }.addOnFailureListener {
            // Ocorreu um erro durante o upload
            trySend("erro").isSuccess
        }

        // CallbackFlow should be closed when no longer needed.
        awaitClose()
    }






}