package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.pacient.data.repository.PatientResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientAverageIconmeViewModel: ViewModel() {

    private val repository: PatientResponseRepository =
        PatientResponseRepository()

    private val _avegareIncome = MutableLiveData<String>()

    fun setAverageIncome(value : String){
        _avegareIncome.value = value
    }

    fun saveValue(): Deferred<String> {
        val result = viewModelScope.async {
            repository.saveData(
                "patient_average_income",
                hashMapOf("pri_average_income" to _avegareIncome.value.toString())
            )
        }
        return result
    }
}