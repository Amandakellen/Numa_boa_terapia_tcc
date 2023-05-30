package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.PsiPatientListRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PsiPatientListViewModel:ViewModel() {
    private val repository = PsiPatientListRepository()
    private lateinit var patientData : ArrayList<HashMap<String, String>>
    private  var patientImage = mutableListOf<ByteArray>()
    private lateinit var imageDefaut : ByteArray


    fun getPatientList(): ArrayList<HashMap<String, String>> = patientData
    fun getImageData(): List<ByteArray> = patientImage

    suspend fun getPatientCollection() {

        val result = viewModelScope.async {
            repository.getPatientCollection()
        }
        result.await().collect { user ->
            patientData = user

        }
    }
    fun setImageDefault(image : ByteArray){
        imageDefaut = image
    }

    suspend fun getImage(){
        repository.setImageDefault(imageDefaut)
        val result = viewModelScope.async {
            repository.getImageFilesFromStorage()
        }

        result.await().collect{list->
            patientImage = list.toMutableList()
        }

    }
}