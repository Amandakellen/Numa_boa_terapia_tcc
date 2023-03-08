package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.pacient.data.PatientResponseEnum
import com.example.numaboaterapia.register.pacient.data.repository.PatientResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientCivilStatusViewModel : ViewModel() {
    var itens: ArrayList<PatientResponseEnum> = arrayListOf()
    private val repository =  PatientResponseRepository()

        fun setDataItens(): ArrayList<PatientResponseEnum> {
            itens.add(PatientResponseEnum.SINGLE)
            itens.add(PatientResponseEnum.RELATIONSHIP)
            itens.add(PatientResponseEnum.DIVORCED)
            itens.add(PatientResponseEnum.WIDOWER)
            itens.add(PatientResponseEnum.DONT_INFORM)


            return itens

        }
    fun saveValue(civilStatus : String): Deferred<String> {
        val result = viewModelScope.async {
            repository.saveData(
                "patiant_civil_status",
                hashMapOf("pc_civil_status" to civilStatus)
            )
        }
        return result
    }
}