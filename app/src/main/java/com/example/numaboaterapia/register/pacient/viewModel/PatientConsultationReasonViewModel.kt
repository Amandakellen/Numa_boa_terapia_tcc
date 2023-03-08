package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.pacient.data.PatientResponseEnum
import com.example.numaboaterapia.register.pacient.data.repository.PatientResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientConsultationReasonViewModel: ViewModel() {
    var itens: ArrayList<PatientResponseEnum> = arrayListOf()
    private val repository: PatientResponseRepository =
        PatientResponseRepository()

    fun getDataItens(): ArrayList<PatientResponseEnum> {

        itens.add(PatientResponseEnum.ANXIETY)
        itens.add(PatientResponseEnum.PERSONAL_GROWTH)
        itens.add(PatientResponseEnum.DEPRESSION)
        itens.add(PatientResponseEnum.GRIEF)
        itens.add(PatientResponseEnum.COUPLE)
        itens.add(PatientResponseEnum.EATING_DISORDER)
        itens.add(PatientResponseEnum.SEXUALITY)
        itens.add(PatientResponseEnum.OTHER)

        return itens

    }


      fun saveValue(reason : String): Deferred<String> {
        val result = viewModelScope.async {
              repository.saveData(
                  "patient_reason",
                  hashMapOf("pr_reason" to reason)
              )
        }
        return result
    }

}