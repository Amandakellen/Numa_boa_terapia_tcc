package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.PsiPatientListRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import java.util.Locale

class PsiPatientListViewModel:ViewModel() {
    private val repository = PsiPatientListRepository()
    private lateinit var patientData : ArrayList<HashMap<String, String>>
    private  var patientImage = mutableListOf<ByteArray?>()
    private lateinit var imageDefaut : ByteArray
    private var filteredPatientData = ArrayList<HashMap<String, String>>()
    private var filteredPatientImage = mutableListOf<ByteArray?>()
    var searchText = MutableLiveData<String>()

    init {
        searchText.value = ""
    }

    fun setSearchText(text: String) {
        searchText.value = text
    }

    fun getFilteredPatientData(): ArrayList<HashMap<String, String>> = filteredPatientData
    fun getFilteredPatientImage(): List<ByteArray?> = filteredPatientImage

    fun getPatientList(): ArrayList<HashMap<String, String>> = patientData
    fun getImageData(): List<ByteArray?> = patientImage

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

    fun filterPatientDataAndImages() {
        val patientList = getPatientList()
        val imageList = getImageData()

        val searchTextValue = searchText.value?.toLowerCase(Locale.getDefault())

        filteredPatientData.clear()
        filteredPatientImage.clear()

        val filteredImagesMap = mutableMapOf<String, ByteArray?>()

        for (i in patientList.indices) {
            val patient = patientList[i]
            val image = imageList[i]

            val patientName = patient["patient_name"]?.toLowerCase(Locale.getDefault())
            val patientCpf = patient["patient_cpf"]?.toLowerCase(Locale.getDefault())

            if (searchTextValue.isNullOrEmpty() || patientName?.contains(searchTextValue) == true ||
                patientCpf?.contains(searchTextValue) == true
            ) {
                filteredPatientData.add(patient)
                filteredPatientImage.add(image)

                // Armazena a imagem filtrada no mapa usando o ID do paciente como chave
                val patientId = patient["patient_id"]
                if (patientId != null) {
                    filteredImagesMap[patientId] = image
                }
            }
        }

        // Popula a lista de imagens filtradas na ordem correspondente aos dados filtrados
        filteredPatientImage.addAll(filteredPatientData.mapNotNull { patient ->
            val patientId = patient["patient_id"]
            filteredImagesMap[patientId]
        })
    }


}