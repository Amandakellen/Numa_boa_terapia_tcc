package com.example.numaboaterapia.camera.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.camera.data.repositry.FirebaseStorageRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class CameraViewModel:ViewModel() {


    private val repository = FirebaseStorageRepository()

    suspend fun sendToFirebase(dataImage: ByteArray, userType: String): String {
        val result = viewModelScope.async {
            repository.uploadImageToFirebaseStorage(userType, dataImage)
        }
        var retorno = ""
        result.await().collect { response ->
            when (response) {
                "sucesso" -> {
                    retorno = "sucesso"
                }

                "erro" -> {
                    retorno= "erro"
                }
            }
        }
        return retorno
    }

}