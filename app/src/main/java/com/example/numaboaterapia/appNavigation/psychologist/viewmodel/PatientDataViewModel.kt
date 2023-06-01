package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.PsiPatientListRepository
import kotlinx.coroutines.async

class PatientDataViewModel: ViewModel() {
    private val repository = PsiPatientListRepository()
    private lateinit var patientData : ArrayList<HashMap<String, String>>


    fun getPatientData():ArrayList<HashMap<String, String>> = patientData

    suspend fun getPatientCollection(patientId: String) {

        val result = viewModelScope.async {
            repository.getPatientInformation(patientId)
        }
        result.await().collect { user ->
            patientData = user

        }
    }
}