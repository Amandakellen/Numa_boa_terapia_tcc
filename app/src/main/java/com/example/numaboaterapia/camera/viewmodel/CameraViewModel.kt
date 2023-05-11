package com.example.numaboaterapia.camera.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.camera.data.repositry.FirebaseStorageRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel:ViewModel() {

    private lateinit var tempFilePath: String
    private val repository = FirebaseStorageRepository()
    private lateinit var userType: String

     fun bitmapToFile(bitmap: Bitmap, type: String?) {
         if (type != null) {
             userType = type
         }
        // Cria um arquivo temporário
        val tempFile = File.createTempFile("prefix", "suffix")

        // Escreve o bitmap no arquivo temporário
        tempFile.outputStream().use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        }

        // Retorna o caminho do arquivo temporário
         tempFilePath =  tempFile.absolutePath
    }

    fun sendToFirebase(): Deferred<Flow<Result<Unit>>> {
        val result = viewModelScope.async {
            repository.uploadImageToFirebaseStorage(tempFilePath, userType)
        }

        return result
    }

}