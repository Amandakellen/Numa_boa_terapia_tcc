package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.pacient.data.ConsultationReasonEnum
import com.example.numaboaterapia.register.pacient.data.repository.PatientResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientConsultationReasonViewModel: ViewModel() {
    var itens: ArrayList<ConsultationReasonEnum> = arrayListOf<ConsultationReasonEnum>()
    private val repository: PatientResponseRepository =
        PatientResponseRepository()

    fun getDataItens(): ArrayList<ConsultationReasonEnum> {

        itens.add(ConsultationReasonEnum.ANXIETY)
        itens.add(ConsultationReasonEnum.PERSONAL_GROWTH)
        itens.add(ConsultationReasonEnum.DEPRESSION)
        itens.add(ConsultationReasonEnum.GRIEF)
        itens.add(ConsultationReasonEnum.COUPLE)
        itens.add(ConsultationReasonEnum.EATING_DISORDER)
        itens.add(ConsultationReasonEnum.SEXUALITY)
        itens.add(ConsultationReasonEnum.OTHER)

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